package team_mic.here_and_there.backend.audio_guide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideItemDto;
import team_mic.here_and_there.backend.audio_guide.dto.response.ResAudioGuideListDto;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AudioGuideService {

  private final AudioGuideRepository audioGuideRepository;

  private final static Integer RANDOM_AUDIO_GUIDES_COUNT = 5;

  public ResAudioGuideListDto getAudioGuideList(String category) {
    if ("random".equals(category)) {
      return getRandomAudioGuideList();
    }
    return getAudioGuideListByCategory(category);
  }

  private ResAudioGuideListDto getAudioGuideListByCategory(String category) {
    List<AudioGuide> audioGuides = audioGuideRepository.findTop4ByCategory(category);
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
        .imageUrl(audioGuide.getImages().iterator().next())
        .tags(toTagsStringList(audioGuide.getTags()))
        .build();
  }

  private List<String> toTagsStringList(Set<AudioGuideTag> tags) {
    List<String> list = new ArrayList<>();
    tags.forEach(audioGuideTag -> list.add(audioGuideTag.getTag().getName()));
    return list;
  }

  public AudioGuide findAudioGuideById(Long audioGuideId) {
    return audioGuideRepository.findById(audioGuideId)
        .orElseThrow(() -> new NoSuchElementException()); //TODO: custom exception
  }
}
