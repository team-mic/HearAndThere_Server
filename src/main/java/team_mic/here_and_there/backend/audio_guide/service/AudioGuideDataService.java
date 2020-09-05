package team_mic.here_and_there.backend.audio_guide.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import javassist.NotFoundException;
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
public class AudioGuideDataService {

  private final AudioGuideRepository audioGuideRepository;
  private final AudioTrackRepository audioTrackRepository;
  private final AudioGuideTrackContainerRepository audioGuideTrackContainerRepository;
  private final TagRepository tagRepository;
  private final AudioGuideTagRepository audioGuideTagRepository;

  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net/";

  public void insertDumpTracksIntoGuides() {
    List<AudioGuide> audioGuideList = audioGuideRepository.findAll();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Mangwon-dong introduction 1")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/A+Thousand+Years+.mp3")
        .placeName("Mangwon-Station")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("5:06")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Mangwon-dong introduction 2")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Butterfly+and+cat.mp3")
        .placeName("Mangwon-Station")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("3:08")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Mangwonjeong Pavilion Site")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Comethru.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("2:59")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Hangang introduction")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Dandelion.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("4:03")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Background of Ridangil")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Do+you+want+to+go+see+the+stars.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("3:21")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("About Mangnidan-gil")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/FREEDOM.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("3:33")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("About Traditional market")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Let's+take+time.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("3:44")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Mangwon market introduction")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/November+Rain.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("4:18")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Mangwon market introduction2")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Whale.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("3:20")
        .build());

    AudioTrack track10 = audioTrackRepository.save(AudioTrack.builder()
        .images(new ArrayList<String>() {{
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image1.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image3.png");
                  add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/images/test_image2.png");
                }}
        ).title("Mangwon market introduction3")
        .placeName("Mangwon-Station")
        .audioFileUrl(
            "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/audio_tracks/audio-files/Whenever+Wherever.mp3")
        .placeAddress("467, Maponaru-gil, Mapo-gu, Seoul 04005 South Korea")
        .runningTime("4:05")
        .build());

    audioGuideList.stream().forEach(
        audioGuide -> {
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track1)
              .orderNumber(1)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track2)
              .orderNumber(2)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track3)
              .orderNumber(3)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track4)
              .orderNumber(4)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track5)
              .orderNumber(5)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track6)
              .orderNumber(6)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track7)
              .orderNumber(7)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track8)
              .orderNumber(8)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track9)
              .orderNumber(9)
              .build());
          audioGuideTrackContainerRepository.save(AudioGuideTrackContainer.builder()
              .audioGuide(audioGuide)
              .audioTrack(track10)
              .orderNumber(10)
              .build());
        });
  }

  public void insertDumpGuides() {
    //강남
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/gangnam.png");
            }})
            .category(new HashSet<String>() {{
              add("shopping");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/gwanghwa.png");
            }})
            .category(new HashSet<String>() {{
              add("traditional");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/insadong.png");
            }})
            .category(new HashSet<String>() {{
              add("traditional");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/jongno.png");
            }})
            .category(new HashSet<String>() {{
              add("traditional");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bukchon.png");
            }})
            .category(new HashSet<String>() {{
              add("traditional");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/dongdaemun.png");
            }})
            .category(new HashSet<String>() {{
              add("shopping");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/jamsil.png");
            }})
            .category(new HashSet<String>() {{
              add("shopping");
            }})
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
            .images(new ArrayList<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/namdaemun.png");
            }})
            .category(new HashSet<String>() {{
              add("shopping");
            }})
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

  public void deleteAllGuideTrackContainers() {
    audioGuideTrackContainerRepository.deleteAll();
  }

  public void insertBukchonGuides() {
    audioGuideRepository.save(AudioGuide.builder()
        .title("Bukchon, a village where you can enjoy the peaceful ambience of hanok")
        .category(new HashSet<String>() {{
          add("history");
          add("k-drama");
          add("k-pop");
        }})
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "audio-guides/images/bukchon/guide_bukchon_1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "audio-guides/images/bukchon/guide_bukchon_2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "audio-guides/images/bukchon/guide_bukchon_3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "audio-guides/images/bukchon/guide_bukchon_4.jpeg");
        }})
        .location("Bukchon-ro, Jongno-gu, Seoul")
        .overviewDescription(
            "Welcome to Bukchon, a village where you can enjoy the peaceful ambience of hanok\n"
                + "'Bukchon' ,which means 'North Village', was named this way because it lies north of  Cheonggyecheon Stream and Jongno area.\n"
                + "Bukchon used to be a residential area for Yangban the ruling class of the Joseon Dynasty.\n"
                + "Beautiful alleys and hanoks are still well preseved so you can feel the atmosphere of the Joseon Dynasty here.\n"
                + "Because of these, It is still loved by many people. Let's go to see the beautiful Bukchon Hanok Village.")
        .build());
  }

  public void insertBukchonTracksIntoGuide() {

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack audioTrack1 = audioTrackRepository.save(AudioTrack.builder()
        .title("Bukchon")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/1_bukchon.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/anguk_station_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/anguk_station_2.png");
        }})
        .locationLatitude(37.577182D)
        .locationLongitude(126.986045D)
        .placeName("Anguk Station")
        .runningTime("1:52")
        .build());

    AudioTrack audioTrack2 = audioTrackRepository.save(AudioTrack.builder()
        .title("Bukchon Hanok Village")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/2_ bukchon_hanok_village.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_4 .png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_4.jpeg");
        }})
        .locationLatitude(37.578952D)
        .locationLongitude(126.986701D)
        .placeName("Bukchon Cultural Center & Bukchon Hanok Village")
        .runningTime("3:07")
        .build());

    AudioTrack audioTrack3 = audioTrackRepository.save(AudioTrack.builder()
        .title("Hanok Introduction")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/3_hanok_introduction.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/hanok_introduction_1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/hanok_introduction_2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/hanok_introduction_3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/hanok_introduction_4.png");
        }})
        .locationLatitude(37.582091D)
        .locationLongitude(126.986786D)
        .placeName("Hanok")
        .runningTime("2:05")
        .build());

    AudioTrack audioTrack4 = audioTrackRepository.save(AudioTrack.builder()
        .title("Various workshops in Bukchon")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/4_various_workshops_in_bukchon.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_4.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_5.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_6.jpeg");
        }})
        .locationLatitude(37.582679D)
        .locationLongitude(126.985828D)
        .placeName("Various workshops")
        .runningTime("2:57")
        .build());

    AudioTrack audioTrack5 = audioTrackRepository.save(AudioTrack.builder()
        .title("Gahoedong Catholic Church")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/5_gahoedong_catholic_church.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_catholic_church_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_catholic_church_2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_catholic_church_3.png");
        }})
        .locationLatitude(37.581737D)
        .locationLongitude(126.985120D)
        .placeName("Gahoedong Catholic Church")
        .runningTime("3:30")
        .build());

    AudioTrack audioTrack6 = audioTrackRepository.save(AudioTrack.builder()
        .title("Gahoedong Alleyways and photo spots")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/6_gahoedong_alleyways_and_photo_spots.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_alleyways_and_photo_spots_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_alleyways_and_photo_spots_2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_alleyways_and_photo_spots_3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_alleyways_and_photo_spots_4.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gahoedong_alleyways_and_photo_spots_5.jpeg");
        }})
        .locationLatitude(37.582578D)
        .locationLongitude(126.983617D)
        .placeName("Gahoedong Alleyways")
        .runningTime("0:49")
        .build());

    AudioTrack audioTrack7 = audioTrackRepository.save(AudioTrack.builder()
        .title("History and Introduction of Sogyeok-dong")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/6_gahoedong_alleyways_and_photo_spots.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/history_and_introduction_of_sogyeok-dong_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/history_and_introduction_of_sogyeok-dong_2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/history_and_introduction_of_sogyeok-dong_3.png");
        }})
        .locationLatitude(37.582346D)
        .locationLongitude(126.982171D)
        .placeName("Sogyeok-dong")
        .runningTime("0:44")
        .build());

    AudioTrack audioTrack8 = audioTrackRepository.save(AudioTrack.builder()
        .title("The road of independence activists and Jeongdok Public Library")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/8_the_road_of_independence_activists_and_jeongdok_public_library.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_5.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_6.png");
        }})
        .locationLatitude(37.580334D)
        .locationLongitude(126.982503D)
        .placeName("Jeongdok Public Library")
        .runningTime("2:05")
        .build());

    AudioTrack audioTrack9 = audioTrackRepository.save(AudioTrack.builder()
        .title("Gamgodang-gil and \"We are young\" graffiti")
        .audioFileUrl(
            AWS_CLOUD_FRONT_URL_PREFIX
                + "audio-guides/audio_tracks/audio-files/bukchon/9_gamgodang-gil_and_we_are_young_graffiti.mp3")
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gamgodang-gil_1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gamgodang-gil_2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX
              + "audio-guides/audio_tracks/images/bukchon/gamgodang-gil_3.png");
        }})
        .locationLatitude(37.578768D)
        .locationLongitude(126.98211D)
        .placeName("Gamgodang-gil")
        .runningTime("1:32")
        .build());

    list.add(audioTrack1);
    list.add(audioTrack2);
    list.add(audioTrack3);
    list.add(audioTrack4);
    list.add(audioTrack5);
    list.add(audioTrack6);
    list.add(audioTrack7);
    list.add(audioTrack8);
    list.add(audioTrack9);

    AudioGuide bukchonGuide = audioGuideRepository
        .findByTitle("Bukchon, a village where you can enjoy the peaceful ambience of hanok")
        .orElseThrow(() -> new NoSuchElementException());

    int orderNumber = 1;
    for (AudioTrack audioTrack : list) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioGuide(bukchonGuide)
              .audioTrack(audioTrack)
              .orderNumber(orderNumber)
              .build());
      orderNumber++;
    }
  }
}
