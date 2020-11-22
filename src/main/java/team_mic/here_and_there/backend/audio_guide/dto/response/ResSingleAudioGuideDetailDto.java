package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import team_mic.here_and_there.backend.audio_course.dto.response.ResAudioCourseInfoItemDto;
import team_mic.here_and_there.backend.trips_tip.dto.response.ResTripTipItemDto;

@Getter
@Builder
@JsonPropertyOrder({"language", "audioGuideId", "title", "location", "distance",
    "estimatedTravelTime", "tags", "guideImages", "overviewDescription",
    "tracksList", "coursesList", "recommendedAudioGuidesList", "recommendedContentsList"})
public class ResSingleAudioGuideDetailDto {

  @ApiModelProperty(value = "언어 버전")
  private String language;

  @ApiModelProperty(value = "오디오 가이드 id")
  private Long audioGuideId;

  //가이드 기본 정보
  @ApiModelProperty(value = "오디오 가이드 제목")
  private String title;

  @ApiModelProperty(value = "오디오 가이드 지역 위치 정보")
  private String location;

  @ApiModelProperty(value = "가이드 총 거리")
  private String distance;

  @ApiModelProperty(value = "가이드 예상 소요 시간")
  private String estimatedTravelTime;

  @ApiModelProperty(value = "오디오 가이드 태그 리스트")
  private List<String> tags;

  @ApiModelProperty(value = "오디오 가이드 이미지 url 리스트")
  private List<String> guideImages;

  @ApiModelProperty(value = "오디오 가이드 overview 내용")
  private String overviewDescription;

  //트랙 리스트
  @ApiModelProperty(value = "오디오 가이드 트랙 리스트")
  private List<ResAudioTrackInfoItemDto> tracksList;

  //코스 리스트 + 연결 관광지
  @ApiModelProperty(value = "오디오 가이드 코스 리스트")
  private List<ResAudioCourseInfoItemDto> coursesList;

  //추천 가이드 리스트
  @ApiModelProperty(value = "해당 오디오 가이드의 추천 오디오 가이드 리스트")
  private List<ResAudioGuideItemDto> recommendedAudioGuidesList;

  //추천 글콘텐츠
  @ApiModelProperty(value = "오디오 가이드 추천 글 콘텐츠 리스트")
  private List<ResTripTipItemDto> recommendedContentsList;
}
