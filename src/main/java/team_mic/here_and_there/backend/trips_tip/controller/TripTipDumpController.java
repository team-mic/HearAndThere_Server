package team_mic.here_and_there.backend.trips_tip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.trips_tip.service.TripTipDumpService;

@RequiredArgsConstructor
@RestController
public class TripTipDumpController {

  private final TripTipDumpService tripTipDumpService;

  @ApiIgnore
  @PostMapping("/trip-tips/dump")
  public ResponseEntity<Void> insertDumpTips() {

    tripTipDumpService.insertDumpTips();

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
