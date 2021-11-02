package team_mic.here_and_there.backend.search.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.search.dto.response.ResPatchedSearchKeywordDto;
import team_mic.here_and_there.backend.search.service.SearchService;

@Api(tags = "검색 API")
@RequiredArgsConstructor
@RestController
public class SearchController {

  private final SearchService searchService;

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

    if (!keywordType.equals("audio-guide") && !keywordType.equals("trip-tip") && !keywordType.equals("attraction")) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if (!language.equals("kor") && !language.equals("eng")) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    if((!keywordType.equals("attraction") && targetIds.length != 1) || (keywordType.equals("attraction") && targetIds.length != 2)){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(searchService.updateSearchKeywordsHitCounts(language, keywordType, targetIds));
  }
}