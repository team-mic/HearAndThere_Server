package team_mic.here_and_there.backend.common.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResourceType {
  AUDIO_GUIDE("audio-guide"),
  TRIP_TIP("trip-tip"),
  ATTRACTION("attraction");

  private final String name;
}
