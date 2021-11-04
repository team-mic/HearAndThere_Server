package team_mic.here_and_there.backend.search.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class NoSearchKeywordException extends BaseException {

  public NoSearchKeywordException() {
    this(HttpStatus.NOT_FOUND);
  }

  private NoSearchKeywordException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("현재 사용자가 검색한 키워드가 0개 입니다. 더미데이터 파라미터를 추가해서 요청하세요.")
        .build());
  }
}
