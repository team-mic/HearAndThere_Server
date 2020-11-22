package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"audioGuideId", "language", "updatedField", "guideViewCount", "guidePlayCount"})
public class ResPatchedSingleAudioGuideDto {

  @ApiModelProperty(notes = "업데이트 된 가이드 id")
  private Long audioGuideId;

  @ApiModelProperty(notes = "업데이트 된 가이드 언어 정보")
  private String language;

  @ApiModelProperty(notes = "업데이트 된 필드(viewcount 혹은 playcount)")
  private String updatedField;

  @ApiModelProperty(notes = "해당 가이드의 조회수")
  private String guideViewCount;

  @ApiModelProperty(notes = "해당 가이드의 재생수")
  private String guidePlayCount;
}
