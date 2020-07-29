package team_mic.here_and_there.backend.audio_guide.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResAudioTrackInfoItemDto {

  private Long audioTrackId;

  private String title;

  private String runningTime;

  private String image;

  private String audioFileUrl;

  private String placeName;

  private String placeAddress;

  @Builder
  private ResAudioTrackInfoItemDto(Long audioTrackId, String title, String runningTime,
      String image, String audioFileUrl, String placeName, String placeAddress) {
    this.audioTrackId = audioTrackId;
    this.title = title;
    this.runningTime = runningTime;
    this.image = image;
    this.audioFileUrl = audioFileUrl;
    this.placeName = placeName;
    this.placeAddress = placeAddress;
  }
}
