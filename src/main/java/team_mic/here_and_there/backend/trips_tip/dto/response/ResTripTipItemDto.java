package team_mic.here_and_there.backend.trips_tip.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"tripTipId", "title", "thumbnailImageUrl", "thumbnailDescription", "contentsUrl"})
public class ResTripTipItemDto {

  @ApiModelProperty(notes = "글 콘텐츠 id")
  private Long tripTipId;

  @ApiModelProperty(notes = "글 콘텐츠 썸네일 이미지 url")
  private String thumbnailImageUrl;

  @ApiModelProperty(notes = "글 콘텐츠 제목")
  private String title;

  @ApiModelProperty(notes = "글 콘텐츠 썸네일 내용")
  private String thumbnailDescription;

  @ApiModelProperty(notes = "글 콘텐츠 노션 url")
  private String contentsUrl;

  @Builder
  private ResTripTipItemDto(Long tripTipId, String thumbnailImageUrl, String title,
      String thumbnailDescription, String contentsUrl) {
    this.tripTipId = tripTipId;
    this.thumbnailImageUrl = thumbnailImageUrl;
    this.title = title;
    this.thumbnailDescription = thumbnailDescription;
    this.contentsUrl = contentsUrl;
  }
}
