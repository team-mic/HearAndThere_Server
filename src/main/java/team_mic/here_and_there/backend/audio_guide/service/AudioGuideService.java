package team_mic.here_and_there.backend.audio_guide.service;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.audio_course.dto.response.ResAudioCourseInfoItemDto;
import team_mic.here_and_there.backend.audio_course.service.AudioCourseService;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.domain.entity.MainCategory;
import team_mic.here_and_there.backend.audio_guide.domain.entity.SubCategory;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.MainCategoryRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.SubCategoryRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideDirectionsDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideSubCategoryDetailDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideLocationItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideLocationListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideOrderingListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideSubCategoryItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideSubCategoryListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResDirectionDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResPatchedSingleAudioGuideDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResSingleAudioGuideDetailDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResTrackPointDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoCorrespondingAudioGuideException;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

import java.util.*;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.common.domain.ImageSizeType;

@Slf4j
@RequiredArgsConstructor
@Service
public class AudioGuideService {

  private final AudioGuideRepository audioGuideRepository;
  private final AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;
  private final MainCategoryRepository mainCategoryRepository;
  private final SubCategoryRepository subCategoryRepository;
  private final DirectionApiService directionApiService;
  private final AudioTrackService audioTrackService;
  private final AudioCourseService audioCourseService;

  private final static Integer RANDOM_AUDIO_GUIDES_COUNT = 5;
  private final static String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

  private ResAudioGuideOrderingListDto getRandomAudioGuideList(Integer randomCount,
      String language) {
    Language lan = null;
    if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
    if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;

    int guidesSize = audioGuideRepository.findAll().size();
    if (guidesSize == 0) {
      throw new NoCorrespondingAudioGuideException();
    }
    List<ResAudioGuideItemDto> list = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    int setSize, randomId;
    Random random = new Random();

    for (int count = 0; count < randomCount; count++) {
      randomId = random.nextInt(guidesSize) + 1;
      setSize = set.size();
      set.add(randomId);
      if (set.size() == setSize) {
        count--;
        continue;
      }
      Optional<AudioGuide> audioGuide = audioGuideRepository.findById((long) randomId);
      if (!audioGuide.isPresent()) {
        count--;
        continue;
      }
      ResAudioGuideItemDto audioGuideItemDto = toAudioGuideItem(audioGuide.get(), lan);
      if(audioGuideItemDto != null){
        list.add(audioGuideItemDto);
      }
    }

    return ResAudioGuideOrderingListDto.builder()
        .language(language)
        .orderBy("random")
        .guideCounts(list.size())
        .audioGuideList(list)
        .build();
  }

  public ResAudioGuideItemDto toAudioGuideItem(AudioGuide audioGuide, Language language) {

    Set<AudioGuideLanguageContent> languageContentSet = audioGuide.getLanguageContents();
    Optional<AudioGuideLanguageContent> languageContent = Optional.empty();
    for (AudioGuideLanguageContent content : languageContentSet) {
      if (language.equals(content.getLanguage())) {
        languageContent = Optional.of(content);
      }
    }

    return languageContent
        .map(correspondingContent -> ResAudioGuideItemDto.builder()
            .audioGuideId(audioGuide.getId())
            .title(correspondingContent.getTitle())
            .thumbnailImageUrl(audioGuide.getImages().get(0) + ImageSizeType.SMALL.getSuffix())
            .tags(toTagsStringList(audioGuide.getTags(), correspondingContent.getLanguage()))
            .build())
        .orElse(null); //return null if audio guide has no version of required language.
  }

  public ResAudioGuideItemDto toAudioGuideItem(AudioGuideLanguageContent guideLanguageContent) {
    AudioGuide audioGuide = guideLanguageContent.getAudioGuide();

    return ResAudioGuideItemDto.builder()
        .audioGuideId(audioGuide.getId())
        .title(guideLanguageContent.getTitle())
        .thumbnailImageUrl(audioGuide.getImages().get(0) + ImageSizeType.SMALL.getSuffix())
        .tags(toTagsStringList(audioGuide.getTags(), guideLanguageContent.getLanguage()))
        .viewCount(Long.valueOf(guideLanguageContent.getViewCount()))
        .build();
  }

    private List<String> toTagsStringList(Set<AudioGuideTag> tags, Language language) {
    List<String> list = new ArrayList<>();

    for (AudioGuideTag audioGuideTag : tags) {
      Tag tag = audioGuideTag.getTag();

      if (language.equals(tag.getLanguage())) {
        list.add(tag.getName());
      }
    }
    return list;
  }

  public AudioGuide findAudioGuideById(Long audioGuideId) {
    return audioGuideRepository.findById(audioGuideId)
        .orElseThrow(() -> new NoCorrespondingAudioGuideException());
  }

  public ResAudioGuideDirectionsDto getAudioGuideDirections(Long audioGuideId)
      throws InterruptedException {
    AudioGuide audioGuide = findAudioGuideById(audioGuideId);
    Set<AudioGuideTrackContainer> tracks = audioGuide.getTracks();

    List<ResTrackPointDto> trackPointList = tracks.parallelStream()
            .map(audioGuideTrackContainer -> ResTrackPointDto.builder()
            .trackId(audioGuideTrackContainer.getAudioTrack().getId())
            .trackOrder(audioGuideTrackContainer.getOrderNumber())
            .trackLatitude(audioGuideTrackContainer.getAudioTrack().getLocationLatitude())
            .trackLongitude(audioGuideTrackContainer.getAudioTrack().getLocationLongitude())
            .build())
        .collect(Collectors.toList());

    List<ResDirectionDto> directionsList = directionApiService
        .getTracksPedestrianDirections(tracks);

    return ResAudioGuideDirectionsDto.builder()
        .audioGuideId(audioGuideId)
        .trackPoints(trackPointList)
        .directions(directionsList)
        .build();
  }

  public ResAudioGuideSubCategoryDetailDto getAudioGuideMainCategoryListV1(String category, String language) {
    MainCategory mainCategory = null;
    AtomicReference<String> categoryName = new AtomicReference<>("");
    List<ResAudioGuideItemDto> guideItemList = new ArrayList<>();
    Language lan = null;
    if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
    if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;

    if (category.equals("art")) {
      mainCategory = mainCategoryRepository.findByEngNameEquals("Art and Design")
              .orElseThrow(NoSuchElementException::new);

    }
    if (category.equals("excursion")) {
      mainCategory = mainCategoryRepository.findByEngNameEquals("Healing in Everyday Life")
          .orElseThrow(NoSuchElementException::new);
    }

    Language finalLan = lan;
    mainCategory.getSubCategories().stream()
        .findFirst()
        .ifPresent(subCategory -> {
          List<AudioGuide> audioGuides = getGuidesOfSubCategory(subCategory);
          guideItemList.addAll(audioGuides.stream()
              .map(guide -> toAudioGuideItem(guide, finalLan))
              .filter(resAudioGuideItemDto -> resAudioGuideItemDto != null)
              .collect(Collectors.toList()));
          categoryName.set(getSubCategoryTitle(subCategory, finalLan));
        });

    return ResAudioGuideSubCategoryDetailDto.builder()
        .language(language)
        .category(categoryName.get())
        .audioGuideList(guideItemList)
        .build();
  }

  public ResAudioGuideOrderingListDto getTopNAudioGuidesListByOrder(String order,
      Integer guideCount, String language) {
    List<AudioGuideLanguageContent> languageContents = new ArrayList<>();
    List<ResAudioGuideItemDto> resultList = new ArrayList<>();

    Integer totalGuidesCount = audioGuideRepository.findAll().size();
    if (guideCount == null || guideCount > totalGuidesCount) {
      guideCount = totalGuidesCount;
    }

    if (order.equals("random")) {
      return getRandomAudioGuideList(guideCount, language);
    }

    if (language.equals(Language.KOREAN.getVersion())) {
      if (order.equals("viewcount")) {
        languageContents = audioGuideLanguageContentRepository
            .findAllByLanguageOrderByViewCountDesc(Language.KOREAN);
      }

      if (order.equals("playingcount")) {
        languageContents = audioGuideLanguageContentRepository
            .findAllByLanguageOrderByPlayingCountDesc(Language.KOREAN);
      }
    }

    if (language.equals(Language.ENGLISH.getVersion())) {
      if (order.equals("viewcount")) {
        languageContents = audioGuideLanguageContentRepository
            .findAllByLanguageOrderByViewCountDesc(Language.ENGLISH);
      }

      if (order.equals("playingcount")) {
        languageContents = audioGuideLanguageContentRepository
            .findAllByLanguageOrderByPlayingCountDesc(Language.ENGLISH);
      }
    }

    if (languageContents.isEmpty()) {
      throw new NoCorrespondingAudioGuideException();
    }

    for (int count = 0; count < guideCount; count++) {
      AudioGuideLanguageContent languageContent = languageContents.get(count);
      AudioGuide guide = languageContent.getAudioGuide();

      resultList.add(ResAudioGuideItemDto.builder()
          .title(languageContent.getTitle())
          .audioGuideId(guide.getId())
          .thumbnailImageUrl(guide.getImages().get(0) + ImageSizeType.SMALL.getSuffix())
          .tags(toTagsStringList(guide.getTags(), languageContent.getLanguage()))
          .build());
    }

    return ResAudioGuideOrderingListDto.builder()
        .audioGuideList(resultList)
        .language(language)
        .guideCounts(guideCount)
        .orderBy(order)
        .build();
  }

  public ResSingleAudioGuideDetailDto getSingleAudioGuideDetail(Long audioGuideId,
      String language, Boolean isHlsSupport) {

    AudioGuide guide = findAudioGuideById(audioGuideId);
    Set<AudioGuideLanguageContent> languageContentSet = guide.getLanguageContents();
    Optional<AudioGuideLanguageContent> languageContent = Optional.empty();

    for (AudioGuideLanguageContent guideLanguageContent : languageContentSet) {
      if (guideLanguageContent.getLanguage().getVersion().equals(language)) {
        languageContent = Optional.of(guideLanguageContent);
      }
    }

    AudioGuideLanguageContent correspondingContent = languageContent
        .orElseThrow(NoSuchElementException::new); //TODO : custom exception

    List<ResAudioTrackInfoItemDto> tracks = audioTrackService
        .getAudioGuidesTrackList(guide, language, isHlsSupport);

    List<ResAudioGuideItemDto> recommendedGuides = guide.getRecommendedAudioGuideIds().stream()
        .map(guideId -> {
          Language lan = null;
          if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
          if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;

          return toAudioGuideItem(findAudioGuideById(guideId), lan);
        })
        .filter(resAudioGuideItemDto -> resAudioGuideItemDto != null)
        .collect(Collectors.toList());
    List<ResAudioCourseInfoItemDto> courses = audioCourseService
        .getAudioGuidesCourseList(guide, language);

    return ResSingleAudioGuideDetailDto.builder()
        .language(language)
        .audioGuideId(guide.getId())
        .distance(guide.getDistance())
        .estimatedTravelTime(guide.getEstimatedTravelTime())
        .location(guide.getLocation())
        .guideImages(guide.getImages().stream()
            .map(image -> image + ImageSizeType.MIDDLE.getSuffix())
            .collect(Collectors.toList()))
        .title(correspondingContent.getTitle())
        .overviewDescription(correspondingContent.getOverviewDescription())
        .tags(toTagsStringList(guide.getTags(), correspondingContent.getLanguage()))
        .tracksList(tracks)
        .recommendedAudioGuidesList(recommendedGuides)
        .coursesList(courses)
        .recommendedContentsList(new ArrayList<>()) //TODO : 지금은 모두 empty 인 상황 / 일반적인 로직으로 적용하기
        .build();
  }

  @Transactional
  public ResPatchedSingleAudioGuideDto updateAudioGuideCountingColumn(Long audioGuideId,
      String updateField,
      String language) {

    AudioGuide guide = findAudioGuideById(audioGuideId);
    Set<AudioGuideLanguageContent> languageContentSet = guide.getLanguageContents();
    Optional<AudioGuideLanguageContent> languageContent = Optional.empty();
    if(updateField.equals("viewcount")){
      for(AudioGuideLanguageContent content : languageContentSet){
        if(content.getLanguage().getVersion().equals(language)){
          languageContent = Optional.of(content);
          content.updateViewCount();
        }
      }
    }

    if(updateField.equals("playcount")){
      for(AudioGuideLanguageContent content : languageContentSet){
        if(content.getLanguage().getVersion().equals(language)){
          languageContent = Optional.of(content);
          content.updatePlayingCount();
        }
      }
    }

    AudioGuideLanguageContent correspondingContent = languageContent.orElseThrow(NoSuchElementException::new);

    return ResPatchedSingleAudioGuideDto.builder()
        .audioGuideId(guide.getId())
        .language(language)
        .updatedField(updateField)
        .guidePlayCount(String.valueOf(correspondingContent.getPlayingCount()))
        .guideViewCount(String.valueOf(correspondingContent.getViewCount()))
        .build();
  }

  public ResAudioGuideLocationListDto getAudioGuideLocationMap(String language) {
    List<AudioGuide> guideList = audioGuideRepository.findAll();
    Language lan = null;
    if(language.equals(Language.ENGLISH.getVersion())){
      lan = Language.ENGLISH;
    }
    if(language.equals(Language.KOREAN.getVersion())){
      lan = Language.KOREAN;
    }

    Language finalLan = lan;
    List<ResAudioGuideLocationItemDto> locationItemList =
        guideList.stream()
            .map(guide -> toLocationItem(guide, finalLan))
            .collect(Collectors.toList());

    return ResAudioGuideLocationListDto.builder()
        .language(language)
        .guideLocationsList(locationItemList)
        .build();
  }

  private ResAudioGuideLocationItemDto toLocationItem(AudioGuide guide, Language language) {
    AudioTrack firstTrack = guide.getTracks().iterator().next().getAudioTrack();

    AudioGuideLanguageContent correspondingContent = findAudioGuideLanguageContentByLanguage(guide, language);

    return ResAudioGuideLocationItemDto.builder()
        .audioGuideId(guide.getId())
        .firstTrackLatitude(firstTrack.getLocationLatitude())
        .firstTrackLongitude(firstTrack.getLocationLongitude())
        .guideTitle(correspondingContent.getTitle())
        .guideDistance(guide.getDistance())
        .guideEstimatedTravelTime(guide.getEstimatedTravelTime())
        .guideLocation(guide.getLocation())
        .guideImageUrl(guide.getImages().get(0) + ImageSizeType.MIDDLE.getSuffix())
        .build();
  }

  //TODO : 사용자가 관심있는 대카테고리에 속한 랜덤 2개의 소카테고리 리스트를 제공
  public List<ResAudioGuideSubCategoryDetailDto> getAudioGuideSubCategoryListV2(String language) {
    List<SubCategory> subCategories = subCategoryRepository.findAll();
    Set<Integer> randomIndexes = new HashSet<>();
    Random random = new Random();
    List<ResAudioGuideSubCategoryDetailDto> resultList = new ArrayList<>();

    Language lan = null;
    if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
    if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;

    while(randomIndexes.size()<2){
      randomIndexes.add(random.nextInt(subCategories.size()));
    }

    for(Integer index : randomIndexes){
      SubCategory pickedSubCategory = subCategories.get(index);

      List<AudioGuide> guides = new ArrayList<>();

      guides.addAll(getGuidesOfSubCategory(pickedSubCategory));

      Language finalLan = lan;
      List<ResAudioGuideItemDto> guideItemList = guides.stream()
          .map(guide -> toAudioGuideItem(guide, finalLan))
          .filter(resAudioGuideItemDto -> resAudioGuideItemDto != null)
          .collect(Collectors.toList());

      resultList.add(ResAudioGuideSubCategoryDetailDto.builder()
          .category(getSubCategoryTitle(pickedSubCategory, lan))
          .language(language)
          .audioGuideList(guideItemList)
          .build());
    }

    return resultList;
  }

  public ResAudioGuideSubCategoryListDto getAudioGuideSubCategoryList(String language) {
    List<SubCategory> subCategories = subCategoryRepository.findAll();
    List<ResAudioGuideSubCategoryItemDto> resultList = new ArrayList<>();

    if(language.equals(Language.ENGLISH.getVersion())){
      resultList.addAll(subCategories.stream()
          .map(subCategory -> ResAudioGuideSubCategoryItemDto.builder()
              .categoryId(subCategory.getId())
              .categoryName(subCategory.getEngName())
              .categoryArea("Seoul") //TODO : 카테고리에 속하는 가이드의 도시
              .bannerBackgroundColorHex(subCategory.getBannerBackgroundColor())
              .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + subCategory.getBannerIconImage())
              .guideCount(getGuidesOfSubCategory(subCategory).size())
              .build())
          .collect(Collectors.toList()));

    }else if(language.equals(Language.KOREAN.getVersion())){
      resultList.addAll(subCategories.stream()
          .map(subCategory -> ResAudioGuideSubCategoryItemDto.builder()
              .categoryId(subCategory.getId())
              .categoryName(subCategory.getKorName())
              .categoryArea("서울") //TODO : 카테고리에 속하는 가이드의 도시
              .bannerBackgroundColorHex(subCategory.getBannerBackgroundColor())
              .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + subCategory.getBannerIconImage())
              .guideCount(getGuidesOfSubCategory(subCategory).size())
              .build())
          .collect(Collectors.toList()));

    }

    return ResAudioGuideSubCategoryListDto.builder()
        .language(language)
        .categoryGuideList(resultList)
        .build();
  }

  private List<AudioGuide> getGuidesOfSubCategory(SubCategory subCategory) {
    return audioGuideRepository.findAllBySubCategory(subCategory);
  }

  public ResAudioGuideSubCategoryDetailDto getAudioGuideListOfSubCategoryId(String language, Integer subCategoryId) {

    List<AudioGuide> audioGuides = new ArrayList<>();
    List<ResAudioGuideItemDto> guideItemList = new ArrayList<>();
    Language lan = null;
    if(language.equals(Language.KOREAN.getVersion())) lan = Language.KOREAN;
    if(language.equals(Language.ENGLISH.getVersion())) lan = Language.ENGLISH;


    SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));

    audioGuides.addAll(getGuidesOfSubCategory(subCategory));

    String categoryTitle = getSubCategoryTitle(subCategory, lan);

    Language finalLan = lan;
    guideItemList.addAll(audioGuides.stream()
        .map(guide -> toAudioGuideItem(guide, finalLan))
        .filter(resAudioGuideItemDto -> resAudioGuideItemDto != null)
        .collect(Collectors.toList()));

    Long firstGuideId = guideItemList.get(0).getAudioGuideId();
    String firstGuideImage = findAudioGuideById(firstGuideId).getImages().get(0);

    String mainGreeting = ""; //TODO 추후 카테고리마다 변경
    if(lan.equals(Language.ENGLISH)){
      mainGreeting = "Listen to the region’s hidden stories\\nthrough the audio guide chosen by Hear Story!";
    }
    if(lan.equals(Language.KOREAN)){
      mainGreeting = "히어스토리가 선정한 오디오 가이드를 통해\\n해당 지역의 숨겨진 이야기를 들어보세요!";
    }

    return ResAudioGuideSubCategoryDetailDto.builder()
        .language(language)
        .category(categoryTitle)
        .categoryMainImage(firstGuideImage)
        .categoryMainGreeting(mainGreeting)
        .audioGuideList(guideItemList)
        .build();
  }

  private String getSubCategoryTitle(SubCategory subCategory, Language language){
    if(language.equals(Language.KOREAN)){
      return subCategory.getEmojiUnicode() + " " + subCategory.getKorName();
    }

    if(language.equals(Language.ENGLISH)){
      return subCategory.getEmojiUnicode() + " " + subCategory.getEngName();
    }

    return "";
  }

  public AudioGuideLanguageContent findAudioGuideLanguageContentByLanguage(AudioGuide audioGuide, Language language){

    Optional<AudioGuideLanguageContent> languageContent = Optional.empty();
    for(AudioGuideLanguageContent content : audioGuide.getLanguageContents()){
      if(content.getLanguage().equals(language)){
        languageContent = Optional.of(content);
      }
    }

    AudioGuideLanguageContent correspondingContent = languageContent.orElseThrow(NoSuchElementException::new);

    return correspondingContent;
  }
}
