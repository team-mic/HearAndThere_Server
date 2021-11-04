package team_mic.here_and_there.backend.attraction.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class InvalidAttractionIdException extends BaseException {

  public InvalidAttractionIdException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private InvalidAttractionIdException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("요청한 Content Type Id, Content Id 에 해당하는 관광지가 존재하지 않습니다.")
        .build());
  }
}
