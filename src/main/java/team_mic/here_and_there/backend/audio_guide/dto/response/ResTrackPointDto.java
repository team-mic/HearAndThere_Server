package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"trackOrder", "trackId", "trackLatitude", "trackLongitude"})
public class ResTrackPointDto {

  @ApiModelProperty("가이드 내 트랙 순서")
  private Integer trackOrder;

  @ApiModelProperty("트랙 id")
  private Long trackId;

  @ApiModelProperty("트랙 위도")
  private Double trackLatitude;

  @ApiModelProperty("트랙 경도")
  private Double trackLongitude;
}
