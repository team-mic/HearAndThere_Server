package team_mic.here_and_there.backend.map_banner.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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

  @GetMapping("/v1/map-banners/area/{area-name}/zones")
  public ResponseEntity<ResMapBannerZonesListDto> getMapBannerZonesList(
      @ApiParam(value = "부모 지역명", required = true, defaultValue = "seoul", example = "seoul")
      @PathVariable(value = "area-name") String areaName,

      @ApiParam(value = "언어버전", example = "kor")
      @RequestParam(value = "lan", required = false, defaultValue = "kor") Language language,
      @ApiParam(value = "제공 데이터 타입", defaultValue = "list", example = "list")
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
