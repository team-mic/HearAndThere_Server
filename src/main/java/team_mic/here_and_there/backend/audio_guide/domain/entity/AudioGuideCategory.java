package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AudioGuideCategory {

  HISTORY("history", "History", "역사"),
  MUSIC("music", "Music", "음악"),
  SHOPPING("shopping", "Shopping", "쇼핑"),
  ART("art", "Art", "예술"),
  NATURE("nature", "Nature", "자연"),
  EXCURSION("excursion", "Excursion", "일상");

  private final String queryName;
  private final String databaseEnglishName;
  private final String databaseKoreanName;
}
