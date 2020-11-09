package team_mic.here_and_there.backend.attraction.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResMainFixedAttractionListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResTouristAreaListDto;
import team_mic.here_and_there.backend.attraction.exception.NoAreaCodeParameterException;
import team_mic.here_and_there.backend.attraction.service.AttractionService;

import java.io.UnsupportedEncodingException;
import team_mic.here_and_there.backend.attraction.service.TouristAreaService;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;

@Api(tags = "관광 명소 API")
@RequiredArgsConstructor
@RestController
public class AttractionController {

  private final AttractionService attractionService;
  private final TouristAreaService touristAreaService;

  @ApiOperation(value = "메인 화면의 tourist-areas 지역별 정보 리스트",
      notes = "지역들에 대한 대표 이미지 url, 지역명, 지역코드(필수), 시군구코드(도, 특별시일 경우 null) 에 대한 리스트를 제공합니다.\n"
          + "* 지역코드와 시군구 코드는 지역기반 attraction tour api 를 호출할 때 필요한 정보입니다.\n"
          + "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @GetMapping("/v1/tourist-areas")
  public ResponseEntity<ResTouristAreaListDto> getTouristAreasInformation(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language){
    if (language == null) {
      throw new NoParameterException();
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(touristAreaService.getTouristAreasInformation(language));
  }

  @ApiOperation(value = "메인 화면의 tourist-areas 하단의 고정 fix 관광지 data",
      notes = "현재 seoul 지역의 4개의 관광지 데이터(경복궁, 롯데월드, N 서울타워, 북촌 한옥마을)만 고정으로 제공됩니다.\n"+
          "* contentId와 contentTypeId 는 상세 attraction tour api 를 호출할 때 필요한 정보입니다.\n"+
          "[area param 종류]\n"+
          "seoul : 서울 지역의 고정 관광지 4개의 데이터 리스트가 제공됩니다.\n" +
          "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @GetMapping("/v1/attractions/main")
  public ResponseEntity<ResMainFixedAttractionListDto> getFixedMainAttractionList(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "지역명", required = true, example = "seoul")
      @RequestParam(value = "area") String area){
    if (language == null || area == null) {
      throw new NoParameterException();
    }

    if(!area.equals("seoul")){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); //TODO : custom exception
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(attractionService.getFixedMainAttractionList(area, language));
  }
  /*
  @ApiOperation(value = "메인 화면의 지역별 관광명소 리스트",
      notes = "[파라미터 지역코드 종류(17개)]\n" +
          "서울 : 1, 인천 : 2, 대전 : 3, 대구 : 4, 광주 : 5, 부산 : 6\n" +
          "울산 : 7, 세종 : 8, 경기도 : 31, 강원도 : 32, 충청북도 : 33, 충청남도 : 34\n" +
          "경상북도 : 35, 경상남도 : 36, 전라북도 : 37, 전라남도 : 38, 제주도 : 39\n" +
          "현재 Tour api와 연결된 상태입니다. 지역별 10개의 관광명소 리스트를 내려줍니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK", response = ResAreaAttractionsListDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 400, message = "No 'area-code' parameter Error"),
      @ApiResponse(code = 404, message = "Invalid 'area-code' Parameter Error")
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
  }*/
}
