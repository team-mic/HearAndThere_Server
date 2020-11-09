package team_mic.here_and_there.backend.trips_tip.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;
import team_mic.here_and_there.backend.trips_tip.service.TripTipsService;

@Api(tags = "여행 팁 API")
@RequiredArgsConstructor
@RestController
public class TripTipController {

  private final TripTipsService tipsService;

  @ApiOperation(value = "메인 화면 하단의 대표 2개의 글 콘텐츠 리스트",
      notes = "* 메인 화면 하단에 삽입되는 2개의 고정 글 콘텐츠가 제공됩니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "No parameter Error"),
      @ApiResponse(code = 404, message = "No Trip tips data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/trip-tips/main")
  public ResponseEntity<ResTripTipsListDto> getMainFixedTripTipsList(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language
  ) {
    if (language == null) {
      throw new NoParameterException();
    }

    return ResponseEntity.status(HttpStatus.OK).body(tipsService.getMainFixedTripTipsList(language));
  }

  @ApiOperation(value = "메인 화면 하단의 글 콘텐츠 리스트 모두 보기",
      notes = "* 메인화면 하단의 글 콘텐츠 view all 을 클릭할 경우, 모든 글 콘텐츠가 조회수 순으로 제공됩니다.")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "No parameter Error"),
      @ApiResponse(code = 404, message = "No Trip tips data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @GetMapping("/v1/trip-tips")
  public ResponseEntity<ResTripTipsListDto> getAllTripTipsListOrderByViewCount(
      @ApiParam(value = "언어버전", required = true, example = "kor")
      @RequestParam(value = "lan") String language
  ) {
    if (language == null) {
      throw new NoParameterException();
    }
    return ResponseEntity.status(HttpStatus.OK).body(tipsService.getAllTripTipsListOrderByViewCount(language));
  }
}
