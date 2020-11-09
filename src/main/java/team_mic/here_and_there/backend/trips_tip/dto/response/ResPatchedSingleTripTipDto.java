package team_mic.here_and_there.backend.trips_tip.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"tripTipsId", "updatedViewCount"})
public class ResPatchedSingleTripTipDto {

  @ApiModelProperty(notes = "조회수가 업데이트 된 글 콘텐츠 id")
  private Long tripTipsId;

  @ApiModelProperty(notes = "업데이트된 조회수 결과")
  private String updatedViewCount;

  @Builder
  private ResPatchedSingleTripTipDto(Long tripTipsId, String updatedViewCount){
    this.tripTipsId = tripTipsId;
    this.updatedViewCount = updatedViewCount;
  }
}
