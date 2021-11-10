package team_mic.here_and_there.backend.search.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import team_mic.here_and_there.backend.attraction.exception.InvalidAttractionIdException;
import team_mic.here_and_there.backend.attraction.service.AttractionService;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoCorrespondingAudioGuideException;
import team_mic.here_and_there.backend.audio_guide.service.AudioGuideService;
import team_mic.here_and_there.backend.common.PageMetaDataDto;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.common.domain.ResourceType;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.search.domain.entity.SearchAttraction;
import team_mic.here_and_there.backend.search.domain.entity.SearchAudioGuide;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;
import team_mic.here_and_there.backend.search.domain.entity.SearchTripTip;
import team_mic.here_and_there.backend.search.domain.repository.SearchAttractionRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchAudioGuideRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchKeywordRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchTripTipRepository;
import team_mic.here_and_there.backend.search.dto.SearchResultAttractionListDto;
import team_mic.here_and_there.backend.search.dto.response.ResPatchedSearchKeywordDto;
import team_mic.here_and_there.backend.search.dto.response.ResSearchKeywordItemDto;
import team_mic.here_and_there.backend.search.dto.response.ResSearchKeywordRankListDto;
import team_mic.here_and_there.backend.search.dto.response.ResSearchResultListDto;
import team_mic.here_and_there.backend.search.exception.NoSearchKeywordException;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;
import team_mic.here_and_there.backend.trips_tip.exception.NoTripTipsException;
import team_mic.here_and_there.backend.trips_tip.service.TripTipsService;

@RequiredArgsConstructor
@Service
public class SearchService {

  private final SearchKeywordRepository searchKeywordRepository;
  private final SearchAudioGuideRepository searchAudioGuideRepository;
  private final SearchAttractionRepository searchAttractionRepository;
  private final SearchTripTipRepository searchTripTipRepository;

  private final AudioGuideRepository audioGuideRepository;
  private final AudioGuideTagRepository audioGuideTagRepository;
  private final AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;
  private final TripTipRepository tripTipRepository;

  private final AttractionService attractionService;
  private final AudioGuideService audioGuideService;
  private final TripTipsService tripTipsService;

  @Transactional
  public ResPatchedSearchKeywordDto updateSearchKeywordsHitCounts(String language, String keywordType, Long[] targetIds)
      throws UnsupportedEncodingException {
    SearchKeyword searchKeyword = null;
    Language lan = null;
    List<Long> updatedIds = new ArrayList<>();

    if(language.equals(Language.KOREAN.getVersion())) {
      lan = Language.KOREAN;
    }else if(language.equals(Language.ENGLISH.getVersion())) {
      lan = Language.ENGLISH;
    }

    if(keywordType.equals(ResourceType.AUDIO_GUIDE.getName())){
      searchKeyword = findOrSaveSearchAudioGuide(targetIds, lan, updatedIds);
    }else if(keywordType.equals(ResourceType.TRIP_TIP.getName())){
      searchKeyword = findOrSaveSearchTripTip(targetIds, lan, updatedIds);
    }else if(keywordType.equals(ResourceType.ATTRACTION.getName())){
      searchKeyword = findOrSaveSearchAttraction(targetIds, lan, updatedIds);
    }

    searchKeyword.updateSearchHitCount();

    return ResPatchedSearchKeywordDto.builder()
        .searchKeywordType(keywordType)
        .language(searchKeyword.getLanguage().getVersion())
        .updatedHitCounts(searchKeyword.getSearchHitCounts())
        .updatedTargetIds(updatedIds)
        .build();
  }

  @Transactional
  public SearchKeyword findOrSaveSearchAttraction(Long[] targetIds, Language lan,
      List<Long> updatedIds) throws UnsupportedEncodingException {
    Integer contentTypeId = Math.toIntExact(targetIds[0]);
    Long contentId = targetIds[1];

    String attractionTitle = attractionService.getDetailCommon(contentId, contentTypeId, lan.getVersion()).getTitle();

    updatedIds.addAll(Arrays.asList(targetIds));

    return searchAttractionRepository.findByContentTypeIdAndContentIdAndLanguage(contentTypeId, contentId, lan)
      .orElseGet(() -> searchAttractionRepository.save(SearchAttraction.builder()
        .title(attractionTitle)
        .contentTypeId(contentTypeId)
        .contentId(contentId)
        .language(lan)
        .build()));
  }

  private SearchKeyword findOrSaveSearchTripTip(Long[] targetIds, Language lan,
      List<Long> updatedIds) {
    TripTip tripTip = tripTipRepository.findByIdAndLanguage(targetIds[0], lan)
        .orElseThrow(NoTripTipsException::new);
    updatedIds.add(tripTip.getId());

    return searchTripTipRepository.findByTripTipAndLanguage(tripTip, lan)
        .orElseGet(()->searchTripTipRepository.save(SearchTripTip.builder()
            .tripTip(tripTip)
            .language(lan)
            .build()));
  }

  private SearchKeyword findOrSaveSearchAudioGuide(Long[] targetIds, Language lan, List<Long> updatedIds) {
    AudioGuideLanguageContent guideLanguageContent = audioGuideLanguageContentRepository.findByAudioGuide_IdAndLanguage(targetIds[0], lan)
        .orElseThrow(NoCorrespondingAudioGuideException::new);

    AudioGuide guide = guideLanguageContent.getAudioGuide();
    updatedIds.add(guide.getId());

    return searchAudioGuideRepository.findByAudioGuideAndLanguage(guide, lan)
        .orElseGet(()->searchAudioGuideRepository.save(SearchAudioGuide.builder()
            .language(lan)
            .audioGuide(guide)
            .build()));
  }

  @Transactional
  public ResSearchKeywordRankListDto getPopularSearchKeywordsRankings(String language, Integer count) {
    Language lan = null;
    if(language.equals(Language.ENGLISH.getVersion())){
      lan = Language.ENGLISH;
    }
    if(language.equals(Language.KOREAN.getVersion())){
      lan = Language.KOREAN;
    }

    Long totalSearchKeywordsCount = searchKeywordRepository.getTotalCountsByLanguage(lan);
    if(totalSearchKeywordsCount == 0){
      throw new NoSearchKeywordException();
    }
    if(count > totalSearchKeywordsCount){
      count = Math.toIntExact(totalSearchKeywordsCount);
    }

    PageRequest pageRequest = PageRequest.of(0, count, Sort.by("searchHitCounts").descending().and(Sort.by("id").ascending()));

    List<SearchKeyword> searchKeywords = searchKeywordRepository.findAllByLanguage(lan, pageRequest);

    List<ResSearchKeywordItemDto> searchKeywordItemList = searchKeywords.stream()
        .map(searchKeyword -> {
          try {
            return toSearchKeywordItem(searchKeyword);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
          }
        })
        .collect(Collectors.toList());

    return ResSearchKeywordRankListDto.builder()
        .count(count)
        .language(lan.getVersion())
        .keywordRankList(searchKeywordItemList)
        .build();
  }

  private ResSearchKeywordItemDto toSearchKeywordItem(SearchKeyword searchKeyword)
      throws UnsupportedEncodingException {
    Language language = searchKeyword.getLanguage();
    String keywordTitle = null;
    List<Long> targetIds = new ArrayList<>();
    String tripTipContentsUrl = null;

    if(searchKeyword.getDiscriminatorValue().equals(ResourceType.AUDIO_GUIDE.getName())){
      AudioGuide guide = ((SearchAudioGuide)searchKeyword).getAudioGuide();
      AudioGuideLanguageContent guideLanguageContent = audioGuideLanguageContentRepository.findByAudioGuide_IdAndLanguage(guide.getId(), language)
          .orElseThrow(NoSuchElementException::new);

      keywordTitle = guideLanguageContent.getTitle();
      targetIds.add(guide.getId());

    }else if(searchKeyword.getDiscriminatorValue().equals(ResourceType.TRIP_TIP.getName())){
      TripTip tripTip = ((SearchTripTip)searchKeyword).getTripTip();

      keywordTitle = tripTip.getTitle();
      targetIds.add(tripTip.getId());
      tripTipContentsUrl = tripTip.getContentsUrl();
    }else if(searchKeyword.getDiscriminatorValue().equals(ResourceType.ATTRACTION.getName())){
      SearchAttraction searchAttraction = (SearchAttraction)searchKeyword;

      if(searchAttraction.getTitle() == null || searchAttraction.getTitle().equals("")){
        String attractionTitle = attractionService.getDetailCommon(searchAttraction.getContentId(), searchAttraction.getContentTypeId(), language.getVersion()).getTitle();
        searchAttraction.updateTitle(attractionTitle);
      }
      keywordTitle = searchAttraction.getTitle();

      targetIds.add(Long.valueOf(searchAttraction.getContentTypeId()));
      targetIds.add(searchAttraction.getContentId());
    }

    return ResSearchKeywordItemDto.builder()
        .keywordType(searchKeyword.getDiscriminatorValue())
        .keywordTitle(keywordTitle)
        .searchHitCounts(searchKeyword.getSearchHitCounts())
        .keywordTargetIds(targetIds)
        .tripTipContentsUrl(tripTipContentsUrl)
        .build();
  }

  public ResSearchResultListDto getSearchResult(String language, String type,
      String keyword, Integer pageNumber, Integer pageSize) throws UnsupportedEncodingException {
    List resultList = new ArrayList();
    Language lan = null;
    if(language.equals(Language.ENGLISH.getVersion())){
      lan = Language.ENGLISH;
    }
    if(language.equals(Language.KOREAN.getVersion())){
      lan = Language.KOREAN;
    }

    PageRequest pageRequest = PageRequest.of(pageNumber-1, pageSize, Sort.by("viewCount").descending().and(Sort.by("id").ascending()));
    PageMetaDataDto pageMetaDataDto = null;

    if(type.equals(ResourceType.AUDIO_GUIDE.getName())){ // title, tags

      Page<AudioGuideLanguageContent> guideContentsContainingKeyword = audioGuideLanguageContentRepository.findDistinctTitleAndTagsByContaining(lan, keyword.toLowerCase(), pageRequest);
      pageMetaDataDto = PageMetaDataDto.builder()
          .currentPageNumber(guideContentsContainingKeyword.getNumber()+1)
          .currentPageSize(guideContentsContainingKeyword.getSize())
          .totalElements(guideContentsContainingKeyword.getTotalElements())
          .totalPageNumbers(guideContentsContainingKeyword.getTotalPages())
          .build();

      resultList.addAll(
          guideContentsContainingKeyword.getContent().stream()
          .map(guideLanguageContent -> audioGuideService.toAudioGuideItem(guideLanguageContent))
          .collect(Collectors.toList())
      );
    }else if(type.equals(ResourceType.TRIP_TIP.getName())){

      Page<TripTip> tripTipsContainingKeyword = tripTipRepository.findDistinctByTitleAndDescriptionContaining(lan, keyword.toLowerCase(), pageRequest);
      pageMetaDataDto = PageMetaDataDto.builder()
          .currentPageNumber(tripTipsContainingKeyword.getNumber()+1)
          .currentPageSize(tripTipsContainingKeyword.getSize())
          .totalElements(tripTipsContainingKeyword.getTotalElements())
          .totalPageNumbers(tripTipsContainingKeyword.getTotalPages())
          .build();

      resultList.addAll(
          tripTipsContainingKeyword.getContent().stream()
              .map(tripTip -> tripTipsService.toTripTipItemDto(tripTip))
              .collect(Collectors.toList())
      );
    }else if(type.equals(ResourceType.ATTRACTION.getName())){
      SearchResultAttractionListDto attractionListDto = attractionService.searchAttractionKeyword(keyword, lan, pageNumber, pageSize);

      Integer totalPageNumbers = attractionListDto.getTotalAttractionCount() % pageSize == 0 ?
          attractionListDto.getTotalAttractionCount() / pageSize : attractionListDto.getTotalAttractionCount() / pageSize + 1;

      pageMetaDataDto = PageMetaDataDto.builder()
          .currentPageSize(pageSize)
          .currentPageNumber(pageNumber)
          .totalPageNumbers(Math.toIntExact(totalPageNumbers))
          .totalElements(Long.valueOf(attractionListDto.getTotalAttractionCount()))
          .build();

      resultList.addAll(attractionListDto.getAttractionsList());
    }

    return ResSearchResultListDto.builder()
        .language(lan.getVersion())
        .searchKeyword(keyword)
        .type(type)
        .resultSize(resultList.size())
        .resultList(resultList)
        .pageMetaDataDto(pageMetaDataDto)
        .build();
  }
}
