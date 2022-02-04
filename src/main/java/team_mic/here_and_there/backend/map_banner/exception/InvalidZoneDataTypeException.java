package team_mic.here_and_there.backend.map_banner.exception;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class InvalidZoneDataTypeException extends BaseException {

  public InvalidZoneDataTypeException() {
    this(HttpStatus.BAD_REQUEST);
  }

  private InvalidZoneDataTypeException(HttpStatus status) {

    super(ErrorModel.builder()
        .httpStatus(status)
        .message("유효하지않은 zone 데이터 타입입니다. " +
            Arrays.toString(DisplayDataType.values()) +
            "중 하나의 데이터 타입만 지원됩니다.")
        .build());
  }
}
