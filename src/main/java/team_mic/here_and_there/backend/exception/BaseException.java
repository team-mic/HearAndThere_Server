package team_mic.here_and_there.backend.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  private ErrorModel errorModel;

  @Builder
  public BaseException(ErrorModel errorModel) {
    super(errorModel.getMessage());
    this.errorModel = errorModel;
  }
}
