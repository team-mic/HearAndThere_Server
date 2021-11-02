package team_mic.here_and_there.backend.search.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.attraction.exception.InvalidAttractionIdException;
import team_mic.here_and_there.backend.attraction.service.AttractionService;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.exception.NoCorrespondingAudioGuideException;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchAttraction;
import team_mic.here_and_there.backend.search.domain.entity.SearchAudioGuide;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;
import team_mic.here_and_there.backend.search.domain.entity.SearchTripTip;
import team_mic.here_and_there.backend.search.domain.repository.SearchAttractionRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchAudioGuideRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchKeywordRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchTripTipRepository;
import team_mic.here_and_there.backend.search.dto.response.ResPatchedSearchKeywordDto;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;
import team_mic.here_and_there.backend.trips_tip.exception.NoTripTipsException;

@RequiredArgsConstructor
@Service
public class SearchService {

  private final SearchKeywordRepository searchKeywordRepository;
  private final SearchAudioGuideRepository searchAudioGuideRepository;
  private final SearchAttractionRepository searchAttractionRepository;
  private final SearchTripTipRepository searchTripTipRepository;

  private final AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;
  private final TripTipRepository tripTipRepository;

  private final AttractionService attractionService;

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

    if(keywordType.equals("audio-guide")){
      searchKeyword = findOrSaveSearchAudioGuide(targetIds, lan, updatedIds);
    }else if(keywordType.equals("trip-tip")){
      searchKeyword = findOrSaveSearchTripTip(targetIds, lan, updatedIds);
    }else if(keywordType.equals("attraction")){
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

  private SearchKeyword findOrSaveSearchAttraction(Long[] targetIds, Language lan,
      List<Long> updatedIds) throws UnsupportedEncodingException {
    Integer contentTypeId = Math.toIntExact(targetIds[0]);
    Long contentId = targetIds[1];

    Integer countsOfAttraction = attractionService.getCountsOfAttraction(contentTypeId, contentId, lan);
    if(countsOfAttraction == 0){
      throw new InvalidAttractionIdException();
    }

    updatedIds.addAll(Arrays.asList(targetIds));

    return searchAttractionRepository.findByContentTypeIdAndContentIdAndLanguage(contentTypeId, contentId, lan)
        .orElseGet(()->searchAttractionRepository.save(SearchAttraction.builder()
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
}
