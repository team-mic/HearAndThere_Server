package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonPropertyOrder({"category", "language", "audioGuideList"})
public class ResAudioGuideListDto {

  private String category;

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
