package team_mic.here_and_there.backend.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"currentPageSize", "currentPageNumber", "totalPageNumbers", "totalElements"})
public class PageMetaDataDto {
  @ApiModelProperty(notes = "요청한 현재 페이지 크기")
  private Integer currentPageSize;
  @ApiModelProperty(notes = "요청한 현재 페이지 번호")
  private Integer currentPageNumber;
  @ApiModelProperty(notes = "currentPageSize 로 동일하게 요청했을 경우 데이터가 존재하는 최대 페이지 번호")
  private Integer totalPageNumbers;
  @ApiModelProperty(notes = "결과에 해당하는 전체 데이터 개수")
  private Long totalElements;
}
