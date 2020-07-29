package team_mic.here_and_there.backend.trips_tip.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;
import team_mic.here_and_there.backend.trips_tip.service.TripTipsService;

import java.util.HashSet;
import java.util.Set;

@Api(tags = "여행 팁 API")
@RequiredArgsConstructor
@RestController
public class TripTipController {

  private final TripTipsService tipsService;

  private final TripTipRepository tripTipRepository;

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

  @ApiIgnore
  @PostMapping("/trip-tips/dump")
  public ResponseEntity<Void> addDumpData() {
    Set<String> images = new HashSet<>();
    images.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bicycle_tip.png");
    TripTip tripTip = TripTip.builder()
        .images(images)
        .title("Rent a bicycle in seoul for 1$")
        .description(
            "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                +
                "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                +
                "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!")
        .build();

    Set<String> images2 = new HashSet<>();
    images2.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/before_a_trip.png");
    TripTip tripTip2 = TripTip.builder()
        .images(images2)
        .title("Things to know before a trip")
        .description(
            "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                +
                "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                +
                "How's the weather in Korea? How can i use public transportation? Here's information about korea")
        .build();

    Set<String> images3 = new HashSet<>();
    images3.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bicycle_tip.png");
    TripTip tripTip3 = TripTip.builder()
        .images(images3)
        .title("Rent a bicycle in seoul for 1$")
        .description(
            "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                +
                "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                +
                "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!")
        .build();

    Set<String> images4 = new HashSet<>();
    images4.add(
        "https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/before_a_trip.png");
    TripTip tripTip4 = TripTip.builder()
        .images(images4)
        .title("Things to know before a trip")
        .description(
            "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                +
                "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                +
                "How's the weather in Korea? How can i use public transportation? Here's information about korea")
        .build();

    tripTipRepository.save(tripTip);
    tripTipRepository.save(tripTip2);
    tripTipRepository.save(tripTip3);
    tripTipRepository.save(tripTip4);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
