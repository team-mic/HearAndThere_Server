package team_mic.here_and_there.backend.trips_tip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;
import team_mic.here_and_there.backend.trips_tip.service.TripTipsService;

@RequiredArgsConstructor
@RestController
public class TripTipController {

    private final TripTipsService tipsService;

    @GetMapping("/trip-tips")
    ResponseEntity<ResTripTipsListDto> getTripTipsList(){
        return ResponseEntity.status(HttpStatus.OK).body(tipsService.getTripTipsList());
    }
}
