package team_mic.here_and_there.backend.audio_course.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"audioCourseId", "title", "estimatedTravelTime",
    "courseOrderNumber", "hasRelatedAttraction", "relatedTourApiAttractionContentId",
    "relatedTourApiAttractionContentTypeId", "courseImages"})
public class ResAudioCourseInfoItemDto {

  @ApiModelProperty(value = "코스 요소의 아이디")
  private Long audioCourseId;

  @ApiModelProperty(value = "코스 제목")
  private String title;

  @ApiModelProperty(value = "코스 예상 소요 시간")
  private String estimatedTravelTime;

  @ApiModelProperty(value = "오디오 가이드 내 해당 코스 요소의 순서")
  private Integer courseOrderNumber;

  @ApiModelProperty(value = "코스 요소에 연결된 tour api 관광지의 존재 여부")
  private Boolean hasRelatedAttraction;

  @ApiModelProperty(value = "관련 tour api 관광지의 content id")
  private Long relatedTourApiAttractionContentId;

  @ApiModelProperty(value = "관련 tour api 관광지의 content type id")
  private Integer relatedTourApiAttractionContentTypeId;

  @ApiModelProperty(value = "코스 이미지 url 리스트")
  private List<String> courseImages;
}
