package team_mic.here_and_there.backend.map_banner.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.common.exception.InvalidLanguageException;
import team_mic.here_and_there.backend.map_banner.dto.response.ResMapBannerZonesListDto;
import team_mic.here_and_there.backend.map_banner.exception.InvalidZoneDataTypeException;
import team_mic.here_and_there.backend.map_banner.service.MapZoneService;

@Api(tags = "지도 배너 API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class MapZoneController {

  private final MapZoneService mapZoneService;

  @ApiOperation(value = "지도배너 지역존 데이터 리스트",
      notes = """
            * 부모 지역 (area-name path variable) 하위 지역별 배너 이미지, 지도 유입 위경도에 대한 데이터를 제공합니다.
            * 현재 부모 지역(area-name path variable)은 "seoul" 만 존재합니다.
            * 클라이언트에서의 활용성을 위한 json display data type 이 두 종류 제공됩니다.
            \n\n
            [data-type param 종류]
            list : zone 고유 key 오름차순으로 정렬 된 데이터를 리스트 타입으로 제공 (default)
            map : 데이터를 맵 타입으로 제공(key : zone 고유 key - value : 데이터 object)
            [lag param 종류]
            kor : 한국어 버전 (default)
            eng : 영어 버전
          """)
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "Invalid parameter Error"),
      @ApiResponse(code = 404, message = "No data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/map-banners/area/{area-name}/zones")
  public ResponseEntity<ResMapBannerZonesListDto> getMapBannerZonesList(
      @ApiParam(value = "부모 지역명", required = true, defaultValue = "seoul", example = "seoul")
      @PathVariable(value = "area-name") String areaName,

      @ApiParam(value = "언어버전", defaultValue = "kor", example = "kor", allowableValues = "kor,eng")
      @RequestParam(value = "lan", required = false, defaultValue = "kor") Language language,
      @ApiParam(value = "제공 json 데이터 타입", defaultValue = "list", example = "list", allowableValues = "list,map")
      @RequestParam(value = "data-type", required = false, defaultValue = "list") DisplayDataType zoneDataType){

    if(zoneDataType == null){
      throw new InvalidZoneDataTypeException();
    }

    if(language == null){
      throw new InvalidLanguageException();
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(mapZoneService.getMapBannerZonesList(language, areaName, zoneDataType));
  }
}
