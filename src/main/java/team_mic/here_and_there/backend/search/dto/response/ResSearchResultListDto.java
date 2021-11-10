package team_mic.here_and_there.backend.search.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import team_mic.here_and_there.backend.common.PageMetaDataDto;

@Builder
@Getter
@JsonPropertyOrder({"language", "searchKeyword", "type", "resultSize", "resultList", "pageMetaDataDto"})
public class ResSearchResultListDto {
  @ApiModelProperty(notes = "언어 버전")
  private String language;

  @ApiModelProperty(notes = "사용자 검색 키워드")
  private String searchKeyword;

  @ApiModelProperty(notes = "검색 결과의 type")
  private String type;

  @ApiModelProperty(notes = "검색 결과 리스트의 size")
  private Integer resultSize;

  @ApiModelProperty(notes = "검색 결과 리스트")
  private List resultList;

  @ApiModelProperty(notes = "페이지네이션 메타정보")
  private PageMetaDataDto pageMetaDataDto;
}
