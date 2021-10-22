package team_mic.here_and_there.backend.audio_guide.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonPropertyOrder({"category", "language", "categoryMainGreeting", "categoryMainImage", "audioGuideList"})
public class ResAudioGuideSubCategoryDetailDto {

  @ApiModelProperty("카테고리 이름")
  private String category;

  @ApiModelProperty("언어 버전")
  private String language;

  @ApiModelProperty("카테고리 테마 인삿말")
  private String categoryMainGreeting;

  @ApiModelProperty(value = "카테고리 테마 대표 오디오 가이드 이미지 url")
  private String categoryMainImage;

  private List<ResAudioGuideItemDto> audioGuideList;
}
