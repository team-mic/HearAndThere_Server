package team_mic.here_and_there.backend.trips_tip.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonPropertyOrder({"language", "tripTipsList"})
public class ResTripTipsListDto {

  @ApiModelProperty("언어 버전")
  private String language;

  private List<ResTripTipItemDto> tripTipsList;

  @Builder
  private ResTripTipsListDto(String language, List<ResTripTipItemDto> tripTipsList) {
    this.language = language;
    this.tripTipsList = tripTipsList;
  }
}
