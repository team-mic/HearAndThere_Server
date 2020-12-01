package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"language", "locationsList"})
public class ResAudioGuideLocationListDto {

  @ApiModelProperty("언어 버전")
  private String language;

  private List<ResAudioGuideLocationItemDto> guideLocationsList;
}
