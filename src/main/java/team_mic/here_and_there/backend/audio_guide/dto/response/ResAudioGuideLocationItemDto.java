package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"audioGuideId", "firstTrackLatitude", "firstTrackLongitude",
    "guideTitle", "guideImageUrl", "guideDistance", "guideExpectedTravelTime", "guideLocation"})
public class ResAudioGuideLocationItemDto {

  @ApiModelProperty("가이드 id")
  private Long audioGuideId;

  @ApiModelProperty("가이드의 첫 트랙 위도")
  private Double firstTrackLatitude;

  @ApiModelProperty("가이드의 첫 트랙 경도")
  private Double firstTrackLongitude;

  @ApiModelProperty("가이드 title")
  private String guideTitle;

  @ApiModelProperty("가이드의 대표 이미지 url")
  private String guideImageUrl;

  @ApiModelProperty("가이드의 총 여행 거리")
  private String guideDistance;

  @ApiModelProperty("가이드의 총 여행 예상 시간")
  private String guideEstimatedTravelTime;

  @ApiModelProperty("가이드의 지역")
  private String guideLocation;
}
