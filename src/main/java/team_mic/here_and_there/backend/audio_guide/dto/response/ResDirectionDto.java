package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"latitude", "longitude"})
public class ResDirectionDto {

  @ApiModelProperty(notes = "point의 위도")
  private Double latitude;

  @ApiModelProperty(notes = "point의 경도")
  private Double longitude;

  @Builder
  private ResDirectionDto(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
