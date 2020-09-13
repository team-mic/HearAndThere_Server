package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"audioGuideId", "directions"})
public class ResAudioGuideDirectionsDto {

  @ApiModelProperty(notes = "오디오 가이드 id")
  private Long audioGuideId;

  @ApiModelProperty(notes = "direction 폴리라인의 위도경도 정보 리스트")
  private List<ResDirectionDto> directions;

  @Builder
  private ResAudioGuideDirectionsDto(Long audioGuideId, List<ResDirectionDto> directions) {
    this.audioGuideId = audioGuideId;
    this.directions = directions;
  }
}
