package team_mic.here_and_there.backend.common.exception;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class InvalidLanguageException extends BaseException {

  public InvalidLanguageException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private InvalidLanguageException(HttpStatus status) {

    super(ErrorModel.builder()
        .httpStatus(status)
        .message("유효하지않은 언어 버전입니다. " +
            Arrays.toString(Language.values()) +
            "중 하나의 언어 버전만 지원됩니다.")
        .build());
  }
}
