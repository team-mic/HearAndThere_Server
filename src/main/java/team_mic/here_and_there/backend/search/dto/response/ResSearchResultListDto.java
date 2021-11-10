package team_mic.here_and_there.backend.search.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import team_mic.here_and_there.backend.common.PageMetaDataDto;

@Builder
@Getter
@JsonPropertyOrder({"language", "searchKeyword", "type", "resultSize", "resultList", "pageMetaDataDto"})
public class ResSearchResultListDto {
  private String language;
  private String searchKeyword;
  private String type;

  private Integer resultSize;
  private List resultList;

  private PageMetaDataDto pageMetaDataDto;
}
