package team_mic.here_and_there.backend.search;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuideLanguageContent;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideLanguageContentRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchAttraction;
import team_mic.here_and_there.backend.search.domain.entity.SearchAudioGuide;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;
import team_mic.here_and_there.backend.search.domain.entity.SearchTripTip;
import team_mic.here_and_there.backend.search.domain.repository.SearchAttractionRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchAudioGuideRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchKeywordRepository;
import team_mic.here_and_there.backend.search.domain.repository.SearchTripTipRepository;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SearchKeywordTest {
  @Autowired
  private SearchKeywordRepository searchKeywordRepository;

  @Autowired
  private SearchAudioGuideRepository searchAudioGuideRepository;

  @Autowired
  private SearchTripTipRepository searchTripTipRepository;

  @Autowired
  private SearchAttractionRepository searchAttractionRepository;

  @Autowired
  private TripTipRepository tripTipRepository;

  @Autowired
  private AudioGuideRepository audioGuideRepository;

  @Autowired
  private AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;

  @Test
  public void SearchKeyword_상속_관계_저장(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    AudioGuideLanguageContent guideLanguageContent = audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .title("test guide")
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());


    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .guideLanguageContent(guideLanguageContent)
        .build());

    SearchTripTip searchTripTip = searchTripTipRepository.save(SearchTripTip.builder()
        .language(Language.ENGLISH)
        .tripTip(tripTip)
        .build());

    SearchAttraction searchAttraction = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    SearchAudioGuide firstSearchGuide = searchAudioGuideRepository.findById(1L).get();
    List<SearchKeyword> keywords = searchKeywordRepository.findAll();

    assertThat(searchAudioGuide.getAudioGuideLanguageContent().getTitle())
        .isEqualTo(firstSearchGuide.getAudioGuideLanguageContent().getTitle());

    assertThat(firstSearchGuide.getSearchHitCounts()).isEqualTo(0);
    assertThat(keywords.size()).isEqualTo(3);
  }

  @Test
  public void SearchKeyword_검색어_순위(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    AudioGuideLanguageContent guideLanguageContent = audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .title("test guide")
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());


    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .guideLanguageContent(guideLanguageContent)
        .build());

    SearchTripTip searchTripTip = searchTripTipRepository.save(SearchTripTip.builder()
        .language(Language.ENGLISH)
        .tripTip(tripTip)
        .build());

    SearchAttraction searchAttraction = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    searchAttraction.updateSearchHitCount();
    searchAttraction.updateSearchHitCount();

    searchAudioGuide.updateSearchHitCount();

    List<SearchKeyword> keywords = searchKeywordRepository.findAllByLanguageOrderBySearchHitCountsDesc(Language.ENGLISH);

    assertThat(keywords.get(0).getClass()).isEqualTo(SearchAttraction.class);
    assertThat(keywords.get(0).getSearchHitCounts()).isEqualTo(2);
    assertThat(keywords.get(1).getSearchHitCounts()).isEqualTo(1);
    assertThat(keywords.get(2).getSearchHitCounts()).isEqualTo(0);
  }

  @Test
  public void SearchKeyword_검색어_순위_한영버전(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    AudioGuideLanguageContent guideLanguageContent = audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .title("test guide")
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());

    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.KOREAN)
        .guideLanguageContent(guideLanguageContent)
        .build());

    SearchTripTip searchTripTip = searchTripTipRepository.save(SearchTripTip.builder()
        .language(Language.KOREAN)
        .tripTip(tripTip)
        .build());

    SearchAttraction searchAttraction = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    searchAttraction.updateSearchHitCount();
    searchAttraction.updateSearchHitCount();

    searchAudioGuide.updateSearchHitCount();

    List<SearchKeyword> keywords = searchKeywordRepository.findAllByLanguageOrderBySearchHitCountsDesc(Language.KOREAN);

    assertThat(keywords.size()).isEqualTo(2);
    assertThat(keywords.get(0).getClass()).isEqualTo(SearchAudioGuide.class);
    assertThat(keywords.get(0).getSearchHitCounts()).isEqualTo(1);
    assertThat(keywords.get(1).getSearchHitCounts()).isEqualTo(0);
  }
}
