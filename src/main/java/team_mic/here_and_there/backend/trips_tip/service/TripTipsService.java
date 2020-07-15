package team_mic.here_and_there.backend.trips_tip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipItemDto;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipsListDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TripTipsService {

    private final TripTipRepository tripTipRepository;

    public ResTripTipsListDto getTripTipsList() {

        List<TripTip> tipsList = tripTipRepository.findTop4ByOrderByCreatedTimeDesc();
        List<ResTripTipItemDto> itemList =tipsList.parallelStream().map(tripTip -> ResTripTipItemDto.builder()
                                                    .tripTipId(tripTip.getId())
                                                    .imageUrl(tripTip.getImages().iterator().next())
                                                    .title(tripTip.getTitle())
                                                    .description(tripTip.getDescription())
                                                    .build())
                    .collect(Collectors.toList());

        return ResTripTipsListDto.builder().tripTipsList(itemList).build();
    }
}
