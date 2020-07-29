package team_mic.here_and_there.backend.audio_guide.service;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideTrackContainer;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioTrack;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideTrackContainerRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioTrackRepository;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.location_tag.domain.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class AudioGuideDumpService {

  private final AudioGuideRepository audioGuideRepository;
  private final AudioTrackRepository audioTrackRepository;
  private final AudioGuideTrackContainerRepository audioGuideTrackContainerRepository;
  private final TagRepository tagRepository;
  private final AudioGuideTagRepository audioGuideTagRepository;

  public void insertDumpTracksIntoGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder().title("test").build());
    AudioTrack audioTrack1 = audioTrackRepository.save(AudioTrack.builder()
        .audioFileUrl("track1.file")
        .title("트랙1")
        .image("트랙1 이미지").build());
    AudioTrack audioTrack2 = audioTrackRepository.save(AudioTrack.builder()
        .audioFileUrl("track2.file")
        .title("트랙2")
        .image("트랙2 이미지").build());
    AudioTrack audioTrack3 = audioTrackRepository.save(AudioTrack.builder()
        .audioFileUrl("track3.file")
        .title("트랙3")
        .image("트랙3 이미지").build());

    audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
        .audioGuide(audioGuide)
        .audioTrack(audioTrack3)
        .orderNumber(3).build());
    audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
        .audioGuide(audioGuide)
        .audioTrack(audioTrack2)
        .orderNumber(2).build());
    audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
        .audioGuide(audioGuide)
        .audioTrack(audioTrack1)
        .orderNumber(1).build());

    AudioGuide audioGuide2 = audioGuideRepository.save(AudioGuide.builder().title("test2").build());
    AudioTrack audioTrack2_1 = audioTrackRepository.save(AudioTrack.builder()
        .audioFileUrl("track2_1.file")
        .title("트랙2_1").build());
    AudioTrack audioTrack2_2 = audioTrackRepository.save(AudioTrack.builder()
        .audioFileUrl("track2_2.file")
        .title("트랙2_2").build());

    audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
        .audioGuide(audioGuide2)
        .audioTrack(audioTrack2_2)
        .orderNumber(2).build());
    audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
        .audioGuide(audioGuide2)
        .audioTrack(audioTrack2_1)
        .orderNumber(1).build());

  }

  public void insertDumpGuides() {
    //강남
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/gangnam.png");
            }})
            .category("shopping")
            .title("The center of shopping and K-culture")
            .build()
    );

    Tag tag1 = tagRepository.save(
        Tag.builder()
            .name("Gangnam")
            .build()
    );

    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag1)
            .audioGuide(audioGuide)
            .build()
    );

    //광화문
    AudioGuide audioGuide2 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/gwanghwa.png");
            }})
            .category("traditional")
            .title("History and culture breathe")
            .build()
    );

    Tag tag2 = tagRepository.save(
        Tag.builder()
            .name("Seoul City Hall")
            .build()
    );
    Tag tag3 = tagRepository.save(
        Tag.builder()
            .name("Gwanghwa")
            .build()
    );
    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag2)
            .audioGuide(audioGuide2)
            .build()
    );
    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag3)
            .audioGuide(audioGuide2)
            .build()
    );

    //인사동
    AudioGuide audioGuide3 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/insadong.png");
            }})
            .category("traditional")
            .title("Filled with Hangeul")
            .build()
    );

    Tag tag4 = tagRepository.save(
        Tag.builder()
            .name("Insa-dong")
            .build()
    );

    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag4)
            .audioGuide(audioGuide3)
            .build()
    );

    //종로
    AudioGuide audioGuide4 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/jongno.png");
            }})
            .category("traditional")
            .title("Ancient meets modern")
            .build()
    );

    Tag tag5 = tagRepository.save(
        Tag.builder()
            .name("Jongno")
            .build()
    );

    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag5)
            .audioGuide(audioGuide4)
            .build()
    );

    //북촌
    AudioGuide audioGuide5 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bukchon.png");
            }})
            .category("traditional")
            .title("Beautiful Hanok cafes and restaurants")
            .build()
    );

    Tag tag6 = tagRepository.save(
        Tag.builder()
            .name("Samcheongdong")
            .build()
    );
    Tag tag7 = tagRepository.save(
        Tag.builder()
            .name("Bukchon")
            .build()
    );
    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag6)
            .audioGuide(audioGuide5)
            .build()
    );
    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag7)
            .audioGuide(audioGuide5)
            .build()
    );

    //동대문
    AudioGuide audioGuide6 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/dongdaemun.png");
            }})
            .category("shopping")
            .title("The mecca of fashion and shopping")
            .build()
    );

    Tag tag8 = tagRepository.save(
        Tag.builder()
            .name("Dongdaemun")
            .build()
    );

    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag8)
            .audioGuide(audioGuide6)
            .build()
    );

    //잠실
    AudioGuide audioGuide7 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/jamsil.png");
            }})
            .category("shopping")
            .title("The center of shopping and entertainment")
            .build()
    );

    Tag tag9 = tagRepository.save(
        Tag.builder()
            .name("Jamsil")
            .build()
    );

    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag9)
            .audioGuide(audioGuide7)
            .build()
    );

    //남대문
    AudioGuide audioGuide8 = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/namdaemun.png");
            }})
            .category("shopping")
            .title("Best-known shopping street and attraction")
            .build()
    );

    Tag tag10 = tagRepository.save(
        Tag.builder()
            .name("Myeongdong")
            .build()
    );
    Tag tag11 = tagRepository.save(
        Tag.builder()
            .name("Namdaemun")
            .build()
    );
    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag10)
            .audioGuide(audioGuide8)
            .build()
    );
    audioGuideTagRepository.save(
        AudioGuideTag.builder()
            .tag(tag11)
            .audioGuide(audioGuide8)
            .build()
    );
  }
}
