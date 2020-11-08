package team_mic.here_and_there.backend.audio_guide.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class WrongCategoryException extends BaseException {

  public WrongCategoryException(){
    this(HttpStatus.BAD_REQUEST);
  }

  private WrongCategoryException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("잘못된 category 입니다. history, excursion, random 만 사용가능합니다.")
        .build());
  }
}
