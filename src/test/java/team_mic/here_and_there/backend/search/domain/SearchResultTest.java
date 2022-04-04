package team_mic.here_and_there.backend.search.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElement;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElementLanguageContent;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioCourseElementLanguageContentRepository;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioCourseElementRepository;
import team_mic.here_and_there.backend.audio_course.domain.repository.AudioGuideCourseRepository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.location_tag.domain.repository.TagRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SearchResultTest {

  @Autowired
  private AudioGuideTagRepository audioGuideTagRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;

  @Autowired
  private AudioGuideRepository audioGuideRepository;

  @Autowired
  private AudioCourseElementRepository audioCourseElementRepository;

  @Autowired
  private AudioCourseElementLanguageContentRepository audioCourseElementLanguageContentRepository;

  @Autowired
  private AudioGuideCourseRepository audioGuideCourseRepository;

  @Test
  public void audioGuideLanguageContent_제목_키워드포함_쿼리_테스트(){

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("1.남산")
        .language(Language.KOREAN)
        .build());
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("2.테스트")
        .language(Language.KOREAN)
        .build());
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("3.테스트")
        .language(Language.KOREAN)
        .build());
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("4.테스트 남산")
        .language(Language.KOREAN)
        .build());

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("viewCount").descending().and(Sort.by("id").ascending()));

    Page<AudioGuideLanguageContent> result = audioGuideLanguageContentRepository.findDistinctTitleAndTagsAndCourseByContaining(Language.KOREAN, "남산", pageRequest);

    assertThat(result.getContent().size()).isEqualTo(2);
  }

  @Test
  public void audioGuideTag_이름_키워드포함_테스트(){

    Tag tag1 = tagRepository.save(Tag.builder()
        .name("#남산")
        .language(Language.KOREAN)
    .build());

    Tag tag2 = tagRepository.save(Tag.builder()
        .name("#공릉")
        .language(Language.KOREAN)
        .build());

    Tag tag3 = tagRepository.save(Tag.builder()
        .name("#남산근처")
        .language(Language.KOREAN)
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(tag1)
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(tag2)
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(tag3)
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(tag1)
        .build());

    List<AudioGuideTag> result = audioGuideTagRepository.findDistinctByTagNameContaining(Language.KOREAN, "남산");

    assertThat(result.size()).isEqualTo(3);
    assertThat(result.get(2).getId()).isEqualTo(4L);
    assertThat(result.get(2).getTag().getId()).isEqualTo(1L);
  }

  @Test
  public void audioGuideLanguageContent_제목_태그_코스_키워드포함_쿼리_테스트(){

    AudioGuide guide1 = audioGuideRepository.save(AudioGuide.builder().build());
    AudioGuide guide2 = audioGuideRepository.save(AudioGuide.builder().build());

    //for guide
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("1.남산")
        .audioGuide(guide1)
        .language(Language.KOREAN)
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("2.공릉")
        .audioGuide(guide2)
        .language(Language.KOREAN)
        .build());

    //for tag
    Tag tag1 = tagRepository.save(Tag.builder()
        .name("#남산")
        .language(Language.KOREAN)
        .build());

    Tag tag2 = tagRepository.save(Tag.builder()
        .name("#공릉")
        .language(Language.KOREAN)
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(tag1)
        .audioGuide(guide1)
        .build());

    audioGuideTagRepository.save(AudioGuideTag.builder()
        .tag(tag2)
        .audioGuide(guide2)
        .build());

    //for course
    AudioCourseElement course1 = audioCourseElementRepository.save(AudioCourseElement.builder().build());
    AudioCourseElement course2 = audioCourseElementRepository.save(AudioCourseElement.builder().build());

    audioCourseElementLanguageContentRepository.save(AudioCourseElementLanguageContent.builder()
        .title("남산코스1")
        .language(Language.KOREAN)
        .audioCourseElement(course1)
        .build());

    audioCourseElementLanguageContentRepository.save(AudioCourseElementLanguageContent.builder()
        .title("남산코스2")
        .language(Language.KOREAN)
        .audioCourseElement(course2)
        .build());

    audioGuideCourseRepository.save(AudioGuideCourse.builder()
        .audioGuide(guide1)
        .audioCourseElement(course1)
        .orderNumber(1)
        .build());

    audioGuideCourseRepository.save(AudioGuideCourse.builder()
        .audioGuide(guide1)
        .audioCourseElement(course2)
        .orderNumber(2)
        .build());

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("viewCount").descending().and(Sort.by("id").ascending()));

    Page<AudioGuideLanguageContent> result = audioGuideLanguageContentRepository.findDistinctTitleAndTagsAndCourseByContaining(Language.KOREAN, "남산", pageRequest);

    assertThat(result.getContent().size()).isEqualTo(1);
    assertThat(result.getContent().get(0).getTitle().equals("1.남산"));
  }
}
