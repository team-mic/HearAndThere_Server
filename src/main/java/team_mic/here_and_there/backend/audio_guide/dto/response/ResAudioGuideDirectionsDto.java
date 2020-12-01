package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"audioGuideId", "trackPoints", "directions"})
public class ResAudioGuideDirectionsDto {

  @ApiModelProperty(notes = "오디오 가이드 id")
  private Long audioGuideId;

  @ApiModelProperty(notes = "가이드의 각 트랙 point 들의 위도경도 리스트")
  private List<ResTrackPointDto> trackPoints;

  @ApiModelProperty(notes = "direction 폴리라인의 위도경도 정보 리스트")
  private List<ResDirectionDto> directions;

  @Builder
  private ResAudioGuideDirectionsDto(Long audioGuideId, List<ResTrackPointDto> trackPoints, List<ResDirectionDto> directions) {
    this.audioGuideId = audioGuideId;
    this.trackPoints = trackPoints;
    this.directions = directions;
  }
}
