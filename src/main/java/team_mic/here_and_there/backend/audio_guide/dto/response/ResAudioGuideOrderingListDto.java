package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"language", "orderBy", "guideCounts", "audioGuideList"})
public class ResAudioGuideOrderingListDto {

  @ApiModelProperty(value = "언어 버전")
  private String language;

  @ApiModelProperty(value = "정렬 기준")
  private String orderBy;

  @ApiModelProperty(value = "response 에 포함된 오디오 가이드 개수")
  private Integer guideCounts;

  private List<ResAudioGuideItemDto> audioGuideList;

  @Builder
  private ResAudioGuideOrderingListDto(String language, String orderBy, Integer guideCounts,
      List<ResAudioGuideItemDto> audioGuideList){
    this.language = language;
    this.orderBy = orderBy;
    this.guideCounts = guideCounts;
    this.audioGuideList = audioGuideList;
  }
}
