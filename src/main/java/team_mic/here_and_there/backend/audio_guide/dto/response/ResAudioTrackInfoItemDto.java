package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"audioTrackId", "trackOrderNumber", "title", "audioFileUrl", "image",
    "placeName", "placeAddress",
    "runningTime"})
public class ResAudioTrackInfoItemDto {

  @ApiModelProperty(notes = "오디오 트랙의 id")
  private Long audioTrackId;

  @ApiModelProperty(notes = "오디오 트랙의 제목")
  private String title;

  @ApiModelProperty(notes = "오디오 트랙의 총 시간")
  private String runningTime;

  @ApiModelProperty(notes = "오디오 트랙 이미지")
  private String image;

  @ApiModelProperty(notes = "오디오 트랙의 오디오 파일 url")
  private String audioFileUrl;

  @ApiModelProperty(notes = "오디오 트랙의 장소 이름")
  private String placeName;

  @ApiModelProperty(notes = "오디오 트랙의 장소 주소")
  private String placeAddress;

  @ApiModelProperty(notes = "오디오 가이드 내부에서 해당 오디오 트랙의 순서 번호")
  private Integer trackOrderNumber;

  @Builder
  private ResAudioTrackInfoItemDto(Long audioTrackId, String title, String runningTime,
      String image, String audioFileUrl, String placeName, String placeAddress,
      Integer orderNumber) {
    this.audioTrackId = audioTrackId;
    this.title = title;
    this.runningTime = runningTime;
    this.image = image;
    this.audioFileUrl = audioFileUrl;
    this.placeName = placeName;
    this.placeAddress = placeAddress;
    this.trackOrderNumber = orderNumber;
  }
}
