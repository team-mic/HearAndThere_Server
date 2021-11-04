package team_mic.here_and_there.backend.search.dto.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"keywordType", "keywordTitle", "keywordTargetIds", "searchHitCounts", "tripTipContentsUrl"})
public class ResSearchKeywordItemDto {
  @ApiModelProperty(notes = "검색 키워드 타입")
  private String keywordType;
  @ApiModelProperty(notes = "검색 키워드 제목")
  private String keywordTitle;
  @ApiModelProperty(notes = "검색 키워드 대상의 id(type=attraction의 경우 contentTypeId, contentId 순서)")
  private List<Long> keywordTargetIds;
  @ApiModelProperty(notes = "검색 hit 수")
  private Long searchHitCounts;
  @ApiModelProperty(notes = "type=attraction 의 경우 제공되는 여행팁의 노션 url")
  private String tripTipContentsUrl;
}
