package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"trackOrder", "trackId", "trackLatitude", "trackLongitude"})
public class ResTrackPointDto {

  private Integer trackOrder;

  private Long trackId;

  private Double trackLatitude;

  private Double trackLongitude;
}
