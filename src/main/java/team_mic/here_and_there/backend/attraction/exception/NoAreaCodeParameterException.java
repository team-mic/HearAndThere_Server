package team_mic.here_and_there.backend.attraction.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class NoAreaCodeParameterException extends BaseException {

  public NoAreaCodeParameterException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private NoAreaCodeParameterException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("파라미터로 지역코드를 요청해주세요.")
        .build());
  }
}
