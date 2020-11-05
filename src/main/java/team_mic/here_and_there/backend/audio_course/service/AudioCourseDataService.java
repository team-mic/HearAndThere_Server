package team_mic.here_and_there.backend.audio_course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioCourseElementRepository;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioGuideCourseRepository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;

@RequiredArgsConstructor
@Service
public class AudioCourseDataService {

  private final AudioCourseElementRepository audioCourseElementRepository;
  private final AudioGuideCourseRepository audioGuideCourseRepository;
  private final AudioGuideRepository audioGuideRepository;

  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net/";

  /*
  public void insertBukchonCourses() {

    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement audioCourseElement1 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Anguk Station")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/anguk_station_1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/anguk_station_2.png");
            }})
            .build());

    AudioCourseElement audioCourseElement2 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Bukchon Cultural Center")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_cultural_center_4 .png");
            }})
            .tourApiAttractionId(610704L)
            .build());

    AudioCourseElement audioCourseElement3 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Bukchon Hanok Village")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/bukchon_hanok_village_4.jpeg");
            }})
            .tourApiAttractionId(561382L)
            .build());

    AudioCourseElement audioCourseElement4 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Bukchon Traditional Crafts Center")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_4.jpeg");
            }})
            .build());

    AudioCourseElement audioCourseElement5 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Geumbagyeon")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_5.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_6.jpeg");
            }})
            .build());

    AudioCourseElement audioCourseElement6 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Saeksil Munyang Nubi workshop")
            .build());

    AudioCourseElement audioCourseElement7 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Dongnim knot workshop")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_1.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/various_workshops_in_bukchon_2.jpeg");
            }})
            .build());

    AudioCourseElement audioCourseElement8 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Gahoedong Catholic Church")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/gahoedong_catholic_church_1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/gahoedong_catholic_church_2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/gahoedong_catholic_church_3.png");
            }})
            .build());

    AudioCourseElement audioCourseElement9 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Jeongdok Public Library")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_5.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/the_road_of_independence_activists_and_jeongdok_public_library_6.png");
            }})
            .build());

    AudioCourseElement audioCourseElement10 = audioCourseElementRepository
        .save(AudioCourseElement.builder()
            .title("Gamgodang-gil")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/gamgodang-gil_1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/gamgodang-gil_2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "audio-guides/audio_tracks/images/bukchon/gamgodang-gil_3.png");
            }})
            .build());

    list.add(audioCourseElement1);
    list.add(audioCourseElement2);
    list.add(audioCourseElement3);
    list.add(audioCourseElement4);
    list.add(audioCourseElement5);
    list.add(audioCourseElement6);
    list.add(audioCourseElement7);
    list.add(audioCourseElement8);
    list.add(audioCourseElement9);
    list.add(audioCourseElement10);

    AudioGuide bukchonGuide = audioGuideRepository
        .findByTitle("Bukchon, a village where you can enjoy the peaceful ambience of hanok")
        .orElseThrow(() -> new NoSuchElementException());

    int orderNumber = 1;
    for (AudioCourseElement audioCourseElement : list) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(audioCourseElement)
          .audioGuide(bukchonGuide)
          .orderNumber(orderNumber)
          .build());
      orderNumber++;
    }
  }*/
}
