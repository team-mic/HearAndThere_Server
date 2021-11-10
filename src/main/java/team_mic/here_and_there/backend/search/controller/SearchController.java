package team_mic.here_and_there.backend.search.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.common.domain.ResourceType;
import team_mic.here_and_there.backend.search.dto.response.ResPatchedSearchKeywordDto;
import team_mic.here_and_there.backend.search.dto.response.ResSearchKeywordRankListDto;
import team_mic.here_and_there.backend.search.dto.response.ResSearchResultListDto;
import team_mic.here_and_there.backend.search.service.SearchDataService;
import team_mic.here_and_there.backend.search.service.SearchService;

@Api(tags = "검색 API")
@RequiredArgsConstructor
@RestController
public class SearchController {

  private final SearchService searchService;
  private final SearchDataService searchDataService;

  @ApiOperation(value = "검색 Hit 수 업데이트",
      notes = "* 검색 탭에서 검색 결과를 클릭하여 상세페이지로 넘어갈 경우, 해당 오디오가이드 or 글콘텐츠 or 관광지의 검색 hit 수를 업데이트 합니다.\n"
          + "* type 파라미터에 따라 id 개수가 달라집니다.\n"
          + "1. type=audio-guide => id 개수 1개 요청 필수(오디오 가이드의 id)\n"
          + "2. type=trip-tip => id 개수 1개 요청 필수(글 콘텐츠의 id)\n"
          + "3. type=attraction => id 개수 2개 요청 필수(관광지 content type id, 관광지 content id - 순서대로 comma 로 분리)\n"
          + "ex) PATCH /v1/search-keywords/hit-counts?lan=kor&type=attraction&id=39,2666461\n"
          + "* 같은 type 과 id 라도 언어버전에 따라 검색 수는 독립적입니다.\n"
          + "* response 의 경우 update 완료된 검색 hit 수를 제공합니다.\n")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Invalid parameter Error"),
      @ApiResponse(code = 404, message = "No data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @PatchMapping("/v1/search-keywords/hit-counts")
  public ResponseEntity<ResPatchedSearchKeywordDto> updateSearchKeywordsHitCounts(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "검색 키워드 type", required = true, example = "audio-guide")
      @RequestParam(value = "type") String keywordType,
      @ApiParam(value = "type 별 검색 대상의 id", required = true, example = "1")
      @RequestParam(value = "id") Long[] targetIds) throws UnsupportedEncodingException {

    if (!keywordType.equals(ResourceType.AUDIO_GUIDE.getName())
        && !keywordType.equals(ResourceType.ATTRACTION.getName())
        && !keywordType.equals(ResourceType.TRIP_TIP.getName())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if (!language.equals(Language.KOREAN.getVersion()) && !language.equals(Language.ENGLISH.getVersion())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if((!keywordType.equals(ResourceType.ATTRACTION.getName()) && targetIds.length != 1)
        || (keywordType.equals(ResourceType.ATTRACTION.getName()) && targetIds.length != 2)){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(searchService.updateSearchKeywordsHitCounts(language, keywordType, targetIds));
  }

  @ApiOperation(value = "인기 검색어 순위",
      notes = "* 검색어 hit 개수에 따라 상위 count 개의 순위 결과를 제공합니다.\n"
          + "* type 파라미터에 따라 제공되는 keywordTargetIds 리스트의 사이즈가 다릅니다.\n"
          + "* 초반엔 실데이터가 없으므로, dummy=true 파라미터를 붙여서 요청하면 10개의 더미데이터를 제공받을 수 있습니다.(더미데이터의 경우 검색 hit 수가 update 되지 않습니다.)\n"
          + "1. type=audio-guide => id 개수 1개(오디오 가이드의 id)\n"
          + "2. type=trip-tip => id 개수 1개(글 콘텐츠의 id)\n"
          + "3. type=attraction => id 개수 2개(관광지 content type id, 관광지 content id - 순서대로 comma 로 분리)\n"
          + "* 언어버전에 따라 인기 검색어 순위는 독립적입니다.\n"
          + "* type=attraction 의 경우 여행팁 노션 url 을 제공합니다. 이외의 type 일 경우 null 입니다.\n"
          + "* 만약 검색 hit 수가 같다면 DB 에 저장된 순서대로 ordering 됩니다.\n")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Invalid parameter Error"),
      @ApiResponse(code = 404, message = "No data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/search-keywords/rankings/popular")
  public ResponseEntity<ResSearchKeywordRankListDto> getPopularSearchKeywordsRankings(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "순위 개수", defaultValue = "10", example = "10")
      @RequestParam(value = "count", required = false, defaultValue = "10") Integer count,
      @ApiParam(value = "더미데이터 요청", example = "true")
      @RequestParam(value = "dummy", required = false) Boolean withDummyData
      ) {
    if (!language.equals(Language.KOREAN.getVersion()) && !language.equals(Language.ENGLISH.getVersion())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if(withDummyData!=null && withDummyData == true){
      return ResponseEntity.status(HttpStatus.OK)
          .body(searchDataService.getPopularSearchKeywordsRankingsWithDummyData(language, count));
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(searchService.getPopularSearchKeywordsRankings(language, count));
  }

  @ApiOperation(value = "키워드 검색",
      notes = "* type(audio-guide, trip-tip, attraction) 에 따른 키워드 검색 결과를 제공합니다.\n"
          + "* type param 의 종류 : audio-guide, trip-tip, attraction\n"
          + "* lan param 의 종류 : eng, kor\n"
          + "* 페이지네이션 메타정보가 제공됩니다.\n"
          + "* type 에 따라 결과 List 의 element 데이터 타입이 다릅니다.\n"
          + "* type 에 따라 결과 List 의 element 데이터 타입이 다릅니다.\n"
          + "* attraction 의 경우 인기순, audio-guide,trip-tip 의 경우 조회수 순 정렬됩니다.\n")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Invalid parameter Error"),
      @ApiResponse(code = 404, message = "No data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/search")
  public ResponseEntity<ResSearchResultListDto> getSearchResult(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "응답받을 데이터 타입", required = true, example = "audio-guide")
      @RequestParam(value = "type") String type,
      @ApiParam(value = "검색키워드", required = true, example = "남산")
      @RequestParam(value = "keyword") String keyword,
      @ApiParam(value = "페이지 번호", example = "1")
      @RequestParam(value = "page-number", defaultValue = "1", required = false) Integer pageNumber,
      @ApiParam(value = "한 페이지 당 데이터 개수", example = "30")
      @RequestParam(value = "page-size", defaultValue = "30", required = false) Integer pageSize)
      throws UnsupportedEncodingException {

    if (!language.equals(Language.KOREAN.getVersion()) && !language.equals(Language.ENGLISH.getVersion())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if (!type.equals(ResourceType.AUDIO_GUIDE.getName()) && !type.equals(ResourceType.TRIP_TIP.getName()) && !type.equals(ResourceType.ATTRACTION.getName())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if(keyword.equals("")){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(searchService.getSearchResult(language, type, keyword, pageNumber, pageSize));
  }
}