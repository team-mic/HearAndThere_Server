package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"audioGuideId", "directions"})
public class ResAudioGuideDirectionsDto {

  private Long audioGuideId;

  private List<ResDirectionDto> directions;

  @Builder
  private ResAudioGuideDirectionsDto(Long audioGuideId, List<ResDirectionDto> directions) {
    this.audioGuideId = audioGuideId;
    this.directions = directions;
  }
}
