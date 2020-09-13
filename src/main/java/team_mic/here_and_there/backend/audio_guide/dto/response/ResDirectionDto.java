package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"latitude", "longitude"})
public class ResDirectionDto {

  private Double latitude;

  private Double longitude;

  @Builder
  private ResDirectionDto(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
