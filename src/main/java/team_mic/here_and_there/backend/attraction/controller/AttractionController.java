package team_mic.here_and_there.backend.attraction.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;
import team_mic.here_and_there.backend.attraction.exception.NoAreaCodeParameterException;
import team_mic.here_and_there.backend.attraction.service.AttractionService;

import java.io.UnsupportedEncodingException;

@Api(tags = "관광 명소 API")
@RequiredArgsConstructor
@RestController
public class AttractionController {

  private final AttractionService attractionService;

  @ApiOperation(value = "메인 화면의 지역별 관광명소 리스트",
      notes = "[파라미터 지역코드 종류(17개)]\n" +
          "서울 : 1, 인천 : 2, 대전 : 3, 대구 : 4, 광주 : 5, 부산 : 6\n" +
          "울산 : 7, 세종 : 8, 경기도 : 31, 강원도 : 32, 충청북도 : 33, 충청남도 : 34\n" +
          "경상북도 : 35, 경상남도 : 36, 전라북도 : 37, 전라남도 : 38, 제주도 : 39\n" +
          "현재 Tour api와 연결된 상태입니다. 지역별 10개의 관광명소 리스트를 내려줍니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK", response = ResAreaAttractionsListDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "Invalid 'area-code' Parameter Error")
  })
  @GetMapping("/attractions")
  public ResponseEntity<ResAreaAttractionsListDto> getAreaAttractions(
      @ApiParam(value = "관광지 지역코드(17개 중 하나)", required = true, example = "1")
      @RequestParam(value = "area-code") Integer areaCode) throws UnsupportedEncodingException {
    if (areaCode == null) {
      throw new NoAreaCodeParameterException();
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(attractionService.getAreaAttractions(areaCode));
  }
}
