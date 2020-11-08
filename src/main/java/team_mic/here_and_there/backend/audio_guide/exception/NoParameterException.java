package team_mic.here_and_there.backend.audio_guide.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class NoParameterException extends BaseException {

  public NoParameterException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private NoParameterException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("request parameter 를 모두 요청해주세요.")
        .build());
  }
}

