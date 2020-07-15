package team_mic.here_and_there.backend.trips_tip.dto.response;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResTripTipItemDto {

    @ApiModelProperty(notes = "여행 팁 id")
    private Long tripTipId;

    @ApiModelProperty(notes = "여행 팁 메인 이미지 url")
    private String imageUrl;

    @ApiModelProperty(notes = "여행 팁 제목")
    private String title;

    @ApiModelProperty(notes = "여행 팁 내용")
    private String description;

    @Builder
    private ResTripTipItemDto(Long tripTipId, String imageUrl, String title, String description){
        this.tripTipId=tripTipId;
        this.imageUrl=imageUrl;
        this.title=title;
        this.description=description;
    }
}
