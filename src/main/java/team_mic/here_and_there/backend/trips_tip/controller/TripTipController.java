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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import team_mic.here_and_there.backend.audio_guide.exception.NoParameterException;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResPatchedSingleTripTipDto;
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

  @ApiOperation(value = "글 콘텐츠를 클릭할 경우, 조회수 업데이트",
      notes = "* 글 콘텐츠를 클릭하여 상세보기로 넘어갈 경우, 해당 id 의 글콘텐츠 조회수를 업데이트 합니다.\n"
          + "* 글 콘텐츠의 경우 update-field param이 viewcount 이어야 합니다.\n"
          + "* 반드시 PATCH로 요청해주세요. response 의 경우 update 완료된 조회수를 제공합니다.\n"
          + "ex) PATCH /v1/trip-tips/1?update-field=viewcount")
  @ApiResponses({
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 400, message = "No parameter Error"),
      @ApiResponse(code = 404, message = "No Trip tips data in DB"),
      @ApiResponse(code = 500, message = "Internal Server Error")
  })
  @PatchMapping("/v1/trip-tips/{trip-tips-id:^[0-9]+$}")
  public ResponseEntity<ResPatchedSingleTripTipDto> updateTripTipViewCount(
      @PathVariable(value = "trip-tips-id") Long tripTipsId,
      @ApiParam(value = "update 할 필드(ex 조회수)", required = true, example = "kor")
      @RequestParam(value = "update-field") String updateField){
    if (tripTipsId == null || !updateField.equals("viewcount")) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST); //TODO : custom exception
    }

    return ResponseEntity.status(HttpStatus.OK).body(tipsService.updateTripTipViewCount(tripTipsId));
  }
}
