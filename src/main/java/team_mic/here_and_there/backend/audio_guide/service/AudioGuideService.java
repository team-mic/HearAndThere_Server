package team_mic.here_and_there.backend.audio_guide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideDirectionsDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideListDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResDirectionDto;
import team_mic.here_and_there.backend.audio_guide.exception.NoCorrespondingAudioGuideException;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

import java.util.*;
import java.util.stream.Collectors;

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
  }

  private ResAudioGuideListDto getRandomAudioGuideList() {
    int guidesSize = audioGuideRepository.findAll().size();
    if(guidesSize == 0){
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
      list.add(toAudioGuideItem(audioGuide.get()));
    }

    return ResAudioGuideListDto.builder()
        .category("random")
        .audioGuideList(list)
        .build();
  }

  private ResAudioGuideItemDto toAudioGuideItem(AudioGuide audioGuide) {
    return ResAudioGuideItemDto.builder()
        .audioGuideId(audioGuide.getId())
        .title(audioGuide.getTitle())
        .imageUrl(audioGuide.getImages().get(0))
        .tags(toTagsStringList(audioGuide.getTags()))
        .build();
  }*/

  private List<String> toTagsStringList(Set<AudioGuideTag> tags) {
    List<String> list = new ArrayList<>();
    tags.forEach(audioGuideTag -> list.add(audioGuideTag.getTag().getName()));
    return list;
  }

  public AudioGuide findAudioGuideById(Long audioGuideId) {
    return audioGuideRepository.findById(audioGuideId)
        .orElseThrow(() -> new NoCorrespondingAudioGuideException());
  }

  public ResAudioGuideDirectionsDto getAudioGuideDirections(Long audioGuideId) {
    AudioGuide audioGuide = findAudioGuideById(audioGuideId);
    Set<AudioGuideTrackContainer> tracks= audioGuide.getTracks();

    List<ResDirectionDto> directionsList = directionApiService.getTracksPedestrianDirections(tracks);

    return ResAudioGuideDirectionsDto.builder()
        .audioGuideId(audioGuideId)
        .directions(directionsList)
        .build();
  }
}
