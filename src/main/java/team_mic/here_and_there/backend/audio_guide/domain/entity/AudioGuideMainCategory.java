package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AudioGuideMainCategory {

  HISTORY("history", "History and Culture", "역사와 문화"),
  MUSIC("music", "Music", "음악"),
  SHOPPING("shopping", "Fashion and Shopping", "패션과 쇼핑"),
  ART("art", "Art and Design", "예술과 디자인"),
  NATURE("nature", "Nature", "자연"),
  EXCURSION("excursion", "Healing in Everyday Life", "일상 속 힐링");

  private final String queryName;
  private final String databaseEnglishName;
  private final String databaseKoreanName;
}
