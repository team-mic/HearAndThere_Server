package team_mic.here_and_there.backend.search;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
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

  @Test
  public void SearchKeyword_상속_관계_저장(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());


    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .audioGuide(audioGuide)
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

    assertEquals(searchAudioGuide.getAudioGuide().getId(), firstSearchGuide.getAudioGuide().getId());

    assertEquals(firstSearchGuide.getSearchHitCounts(), 0);
    assertEquals(keywords.size(),3);
  }

  @Test
  public void SearchKeyword_검색어_순위(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());


    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .audioGuide(audioGuide)
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

    assertEquals(keywords.get(0).getClass(), SearchAttraction.class);
    assertEquals(keywords.get(0).getSearchHitCounts(),2);
    assertEquals(keywords.get(1).getSearchHitCounts(), 1);
    assertEquals(keywords.get(2).getSearchHitCounts(),0);
  }

  @Test
  public void SearchKeyword_검색어_순위_한영버전(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());

    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.KOREAN)
        .audioGuide(audioGuide)
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

    assertEquals(keywords.size(),2);
    assertEquals(keywords.get(0).getClass(), SearchAudioGuide.class);
    assertEquals(keywords.get(0).getSearchHitCounts(), 1);
    assertEquals(keywords.get(1).getSearchHitCounts(), 0);
  }

  @Test
  public void findByDiscriminatorColumn_테스트(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());

    searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.KOREAN)
        .audioGuide(audioGuide)
        .build());

    assertEquals(searchAudioGuideRepository.findAllByType("audio-guide").get(0).getId(), 1L);
  }

  @Test
  public void 검색어랭킹_PageRequest_테스트(){
    SearchAttraction searchAttraction1 = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    SearchAttraction searchAttraction2 = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(2L)
        .contentTypeId(2)
        .build());

    SearchAttraction searchAttraction3 = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(3L)
        .contentTypeId(3)
        .build());

    searchAttraction1.updateSearchHitCount();
    searchAttraction2.updateSearchHitCount();
    searchAttraction2.updateSearchHitCount();

    PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("searchHitCounts").descending());

    List<SearchKeyword> searchKeywords = searchKeywordRepository.findAllByLanguage(Language.ENGLISH, pageRequest);

    assertEquals(searchKeywords.size(), 3);
    assertEquals(searchKeywords.get(0).getSearchHitCounts(), 2L);
    assertEquals(searchKeywords.get(2).getId(), 3L);
  }

  @Test
  public void 검색어랭킹_PageRequest_다른Entity_다른언어버전_테스트(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());

    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .audioGuide(audioGuide)
        .build());

    SearchAttraction searchAttraction = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    SearchTripTip searchTripTip = searchTripTipRepository.save(SearchTripTip.builder()
        .language(Language.KOREAN)
        .tripTip(tripTip)
        .build());

    searchAudioGuide.updateSearchHitCount();
    searchAttraction.updateSearchHitCount();
    searchAttraction.updateSearchHitCount();

    PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("searchHitCounts").descending());

    List<SearchKeyword> searchKeywords = searchKeywordRepository.findAllByLanguage(Language.ENGLISH, pageRequest);

    assertEquals(searchKeywords.size(), 2);
    assertEquals(searchKeywords.get(0).getDiscriminatorValue(), "attraction");
    assertEquals(searchKeywords.get(1).getId(), 1L);
    assertEquals(searchKeywords.get(1).getDiscriminatorValue(), "audio-guide");
  }

  @Test
  public void searchKeyword_total_entity_개수_테스트(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());

    searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .audioGuide(audioGuide)
        .build());

    searchTripTipRepository.save(SearchTripTip.builder()
        .language(Language.KOREAN)
        .tripTip(tripTip)
        .build());

    assertEquals(searchKeywordRepository.getTotalCountsByLanguage(Language.ENGLISH), 3L);
  }

  @Test
  public void 검색어랭킹_같다면_Id순정렬_테스트(){
    AudioGuide audioGuide = audioGuideRepository.save(AudioGuide.builder()
        .build());
    TripTip tripTip = tripTipRepository.save(TripTip.builder()
        .title("test trip tip")
        .build());

    SearchAudioGuide searchAudioGuide = searchAudioGuideRepository.save(SearchAudioGuide.builder()
        .language(Language.ENGLISH)
        .audioGuide(audioGuide)
        .build());

    SearchAttraction searchAttraction = searchAttractionRepository.save(SearchAttraction.builder()
        .language(Language.ENGLISH)
        .contentId(1L)
        .contentTypeId(1)
        .build());

    SearchTripTip searchTripTip = searchTripTipRepository.save(SearchTripTip.builder()
        .language(Language.ENGLISH)
        .tripTip(tripTip)
        .build());

    searchAudioGuide.updateSearchHitCount();
    searchAttraction.updateSearchHitCount();
    searchTripTip.updateSearchHitCount();

    PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("searchHitCounts").descending().and(Sort.by("id")));

    List<SearchKeyword> searchKeywords = searchKeywordRepository.findAllByLanguage(Language.ENGLISH, pageRequest);

    assertEquals(searchKeywords.get(0).getDiscriminatorValue(), "audio-guide");
    assertEquals(searchKeywords.get(1).getDiscriminatorValue(), "attraction");
    assertEquals(searchKeywords.get(2).getDiscriminatorValue(), "trip-tip");
  }
}
