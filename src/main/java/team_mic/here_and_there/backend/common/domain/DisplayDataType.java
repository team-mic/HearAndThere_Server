package team_mic.here_and_there.backend.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DisplayDataType {

  MAP("map"),
  LIST("list");

  private final String type;
}
