package team_mic.here_and_there.backend.trips_tip.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;
import team_mic.here_and_there.backend.trips_tip.service.TripTipsService;

@Api(tags = "여행 팁 API")
@RequiredArgsConstructor
@RestController
public class TripTipController {

  private final TripTipsService tipsService;

  @ApiOperation(value = "메인 화면 하단의 여행 팁 리스트",
      notes = "현재 4개의 여행 팁 덤프 데이터가 내려옵니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/trip-tips")
  public ResponseEntity<ResTripTipsListDto> getTripTipsList() {
    return ResponseEntity.status(HttpStatus.OK).body(tipsService.getTripTipsList());
  }
}
