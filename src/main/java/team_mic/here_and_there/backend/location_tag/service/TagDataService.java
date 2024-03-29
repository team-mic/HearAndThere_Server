package team_mic.here_and_there.backend.location_tag.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.location_tag.domain.repository.TagRepository;

@RequiredArgsConstructor
@Service
public class TagDataService {

  private final TagRepository tagRepository;
  private final AudioGuideTagRepository audioGuideTagRepository;
  private final AudioGuideRepository audioGuideRepository;

  public void insertGongneungTag() {
    Tag engTag = tagRepository.save(Tag.builder()
        .language(Language.ENGLISH)
        .name("Gongneung")
        .build());
    Tag korTag = tagRepository.save(Tag.builder()
        .language(Language.KOREAN)
        .name("공릉")
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(engTag)
        .audioGuide(audioGuideRepository.findById(1L).orElseThrow(NoSuchElementException::new))
        .build());
    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(korTag)
        .audioGuide(audioGuideRepository.findById(1L).orElseThrow(NoSuchElementException::new))
        .build());
  }

  public void insertTagsFromGongneungToInsadong() {

    String[] engTagsArr = {"Gongneung", "Namsan", "Noryanjin", "Daehak-Ro", "Dongdaemun", "Mangwon",
        "Bukchon", "Jamsil", "Jamsil", "Yongsan", "Yongsan", "OlympicPark", "Insadong"};
    String[] korTagsArr = {"공릉", "남산", "노량진", "대학로", "동대문", "망원", "북촌", "잠실", "잠실", "용산", "용산",
        "올림픽공원", "인사동"};

    saveAudioTags(engTagsArr, korTagsArr, 1L);
  }

  public void insertTagsFromDeoksugungToBuamdong() {
    String[] engTagsArr = {"Deoksugung Palace", "Gyeongbokgung Palace", "Changdeokgung Palace",
        "Yeouido", "Yeongdeungpo", "Hongdae", "Buam-dong"};
    String[] korTagsArr = {"덕수궁", "경복궁", "창덕궁", "여의도", "영등포", "홍대", "부암동"};

    saveAudioTags(engTagsArr, korTagsArr, 14L);
  }

  private void saveAudioTags(String[] engTagsArr, String[] korTagsArr,
      Long startPointOfAudioGuideId) {

    for (int idIndex = 0; idIndex < engTagsArr.length; idIndex++) {

      Tag engTag = tagRepository.save(Tag.builder()
          .language(Language.ENGLISH)
          .name(engTagsArr[idIndex])
          .build());
      Tag korTag = tagRepository.save(Tag.builder()
          .language(Language.KOREAN)
          .name(korTagsArr[idIndex])
          .build());

      audioGuideTagRepository.save(AudioGuideTag.builder()
          .tag(engTag)
          .audioGuide(audioGuideRepository.findById(idIndex + startPointOfAudioGuideId)
              .orElseThrow(NoSuchElementException::new))
          .build());
      audioGuideTagRepository.save(AudioGuideTag.builder()
          .tag(korTag)
          .audioGuide(audioGuideRepository.findById(idIndex + startPointOfAudioGuideId)
              .orElseThrow(NoSuchElementException::new))
          .build());
    }
  }
}
