package team_mic.here_and_there.backend.trips_tip.service;

import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;

@RequiredArgsConstructor
@Service
public class TripTipDumpService {

  private final TripTipRepository tripTipRepository;

  public void insertDumpTips() {
    tripTipRepository.save(
        TripTip.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bicycle_tip.png");
            }})
            .title("Rent a bicycle in seoul for 1$")
            .description(
                "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                    +
                    "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                    +
                    "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!")
            .build()
    );

    tripTipRepository.save(
        TripTip.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/before_a_trip.png");
            }})
            .title("Things to know before a trip #1")
            .description(
                "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                    +
                    "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                    +
                    "How's the weather in Korea? How can i use public transportation? Here's information about korea")
            .build()
    );

    tripTipRepository.save(
        TripTip.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/bicycle_tip.png");
            }})
            .title("Rent a bicycle in seoul for 1$")
            .description(
                "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                    +
                    "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!\n"
                    +
                    "If you want to rent a bike instead of public transportation or walking, try the Seoul bike!")
            .build()
    );

    tripTipRepository.save(
        TripTip.builder()
            .images(new HashSet<String>() {{
              add("https://here-and-there.s3.ap-northeast-2.amazonaws.com/audio-guides/images/before_a_trip.png");
            }})
            .title("Things to know before a trip #2")
            .description(
                "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                    +
                    "How's the weather in Korea? How can i use public transportation? Here's information about korea\n"
                    +
                    "How's the weather in Korea? How can i use public transportation? Here's information about korea")
            .build()
    );
  }
}
