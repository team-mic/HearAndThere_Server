package team_mic.here_and_there.backend.trips_tip.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class NoTripTipsException extends BaseException {

  public NoTripTipsException() {
    this(HttpStatus.NOT_FOUND);
  }

  private NoTripTipsException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("요청한 id 에 해당하는 글 콘텐츠가 존재하지 않습니다.")
        .build());
  }
}
