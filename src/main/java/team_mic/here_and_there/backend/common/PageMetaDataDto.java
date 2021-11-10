package team_mic.here_and_there.backend.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageMetaDataDto {
  private Integer currentPageSize;
  private Integer currentPageNumber;
  private Integer totalPageNumbers;
  private Long totalElements;
}
