package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"audioGuideId", "userPresentLatitude", "userPresentLongitude",
    "isAudioTrackNearBy",
    "nearestTrackInfo"})
public class ResNearestAudioTrackDto {

  @ApiModelProperty(notes = "오디오 가이드 id")
  private Long audioGuideId;

  @ApiModelProperty(notes = "현재 사용자의 위도")
  private Double userPresentLatitude;

  @ApiModelProperty(notes = "현재 사용자의 경도")
  private Double userPresentLongitude;

  @ApiModelProperty(notes = "사용자 위치 반경 50m에 존재하는 오디오 트랙의 여부")
  private Boolean isAudioTrackNearBy;

  @ApiModelProperty(notes = "반경 50m에 존재하는 오디오 트랙의 정보")
  private ResNearestAudioTrackInfoDto nearestTrackInfo;

  @Builder
  private ResNearestAudioTrackDto(Long audioGuideId, Double userPresentLatitude,
      Double userPresentLongitude,
      Boolean isAudioTrackNearBy, ResNearestAudioTrackInfoDto nearestTrackInfo) {
    this.audioGuideId = audioGuideId;
    this.userPresentLatitude = userPresentLatitude;
    this.userPresentLongitude = userPresentLongitude;
    this.isAudioTrackNearBy = isAudioTrackNearBy;
    this.nearestTrackInfo = nearestTrackInfo;
  }
}
