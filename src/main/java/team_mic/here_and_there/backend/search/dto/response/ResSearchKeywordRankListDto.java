package team_mic.here_and_there.backend.search.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"language", "count", "keywordRankList"})
public class ResSearchKeywordRankListDto {
  @ApiModelProperty(notes = "언어버전")
  private String language;
  @ApiModelProperty(notes = "순위 결과 개수")
  private Integer count;
  private List<ResSearchKeywordItemDto> keywordRankList;
}
