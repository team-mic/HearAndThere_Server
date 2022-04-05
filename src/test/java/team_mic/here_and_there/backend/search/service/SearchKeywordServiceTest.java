package team_mic.here_and_there.backend.search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

import java.util.List;
import javax.persistence.Index;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.search.domain.entity.SearchAttraction;
import team_mic.here_and_there.backend.search.domain.entity.SearchAudioGuide;
import team_mic.here_and_there.backend.search.domain.entity.SearchKeyword;
import team_mic.here_and_there.backend.search.domain.repository.SearchKeywordRepository;
import team_mic.here_and_there.backend.search.dto.response.ResSearchKeywordRankListDto;

@ExtendWith(MockitoExtension.class)
public class SearchKeywordServiceTest {

  @Mock
  private SearchKeywordRepository searchKeywordRepository;

  @InjectMocks
  private SearchService searchService;

  @Mock
  private SearchDataService searchDataService;

  @Test
  public void 배포_초기_데이터가_countParam_미만일경우_더미데이터제공_테스트(){
    //given
    Language givenLanguage = Language.KOREAN;
    Integer givenCountParam = 10;

    given(searchKeywordRepository.getTotalCountsByLanguage(givenLanguage))
        .willReturn(2L);

    //when
    ResSearchKeywordRankListDto rankListDto = searchService.getPopularSearchKeywordsRankings(givenLanguage, givenCountParam);

    //then
    then(searchKeywordRepository)
        .should(never())
        .findAllByLanguage(any(Language.class), any(PageRequest.class));

    then(searchDataService)
        .should()
        .getPopularSearchKeywordsRankingsWithDummyData(givenLanguage, givenCountParam);
  }

  @Test
  public void 데이터가_countParam_이상일경우_실제데이터제공_테스트(){
    //given
    Language givenLanguage = Language.KOREAN;
    Integer givenCountParam = 2;
    PageRequest givenPageRequest = PageRequest.of(0, givenCountParam, Sort.by("searchHitCounts").descending().and(Sort.by("id").ascending()));

    given(searchKeywordRepository.getTotalCountsByLanguage(givenLanguage))
        .willReturn(2L);

    given(searchKeywordRepository.findAllByLanguage(givenLanguage, givenPageRequest))
        .willReturn(List.of(
            SearchAttraction.builder()
                    .language(givenLanguage)
                    .contentId(1L)
                    .contentTypeId(1)
                    .title("test1").build(),
            SearchAttraction.builder()
                .language(givenLanguage)
                .contentId(1L)
                .contentTypeId(2)
                .title("test2").build()));

    //when
    ResSearchKeywordRankListDto rankListDto = searchService.getPopularSearchKeywordsRankings(givenLanguage, givenCountParam);

    //then
    then(searchDataService)
        .should(never())
        .getPopularSearchKeywordsRankingsWithDummyData(givenLanguage, givenCountParam);

    then(searchKeywordRepository)
        .should()
        .findAllByLanguage(givenLanguage, givenPageRequest);

    assertEquals(false, rankListDto.getIsDummyData());
    assertEquals(2, rankListDto.getCount());
  }
}
