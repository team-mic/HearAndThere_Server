package team_mic.here_and_there.backend.audio_guide.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class NoCategoryParameterException extends BaseException {

  public NoCategoryParameterException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private NoCategoryParameterException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("path variable 로 오디오 가이드의 카테고리를 요청해주세요.")
        .build());
  }
}

