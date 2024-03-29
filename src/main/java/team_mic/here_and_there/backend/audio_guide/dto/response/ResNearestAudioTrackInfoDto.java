package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"audioTrackId", "title", "audioFileUrl",
    "runningTime", "images", "placeName", "placeAddress"})
public class ResNearestAudioTrackInfoDto {

  @ApiModelProperty(notes = "오디오 트랙의 id")
  private Long audioTrackId;

  @ApiModelProperty(notes = "오디오 트랙의 제목")
  private String title;

  @ApiModelProperty(notes = "오디오 트랙의 총 시간")
  private String runningTime;

  @ApiModelProperty(notes = "오디오 트랙 이미지")
  private List<String> images;

  @ApiModelProperty(notes = "오디오 트랙의 오디오 파일 url")
  private String audioFileUrl;

  @ApiModelProperty(notes = "오디오 트랙의 장소 이름")
  private String placeName;

  @ApiModelProperty(notes = "오디오 트랙의 장소 주소")
  private String placeAddress;

  @Builder
  private ResNearestAudioTrackInfoDto(Long audioTrackId, String title,
      String runningTime, List<String> images,
      String audioFileUrl, String placeName, String placeAddress) {
    this.audioTrackId = audioTrackId;
    this.title = title;
    this.runningTime = runningTime;
    this.images = images;
    this.audioFileUrl = audioFileUrl;
    this.placeName = placeName;
    this.placeAddress = placeAddress;
  }
}
