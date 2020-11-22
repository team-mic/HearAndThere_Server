package team_mic.here_and_there.backend.audio_guide.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.dto.response.ResAudioCourseInfoItemDto;
import team_mic.here_and_there.backend.audio_course.service.AudioCourseService;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideCategory;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideDirectionsDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideCategoryListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideOrderingListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioTrackInfoItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResDirectionDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResSingleAudioGuideDetailDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoCorrespondingAudioGuideException;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

import java.util.*;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;

@RequiredArgsConstructor
@Service
public class AudioGuideService {

  private final AudioGuideRepository audioGuideRepository;
  private final AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;

  private final DirectionApiService directionApiService;
  private final AudioTrackService audioTrackService;
  private final AudioCourseService audioCourseService;

  private final static Integer RANDOM_AUDIO_GUIDES_COUNT = 5;

  /*
  public ResAudioGuideListDto getAudioGuideList(String category) {
    if ("random".equals(category)) {
      return getRandomAudioGuideList();
    }
    return getAudioGuideListByCategory(category);
  }

  private ResAudioGuideListDto getAudioGuideListByCategory(String category) {
    List<AudioGuide> audioGuides = audioGuideRepository.findTop4ByCategory(category);
    if(audioGuides.isEmpty()){
      throw new NoCorrespondingAudioGuideException();
    }
    List<ResAudioGuideItemDto> list = audioGuides.parallelStream()
        .map(audioGuide -> toAudioGuideItem(audioGuide))
        .collect(Collectors.toList());

    return ResAudioGuideListDto.builder()
        .category(category)
        .audioGuideList(list)
        .build();
  }*/

  private ResAudioGuideOrderingListDto getRandomAudioGuideList(Integer randomCount,
      String language) {
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
      list.add(toAudioGuideItem(audioGuide.get(), language));
    }

    return ResAudioGuideOrderingListDto.builder()
        .language(language)
        .orderBy("random")
        .guideCounts(randomCount)
        .audioGuideList(list)
        .build();
  }

  private ResAudioGuideItemDto toAudioGuideItem(AudioGuide audioGuide, String language) {

    Set<AudioGuideLanguageContent> languageContentSet = audioGuide.getLanguageContents();
    Optional<AudioGuideLanguageContent> languageContent = Optional.empty();
    for (AudioGuideLanguageContent content : languageContentSet) {
      if (language.equals(content.getLanguage().getVersion())) {
        languageContent = Optional.of(content);
      }
    }

    AudioGuideLanguageContent correspondingContent = languageContent
        .orElseThrow(NoSuchElementException::new); //TODO : custom exception

    return ResAudioGuideItemDto.builder()
        .audioGuideId(audioGuide.getId())
        .title(correspondingContent.getTitle())
        .thumbnailImageUrl(audioGuide.getImages().get(0))
        .tags(toTagsStringList(audioGuide.getTags(), language))
        .build();
  }

  private List<String> toTagsStringList(Set<AudioGuideTag> tags, String language) {
    List<String> list = new ArrayList<>();

    for (AudioGuideTag audioGuideTag : tags) {
      Tag tag = audioGuideTag.getTag();

      if (language.equals(tag.getLanguage().getVersion())) {
        list.add(tag.getName());
      }
    }
    return list;
  }

  public AudioGuide findAudioGuideById(Long audioGuideId) {
    return audioGuideRepository.findById(audioGuideId)
        .orElseThrow(() -> new NoCorrespondingAudioGuideException());
  }
  /*
  public ResAudioGuideDirectionsDto getAudioGuideDirections(Long audioGuideId) {
    AudioGuide audioGuide = findAudioGuideById(audioGuideId);
    Set<AudioGuideTrackContainer> tracks = audioGuide.getTracks();

    List<ResDirectionDto> directionsList = directionApiService
        .getTracksPedestrianDirections(tracks);

    return ResAudioGuideDirectionsDto.builder()
        .audioGuideId(audioGuideId)
        .directions(directionsList)
        .build();
  }*/

  public ResAudioGuideCategoryListDto getAudioGuideCategoryList(String category, String language) {

    List<ResAudioGuideItemDto> guideList = new ArrayList();
    String languageCategory = null;

    if (category.equals(AudioGuideCategory.HISTORY.getQueryName())) {
      Long[] mainHistoryGuideIds = {7L};
      guideList = getAudioGuideItemList(mainHistoryGuideIds, language);

      if (language.equals(Language.KOREAN.getVersion())) {
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseKoreanName();
      }
      if (language.equals(Language.ENGLISH.getVersion())) {
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseEnglishName();
      }
    }
    if (category.equals(AudioGuideCategory.EXCURSION.getQueryName())) {
      Long[] mainExcursionGuideIds = {13L, 6L, 3L, 2L};
      guideList = getAudioGuideItemList(mainExcursionGuideIds, language);

      if (language.equals(Language.KOREAN.getVersion())) {
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseKoreanName();
      }
      if (language.equals(Language.ENGLISH.getVersion())) {
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseEnglishName();
      }
    }

    return ResAudioGuideCategoryListDto.builder()
        .language(language)
        .category(languageCategory)
        .audioGuideList(guideList)
        .build();
  }

  private List<ResAudioGuideItemDto> getAudioGuideItemList(Long[] mainGuideIds, String language) {
    List<ResAudioGuideItemDto> list = new ArrayList<>();
    for (Long guideId : mainGuideIds) {
      AudioGuide guide = findAudioGuideById(guideId);
      list.add(toAudioGuideItem(guide, language));
    }

    return list;
  }

  public ResAudioGuideOrderingListDto getTopNAudioGuidesListByOrder(String order,
      Integer guideCount, String language) {
    List<AudioGuideLanguageContent> languageContents = new ArrayList<>();
    List<ResAudioGuideItemDto> resultList = new ArrayList<>();

    Integer totalGuidesCount = (int) (audioGuideRepository.count());
    if (guideCount == null) {
      guideCount = totalGuidesCount;
    }
    if (guideCount > totalGuidesCount) {
      throw new NoCorrespondingAudioGuideException(); //TODO custom exception
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
          .thumbnailImageUrl(guide.getImages().get(0))
          .tags(toTagsStringList(guide.getTags(), language))
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
      String language) {

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
        .getAudioGuidesTrackList(guide, language);
    List<ResAudioGuideItemDto> recommendedGuides = guide.getRecommendedAudioGuideIds().stream()
        .map(guideId -> toAudioGuideItem(findAudioGuideById(guideId), language))
        .collect(Collectors.toList());
    List<ResAudioCourseInfoItemDto> courses = audioCourseService
        .getAudioGuidesCourseList(guide, language);

    return ResSingleAudioGuideDetailDto.builder()
        .language(language)
        .audioGuideId(guide.getId())
        .distance(guide.getDistance())
        .estimatedTravelTime(guide.getEstimatedTravelTime())
        .location(guide.getLocation())
        .guideImages(guide.getImages())
        .title(correspondingContent.getTitle())
        .overviewDescription(correspondingContent.getOverviewDescription())
        .tags(toTagsStringList(guide.getTags(), language))
        .tracksList(tracks)
        .recommendedAudioGuidesList(recommendedGuides)
        .coursesList(courses)
        .recommendedContentsList(new ArrayList<>()) //TODO : 지금은 모두 empty 인 상황 / 일반적인 로직으로 적용하기
        .build();
  }
}
