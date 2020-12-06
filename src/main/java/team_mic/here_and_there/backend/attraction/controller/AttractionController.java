package team_mic.here_and_there.backend.attraction.controller;

import io.swagger.annotations.*;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAttractionsDetailDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResMainFixedAttractionListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResTouristAreaListDto;
import team_mic.here_and_there.backend.attraction.exception.NoAreaCodeParameterException;
import team_mic.here_and_there.backend.attraction.service.AttractionService;

import java.io.UnsupportedEncodingException;
import team_mic.here_and_there.backend.attraction.service.TouristAreaService;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;
import team_mic.here_and_there.backend.common.domain.Language;

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
    if (!language.equals(Language.KOREAN.getVersion()) && !language.equals(Language.ENGLISH.getVersion())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(touristAreaService.getTouristAreasInformation(language));
  }

  /*
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

    if(!area.equals("seoul")){
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); //TODO : custom exception
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(attractionService.getFixedMainAttractionList(area, language));
  }*/

  @ApiOperation(value = "관광지 상세 페이지 조회",
      notes = "* content-id, content-type-id 에 해당하는 관광지의 상세 페이지를 조회합니다.\n"
          +"* 제공되는 정보\n"
          + "1. detailCommonInfo : 모든 관광지들이 공통적으로 가지고 있는 기본 정보\n"
          + "2. detailIntroductionInfo : content-type-id 에 따라서 개별적으로 tour api 에서 제공하는 정보.(content-type-id 에 따라 필드가 달라집니다. 제공되는 필드 정보는 https://github.com/team-mic/HearAndThere_Server/issues/58 에서 확인 가능합니다.)"
          + "3. imagesList : tour api 에서 제공하는 관광지 이미지 + 기획상 추가된 관광지 이미지\n"
          + "4. hasRelatedAudioGuides : 관광지와 연결된 추천 오디오 가이드의 존재여부\n"
          + "5. relatedAudioGuidesCount : 관광지와 연결된 추천 오디오 가이드 개수\n"
          + "6. relatedAudioGuideLists : 관광지와 연결된 추천 오디오 가이드 정보 리스트\n"
          + "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK", response = ResAreaAttractionsListDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error"),
  })
  @GetMapping("/v1/attractions/detail")
  public ResponseEntity<ResAttractionsDetailDto> getAttractionDetail(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "관광지 content id", required = true, example = "1326972")
      @RequestParam(value = "content-id") Long contentId,
      @ApiParam(value = "관광지 content type id", required = true, example = "76")
      @RequestParam(value = "content-type-id") Integer contentTypeId)
      throws UnsupportedEncodingException {

    if (!language.equals(Language.KOREAN.getVersion()) && !language.equals(Language.ENGLISH.getVersion())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(attractionService.getAttractionDetail(contentId, contentTypeId, language));
  }

  @ApiOperation(value = "지역별 관광지 리스트",
      notes = "지역코드, 시군구코드에 해당하는 tour api 지역별 관광지 리스트를 제공합니다.\n"
          + "[param]"
          + "* page-number : (required) 페이지네이션을 제공합니다. 원하는 페이지 번호를 입력해주세요.마지막 페이지를 초과했다면 attractionsList 에 빈 리스트[] 가 들어갑니다.\n"
          + "* page-size : 한 페이지 당 관광지 데이터의 개수를 설정할 수 있습니다. 요청하지 않을 경우, 디폴트는 30 개 입니다.\n"
          + "* area-code : (required) 지역 코드입니다. 지역코드에 대한 리스트는 /tourist-areas 에서 알 수 있습니다.\n"
          + "* sigungu-area-code : 시군구 코드 입니다. 시군구 코드가 없는 경우, 요청할 필요는 없습니다. 시군구코드에 대한 리스트는 /tourist-areas 에서 알 수 있습니다.\n"
          + "[lag param 종류]\n" +
          "kor : 한국어 버전\n" +
          "eng : 영어 버전")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK", response = ResAreaAttractionsListDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error"),
      @ApiResponse(code = 404, message = "Invalid 'area-code', 'sigungu-area-code' Parameter Error")
  })
  @GetMapping("/v1/attractions")
  public ResponseEntity<ResAreaAttractionsListDto> getAreaAttractionsList(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language,
      @ApiParam(value = "페이지 번호", example = "1", required = true)
      @RequestParam(value = "page-number", defaultValue = "1") Integer pageNumber,
      @ApiParam(value = "한 페이지 당 관광지 개수", example = "30")
      @RequestParam(value = "page-size", defaultValue = "30", required = false) Integer pageSize,
      @ApiParam(value = "지역코드", required = true, example = "32")
      @RequestParam(value = "area-code") Integer areaCode,
      @ApiParam(value = "지역 시군구코드(optional)", example = "1")
      @RequestParam(value = "sigungu-area-code", required = false) Integer sigunguAreaCode)
      throws IOException {

    if (!language.equals(Language.KOREAN.getVersion()) && !language.equals(Language.ENGLISH.getVersion())) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(attractionService.getAreaAttractionsList(areaCode, sigunguAreaCode, pageNumber, pageSize, language));
  }
}
