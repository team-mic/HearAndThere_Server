package team_mic.here_and_there.backend.audio_guide.service;

import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideCategory;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideDirectionsDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResDirectionDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoCorrespondingAudioGuideException;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

import java.util.*;
import java.util.stream.Collectors;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;

@RequiredArgsConstructor
@Service
public class AudioGuideService {

  private final AudioGuideRepository audioGuideRepository;
  private final DirectionApiService directionApiService;

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

  private ResAudioGuideListDto getRandomAudioGuideList(String language) {
    int guidesSize = audioGuideRepository.findAll().size();
    if (guidesSize == 0) {
      throw new NoCorrespondingAudioGuideException();
    }
    List<ResAudioGuideItemDto> list = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    int setSize, randomId;
    Random random = new Random();

    for (int count = 0; count < RANDOM_AUDIO_GUIDES_COUNT; count++) {
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

    return ResAudioGuideListDto.builder()
        .language(language)
        .category("random")
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
        .imageUrl(audioGuide.getImages().get(0))
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

  public ResAudioGuideDirectionsDto getAudioGuideDirections(Long audioGuideId) {
    AudioGuide audioGuide = findAudioGuideById(audioGuideId);
    Set<AudioGuideTrackContainer> tracks = audioGuide.getTracks();

    List<ResDirectionDto> directionsList = directionApiService
        .getTracksPedestrianDirections(tracks);

    return ResAudioGuideDirectionsDto.builder()
        .audioGuideId(audioGuideId)
        .directions(directionsList)
        .build();
  }

  public ResAudioGuideListDto getAudioGuideCategoryList(String category, String language) {
    if ("random".equals(category)) {
      return getRandomAudioGuideList(language);
    }

    List<ResAudioGuideItemDto> guideList = new ArrayList();
    String languageCategory = null;

    if (category.equals(AudioGuideCategory.HISTORY.getQueryName())) {
      Long[] mainHistoryGuideIds = {7L};
      guideList = getAudioGuideItemList(mainHistoryGuideIds, language);

      if(language.equals(Language.KOREAN.getVersion())){
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseKoreanName();
      }
      if(language.equals(Language.ENGLISH.getVersion())){
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseEnglishName();
      }
    }
    if (category.equals(AudioGuideCategory.EXCURSION.getQueryName())) {
      Long[] mainExcursionGuideIds = {13L, 6L, 3L, 2L};
      guideList = getAudioGuideItemList(mainExcursionGuideIds, language);

      if(language.equals(Language.KOREAN.getVersion())){
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseKoreanName();
      }
      if(language.equals(Language.ENGLISH.getVersion())){
        languageCategory = AudioGuideCategory.HISTORY.getDatabaseEnglishName();
      }
    }

    return ResAudioGuideListDto.builder()
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
}
