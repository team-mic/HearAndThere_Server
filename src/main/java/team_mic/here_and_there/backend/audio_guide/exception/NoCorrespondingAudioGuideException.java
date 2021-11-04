package team_mic.here_and_there.backend.audio_guide.exception;

import org.springframework.http.HttpStatus;
import team_mic.here_and_there.backend.exception.BaseException;
import team_mic.here_and_there.backend.exception.ErrorModel;

public class NoCorrespondingAudioGuideException extends BaseException {

  public NoCorrespondingAudioGuideException() {
    this(HttpStatus.NOT_FOUND);
  }

  private NoCorrespondingAudioGuideException(HttpStatus status) {
    super(ErrorModel.builder()
        .httpStatus(status)
        .message("요청한 id 에 해당하는 오디오 가이드가 존재하지 않습니다.")
        .build());
  }
}
