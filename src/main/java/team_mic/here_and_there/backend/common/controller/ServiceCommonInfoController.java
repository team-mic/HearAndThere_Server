package team_mic.here_and_there.backend.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;
import team_mic.here_and_there.backend.common.service.ServiceCommonInfoService;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;

@Api(tags = "서비스 정보 API")
@RequiredArgsConstructor
@RestController
public class ServiceCommonInfoController {

  private final ServiceCommonInfoService serviceCommonInfoService;

  @ApiOperation(value = "서비스 앱 최신 배포 버전 정보",
      notes = "구글플레이에 배포된 최신 앱 버전을 제공합니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code= 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/service-infos/app-versions/latest")
  public ResponseEntity<String> getLatestAppVersionOfService() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(serviceCommonInfoService.getLatestAppVersionOfService());
  }

  @ApiOperation(value = "서비스 앱 최신 배포 버전 정보 업데이트",
      notes = "구글플레이에 배포된 최신 앱 버전 정보를 업데이트합니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code= 500, message = "Internal Server Error")
  })
  @PutMapping("/v1/service-infos/app-versions/latest")
  public ResponseEntity<Void> updateLatestAppVersionOfService(
      @ApiParam(value = "배포된 최신 앱 버전", required = true, example = "1.0.0")
      @RequestParam(value = "aos-app-version") String latestVersion
  ) {
    serviceCommonInfoService.updateLatestAppVersionOfService(latestVersion);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
