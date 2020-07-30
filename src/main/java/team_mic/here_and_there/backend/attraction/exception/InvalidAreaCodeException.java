package team_mic.here_and_there.backend.attraction.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class InvalidAreaCodeException extends BaseException {

  public InvalidAreaCodeException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private InvalidAreaCodeException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("유효하지 않은 지역 코드입니다.")
        .build());
  }
}
