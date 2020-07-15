package team_mic.here_and_there.backend.trips_tip.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ResTripTipsListDto {

    private List<ResTripTipItemDto> tripTipsList;

    @Builder
    private ResTripTipsListDto(List<ResTripTipItemDto> tripTipsList){
        this.tripTipsList=tripTipsList;
    }
}
