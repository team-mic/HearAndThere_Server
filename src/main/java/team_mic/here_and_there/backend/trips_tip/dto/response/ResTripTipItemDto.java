package team_mic.here_and_there.backend.trips_tip.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResTripTipItemDto {

    private Long tripTipId;

    private String imageUrl;

    private String title;

    private String description;

    @Builder
    private ResTripTipItemDto(Long tripTipId, String imageUrl, String title, String description){
        this.tripTipId=tripTipId;
        this.imageUrl=imageUrl;
        this.title=title;
        this.description=description;
    }
}
