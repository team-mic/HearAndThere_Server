package team_mic.here_and_there.backend.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageSizeType {
  THUMBNAIL(50, 50, "webp"),
  SMALL(300,300, "webp"),
  MIDDLE(800, 800, "webp"),
  LARGE(1200, 1200, "webp");

  private final int width;
  private final int height;
  private final String format;

  public String getSuffix(){
    return "?w="+ width + "&h="+ height + "&f=" + format;
  }
}
