package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonPropertyOrder({"category", "language", "audioGuideList"})
public class ResAudioGuideListDto {

  @ApiModelProperty("오디오 가이드의 카테고리")
  private String category;

  @ApiModelProperty("언어 버전")
  private String language;

  private List<ResAudioGuideItemDto> audioGuideList;

  @Builder
  private ResAudioGuideListDto(String category, String language,
      List<ResAudioGuideItemDto> audioGuideList) {
    this.category = category;
    this.language = language;
    this.audioGuideList = audioGuideList;
  }
}
