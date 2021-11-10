package team_mic.here_and_there.backend.search;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.location_tag.domain.repository.TagRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchResultTest {

  @Autowired
  private AudioGuideTagRepository audioGuideTagRepository;

  @Autowired
  private TagRepository tagRepository;

  @Autowired
  private AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;

  @Test
  public void audioGuideLanguageContent_제목_설명_키워드포함_쿼리_테스트(){

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("1.남산")
        .language(Language.KOREAN)
        .build());
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("2.테스트")
        .language(Language.KOREAN)
        .overviewDescription("여긴 남산입니다.")
        .build());
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("3.테스트")
        .language(Language.KOREAN)
        .build());
    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .title("4.테스트 남산")
        .language(Language.KOREAN)
        .overviewDescription("여기도 남산")
        .build());

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("viewCount").descending().and(Sort.by("id").ascending()));

    Page<AudioGuideLanguageContent> result = audioGuideLanguageContentRepository.findDistinctTitleAndTagsByContaining(Language.KOREAN, "남산", pageRequest);

    assertThat(result.getContent().size()).isEqualTo(3);
    assertThat(result.getContent().get(1).getTitle()).isEqualTo("2.테스트");
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
  public void 문자열대소비교_테스트(){
    AudioGuideLanguageContent languageContent1 = AudioGuideLanguageContent.builder()
        .viewCount("11")
        .build();
    AudioGuideLanguageContent languageContent2 = AudioGuideLanguageContent.builder()
        .viewCount("11")
        .build();

    List<AudioGuideLanguageContent> list = new ArrayList<>();
    list.add(languageContent1);
    list.add(languageContent2);

    list = list.stream()
        .sorted((a1, a2)-> {
          long al1 = Long.parseLong(a1.getViewCount());
          long al2 = Long.parseLong(a2.getViewCount());

          return Long.compare(al2, al1);
        })
        .collect(Collectors.toList());

    assertThat(list.get(1).getViewCount()).isEqualTo("11");
  }
}
