package team_mic.here_and_there.backend.trips_tip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.trips_tip.service.TripTipDataService;

@RequiredArgsConstructor
@RestController
public class TripTipDataController{

  private final TripTipDataService tripTipDataService;

  @ApiIgnore
  @PostMapping("/data/v1/trip-tips")
  public ResponseEntity<Void> insertTripTipsVersion1() {

    tripTipDataService.insertTripTips();

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiIgnore
  @PostMapping("/data/v2/trip-tips")
  public ResponseEntity<Void> insertTripTipsVersion2() {

    tripTipDataService.insertTripTipsVersion2();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
