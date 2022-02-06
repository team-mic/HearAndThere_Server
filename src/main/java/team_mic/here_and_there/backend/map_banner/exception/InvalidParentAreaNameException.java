package team_mic.here_and_there.backend.map_banner.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class InvalidParentAreaNameException extends BaseException {

  public InvalidParentAreaNameException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private InvalidParentAreaNameException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("존재하지 않는 지역명입니다. 현재 'seoul' 지도 배너만 가능합니다.")
        .build());
  }
}
