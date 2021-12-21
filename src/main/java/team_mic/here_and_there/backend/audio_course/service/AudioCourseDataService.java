package team_mic.here_and_there.backend.audio_course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElementLanguageContent;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioCourseElementLanguageContentRepository;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioCourseElementRepository;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioGuideCourseRepository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.common.domain.Language;

@Slf4j
@RequiredArgsConstructor
@Service
public class AudioCourseDataService {

  private final AudioCourseElementRepository audioCourseElementRepository;
  private final AudioCourseElementLanguageContentRepository audioCourseElementLanguageContentRepository;
  private final AudioGuideCourseRepository audioGuideCourseRepository;
  private final AudioGuideRepository audioGuideRepository;

  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

  public void insertGongneungCourses() {

    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("화랑대역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Hwarang station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/2-1.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/2-2.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/2-3.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/2-4.jpg");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("화랑대 철도공원")
            .tourApiAttractionContentId(2654601L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Hwarangdae Railroad Park")
            .tourApiAttractionContentId(2656744L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/3.jpg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("화랑대 역사전시관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Hwarangdae History Museum")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/4.jpg");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("도깨비시장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Dokkaebi Traditional Market")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/5.jpg");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("공트럴파크")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Gyeongchun line Forest Trail")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/6-1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/6-2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/6-3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Gongneung/6-4.png");

            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .tourApiAttractionContentId(1866427L)
            .tourApiAttractionContentTypeId(14)
            .title("서울시립 북서울미술관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .tourApiAttractionContentId(1867810L)
            .tourApiAttractionContentTypeId(78)
            .title("The Buk-Seoul Museum of Art")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(1L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertNamsanCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Namsan/1.jpg");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("충무로역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Chungmuro Station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Namsan/2.jpeg");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("남산타워")
            .tourApiAttractionContentId(126535L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Namsan Tower")
            .tourApiAttractionContentId(264550L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Namsan/3.jpeg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("명동")
            .tourApiAttractionContentId(264311L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Myeong-dong")
            .tourApiAttractionContentId(264312L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Namsan/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("명동예술극장")
            .tourApiAttractionContentId(748186L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Myeongdong Theater")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Namsan/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("서울명동성당")
            .tourApiAttractionContentId(126804L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Myeongdong Cathedral")
            .tourApiAttractionContentId(264138L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(2L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertNoryangjinCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Noryanjin/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("노량진 수산시장")
            .tourApiAttractionContentId(132216L)
            .tourApiAttractionContentTypeId(38)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Noryangjin Fisheries Wholesale Market")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Noryanjin/2.jpeg");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("노량진 컵밥거리")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Noryangjin Cup Rice Alley")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Noryanjin/3.jpg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("사육신공원")
            .tourApiAttractionContentId(126523L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Sayuksin Park")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Noryanjin/4.jpeg");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("한강대교")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Han River Bridge")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Noryanjin/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("노들섬")
            .tourApiAttractionContentId(2638475L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Nodeul Island")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(3L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertDaehakroCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Daehak-Ro/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("혜화역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Hyehwa station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Daehak-Ro/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("마로니에공원")
            .tourApiAttractionContentId(126487L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Marronnier Park")
            .tourApiAttractionContentId(264313L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Daehak-Ro/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("낙산공원")
            .tourApiAttractionContentId(129501L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .tourApiAttractionContentId(264478L)
            .tourApiAttractionContentTypeId(76)
            .title("Naksan Park")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Daehak-Ro/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("학림다방")
            .tourApiAttractionContentId(2389009L)
            .tourApiAttractionContentTypeId(39)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Hakrim Dabang")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Daehak-Ro/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("대명거리")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("daemyung street")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Daehak-Ro/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("성균관")
            .tourApiAttractionContentId(126517L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Sungkyunkwan")
            .tourApiAttractionContentId(1407539L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(4L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertDongdaemunCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Dongdaemun/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("동대문")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Dongdaemun")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Dongdaemun/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("서울성곽")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Fortress Wall of Seoul")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Dongdaemun/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("비우당과 자주동샘")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Biudang House and Jajidongcheon Well")
            .build());

    AudioCourseElement element4 = audioCourseElementLanguageContentRepository.findByTitle("낙산공원")
        .orElseThrow(NoSuchElementException::new)
        .getAudioCourseElement();

    list.add(element4);

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Dongdaemun/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("낙산전시관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Naksan exhibition hall")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Dongdaemun/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("이화장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Iwhajang")
            .build());

    AudioCourseElement element7 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Dongdaemun/7.png");
            }})
            .build());
    list.add(element7);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element7)
            .title("이화동벽화마을")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element7)
            .title("Ihwa Mural Village")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(5L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertMangwonCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Mangwon/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("망원역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Mangwon Station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Mangwon/2.jpg");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("망원정")
            .tourApiAttractionContentId(127307L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Mangwonjeong Pavilion Site")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Mangwon/3.jpg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("망리단길")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Mangnidangil")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Mangwon/4.jpg");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("망원시장")
            .tourApiAttractionContentId(2499807L)
            .tourApiAttractionContentTypeId(38)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Mangwon Market")
            .tourApiAttractionContentId(2592401L)
            .tourApiAttractionContentTypeId(79)
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Mangwon/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("망원한강공원")
            .tourApiAttractionContentId(1059638L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Mangwon Hangang Park")
            .tourApiAttractionContentId(1064349L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(6L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertBukchonCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Bukchon/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("안국역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Anguk Station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Bukchon/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("북촌 문화센터")
            .tourApiAttractionContentId(130903L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Bukchon Cultural Center")
            .tourApiAttractionContentId(610704L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("50min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Bukchon/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("북촌 한옥마을")
            .tourApiAttractionContentId(126537L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Bukchon Hanok Village")
            .tourApiAttractionContentId(561382L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Bukchon/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("가회동 성당")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Gahoedong Catholic Church")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Bukchon/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("정독도서관")
            .tourApiAttractionContentId(1019294L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Jeongdok Public Library")
            .tourApiAttractionContentId(1031213L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Bukchon/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("감고당길")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Gamgodang-gil")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(7L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertJamsilCourses() {
    //A Good Neighborhood for a Walk
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/a_good_neighborhood_for_a_walk/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("석촌호수")
            .tourApiAttractionContentId(754052L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Seokchon Lake(Seoho-Dongho)")
            .tourApiAttractionContentId(1542646L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/a_good_neighborhood_for_a_walk/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("롯데월드타워")
            .tourApiAttractionContentId(2003909L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Lotte World Tower")
            .tourApiAttractionContentId(2003918L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/a_good_neighborhood_for_a_walk/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("잠실지하상가")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Jamsil Underground Shopping Center")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/a_good_neighborhood_for_a_walk/4.jpg");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("방이동 먹자골목")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Bangi Food Alley")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/a_good_neighborhood_for_a_walk/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("서울책보고")
            .tourApiAttractionContentId(2606740L)
            .tourApiAttractionContentTypeId(38)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Seoul Book Report")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/a_good_neighborhood_for_a_walk/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("잠실 한강공원")
            .tourApiAttractionContentId(970460L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Jamsil Han River Park")
            .tourApiAttractionContentId(1000299L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(8L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }

    //Seoul Special Tourist Zone
    List<AudioCourseElement> list2 = new ArrayList<>();

    list2.add(element1);

    list2.add(element2);

    AudioCourseElement element2_3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("60min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/seoul_specials_tourist_zone/3.png");
            }})
            .build());
    list2.add(element2_3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_3)
            .title("롯데월드 어드벤처")
            .tourApiAttractionContentId(126498L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_3)
            .title("Lotte World")
            .tourApiAttractionContentId(264152L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element2_4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/seoul_specials_tourist_zone/4.png");
            }})
            .build());
    list2.add(element2_4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_4)
            .title("샤롯데씨어터")
            .tourApiAttractionContentId(130886L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_4)
            .title("Charlotte Theater")
            .tourApiAttractionContentId(268226L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element2_5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("25min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Jamsil/seoul_specials_tourist_zone/5.jpg");
            }})
            .build());
    list2.add(element2_5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_5)
            .title("올림픽공원")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_5)
            .title("Olympic Park")
            .build());

    AudioGuide audioGuide2 = audioGuideRepository.findById(9L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list2.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list2.get(order))
          .audioGuide(audioGuide2)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertYongSanCourses() {
    //Yongsan
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seoul/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("삼각지역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Samgakji Station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("60min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seoul/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("전쟁기념관")
            .tourApiAttractionContentId(130431L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("The War Memorial of Korea")
            .tourApiAttractionContentId(268131L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seou/3.jpg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("열정도")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Yeoljeongdo Street")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seoul/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("효창공원")
            .tourApiAttractionContentId(127643L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Hyochang Park")
            .tourApiAttractionContentId(1747993L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seoul/5.jpg");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("용문전통시장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Yongmun Traditional Market")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seoul/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("아이파크몰")
            .tourApiAttractionContentId(688998L)
            .tourApiAttractionContentTypeId(38)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("I'Park Mall")
            .tourApiAttractionContentId(273803L)
            .tourApiAttractionContentTypeId(79)
            .build());

    AudioCourseElement element7 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/the_center_of_seoul/7.jpg");
            }})
            .build());
    list.add(element7);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element7)
            .title("아모레퍼시픽 미술관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element7)
            .title("Amorepacific Museum of Art")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(10L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }

    //A Village Full of History and Culture
    List<AudioCourseElement> list2 = new ArrayList<>();

    AudioCourseElement element2_1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/a_village_full_of_history_and_culture/1.png");
            }})
            .build());
    list2.add(element2_1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_1)
            .title("국립중앙박물관")
            .tourApiAttractionContentId(129703L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_1)
            .title("National  Museum of Korea")
            .tourApiAttractionContentId(268137L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element2_2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/a_village_full_of_history_and_culture/2.jpg");
            }})
            .build());
    list2.add(element2_2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_2)
            .title("국립한글박물관")
            .tourApiAttractionContentId(1954333L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_2)
            .title("National Hangul Museum")
            .tourApiAttractionContentId(1962734L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element2_3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/a_village_full_of_history_and_culture/3.jpg");
            }})
            .build());
    list2.add(element2_3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_3)
            .title("용산가족공원")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_3)
            .title("Yongsan Family Park")
            .build());

    list2.add(element6); // 2_4
    list2.add(element7); // 2_5

    AudioCourseElement element2_6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX
                  + "/audio-guides/audio_courses/Yongsan/a_village_full_of_history_and_culture/6.jpg");
            }})
            .build());
    list2.add(element2_6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2_6)
            .title("용리단길")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2_6)
            .title("Yongridan-gil")
            .build());

    list2.add(element2); //2_7

    AudioGuide audioGuide2 = audioGuideRepository.findById(11L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list2.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list2.get(order))
          .audioGuide(audioGuide2)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertOlympicParkCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("세계평화의문")
            .tourApiAttractionContentId(126532L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("World Peace Gate")
            .tourApiAttractionContentId(789703L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("평화의 광장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Peace Plaza")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/3.jpeg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("몽촌해자 음악분수")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Mongchon Moat Musical Fountain")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/4.jpeg");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("곰말다리")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Gomal Bridge")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/5.jpg");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("나홀로나무")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("One Tree hill")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/6.jpeg");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("88호수")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("88 Lake")
            .build());

    AudioCourseElement element7 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/7.png");
            }})
            .build());
    list.add(element7);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element7)
            .title("야생화학습장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element7)
            .title("Wildflower Garden")
            .build());

    AudioCourseElement element8 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/8.png");
            }})
            .build());
    list.add(element8);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element8)
            .title("조각공원")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element8)
            .title("Sculpture Park")
            .build());

    AudioCourseElement element9 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/9.png");
            }})
            .build());
    list.add(element9);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element9)
            .title("소마미술관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element9)
            .title("SOMA(Seoul Olympic Museum of Art)")
            .build());

    AudioCourseElement element10 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/10.jpg");
            }})
            .build());
    list.add(element10);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element10)
            .title("한성백제박물관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element10)
            .title("Seoul Hanseong Baekje Museum")
            .build());

    AudioCourseElement element11 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/11.png");
            }})
            .build());
    list.add(element11);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element11)
            .title("들꽃마루")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element11)
            .title("Flower Garden")
            .build());

    AudioCourseElement element12 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/12.jpg");
            }})
            .build());
    list.add(element12);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element12)
            .title("장미광장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element12)
            .title("Rose Plaza")
            .build());

    AudioCourseElement element13 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/OlympicPark/13.jpg");
            }})
            .build());
    list.add(element13);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element13)
            .title("만남의 광장")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element13)
            .title("Meeting Plaza")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(12L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }


  public void insertInsadongCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Insadong/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("보신각터")
            .tourApiAttractionContentId(126516L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Bosingak Belfry")
            .tourApiAttractionContentId(264135L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Insadong/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("공평도시유적전시관")
            .tourApiAttractionContentId(2606224L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Gongpyeong Historic Site Museum")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Insadong/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("인사동")
            .tourApiAttractionContentId(264353L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Insadong")
            .tourApiAttractionContentId(264354L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Insadong/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("운현궁")
            .tourApiAttractionContentId(127454L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Unhyeongung Royal Residence")
            .tourApiAttractionContentId(264223L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Insadong/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("낙원상가")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Nakwon Arcade")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Insadong/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .tourApiAttractionContentId(2650046L)
            .tourApiAttractionContentTypeId(12)
            .title("익선동")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Ikseon-dong")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(13L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertDeoksugungPalaceCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("55min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/DeoksugungPalace/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("덕수궁")
            .tourApiAttractionContentId(126509L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Deoksugung")
            .tourApiAttractionContentId(264316L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/DeoksugungPalace/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("덕수궁 돌담길")
            .tourApiAttractionContentId(129186L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Deoksugung Stonewall Walkway/Dol-dam Road")
            .tourApiAttractionContentId(1748351L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/DeoksugungPalace/3.jpg");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("고종의 길")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("King Gojong’s Road")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/DeoksugungPalace/4.jpg");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("정동공원")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Jeongdong Park")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/DeoksugungPalace/5.jpg");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("중명전")
            .tourApiAttractionContentId(1017547L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Jungmyeongjeon")
            .tourApiAttractionContentId(1034818L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(14L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertGyeongbokgungPalaceCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("120min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/GyeongbokgungPalace/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("경복궁")
            .tourApiAttractionContentId(126508L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Gyeongbokgung Palace")
            .tourApiAttractionContentId(264337L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(15L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertChangdeokgungPalaceCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("60min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/ChangdeokgungPalace/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("창덕궁")
            .tourApiAttractionContentId(127642L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Changdeokgung Palace")
            .tourApiAttractionContentId(264348L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(16L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertYeouidoCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("5min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/1.png");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("여의도역")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Yeouido Station")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("IFC몰")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("IFC Mall")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("SeMA벙커")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("SeMa Bunker")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("여의도공원")
            .tourApiAttractionContentId(127955L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Yeouido Park")
            .tourApiAttractionContentId(264340L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("25min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("국회의사당")
            .tourApiAttractionContentId(126827L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Assembly building")
            .tourApiAttractionContentId(264132L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("25min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/6.png");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("여의도한강공원")
            .tourApiAttractionContentId(1059479L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Yeouido Han River Park")
            .tourApiAttractionContentId(1064767L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element7 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeouido/7.png");
            }})
            .build());
    list.add(element7);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element7)
            .title("63 스퀘어")
            .tourApiAttractionContentId(127699L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element7)
            .title("63 Square")
            .tourApiAttractionContentId(264122L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(17L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertYeongdeungpoCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeongdeungpo/1.jpg");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("영등포공원")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Yeongdeungpo Park")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeongdeungpo/2.png");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("타임스퀘어")
            .tourApiAttractionContentId(806322L)
            .tourApiAttractionContentTypeId(38)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Time Square")
            .tourApiAttractionContentId(834090L)
            .tourApiAttractionContentTypeId(79)
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeongdeungpo/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("문래예술촌")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Mullae Art Village")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeongdeungpo/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("영등포로 지하쇼핑센터")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Yeongdeungpo Underground Shopping Center")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("15min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Yeongdeungpo/5.jpeg");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("영등포 먹자골목")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Yeongdeungpo Food Alley")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(18L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertHongdaeCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("10min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Hongdae/1.jpeg");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("홍대입구")
            .tourApiAttractionContentId(781031L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Hongik University Entrance")
            .tourApiAttractionContentId(1326972L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Hongdae/2.jpeg");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("1984")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("1984")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Hongdae/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("공상온도")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Gongsang Ondo")
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Hongdae/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("온고당서점")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Ongodang Bookstore")
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Hongdae/5.jpeg");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("안도북스")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("Ando Books")
            .build());

    AudioCourseElement element6 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Hongdae/6.jpeg");
            }})
            .build());
    list.add(element6);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element6)
            .title("경의선숲길 (경의선 책거리)")
            .tourApiAttractionContentId(2500229L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element6)
            .title("Gyeongui Line Forest Road (Gyeongui Line Book Street)")
            .tourApiAttractionContentId(2538121L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(19L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }

  public void insertBuamdongCourses() {
    List<AudioCourseElement> list = new ArrayList<>();

    AudioCourseElement element1 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Buam-dong/1.jpeg");
            }})
            .build());
    list.add(element1);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element1)
            .title("청운공원(윤동주시인의 언덕)")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element1)
            .title("Cheongun Park (Yoon Dong-ju Hill)")
            .build());

    AudioCourseElement element2 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Buam-dong/2.jpeg");
            }})
            .build());
    list.add(element2);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element2)
            .title("윤동주문학관")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element2)
            .title("Yoon Dong-ju Literary Museum")
            .build());

    AudioCourseElement element3 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("30min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Buam-dong/3.png");
            }})
            .build());
    list.add(element3);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element3)
            .title("환기미술관")
            .tourApiAttractionContentId(129760L)
            .tourApiAttractionContentTypeId(14)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element3)
            .title("Whanki (Art) Museum")
            .tourApiAttractionContentId(268099L)
            .tourApiAttractionContentTypeId(78)
            .build());

    AudioCourseElement element4 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("20min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Buam-dong/4.png");
            }})
            .build());
    list.add(element4);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element4)
            .title("백사실계곡")
            .tourApiAttractionContentId(809372L)
            .tourApiAttractionContentTypeId(12)
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element4)
            .title("Baeksasil Valley")
            .tourApiAttractionContentId(1851871L)
            .tourApiAttractionContentTypeId(76)
            .build());

    AudioCourseElement element5 = audioCourseElementRepository.save(
        AudioCourseElement.builder()
            .estimatedTravelTime("40min")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_courses/Buam-dong/5.png");
            }})
            .build());
    list.add(element5);
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.KOREAN)
            .audioCourseElement(element5)
            .title("산모퉁이카페(커피프린스1호점 촬영지)")
            .build());
    audioCourseElementLanguageContentRepository.save(
        AudioCourseElementLanguageContent.builder()
            .language(Language.ENGLISH)
            .audioCourseElement(element5)
            .title("San-motung-i Cafe (Coffee Prince's No.1 shooting location)")
            .build());

    AudioGuide audioGuide = audioGuideRepository.findById(20L)
        .orElseThrow(() -> new NoSuchElementException());

    for (int order = 0; order < list.size(); order++) {
      audioGuideCourseRepository.save(AudioGuideCourse.builder()
          .audioCourseElement(list.get(order))
          .audioGuide(audioGuide)
          .orderNumber(order + 1)
          .build());
    }
  }
}
