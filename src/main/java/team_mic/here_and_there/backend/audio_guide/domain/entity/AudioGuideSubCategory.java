package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AudioGuideSubCategory {

  SEOUL_PALACE("서울의 궁궐 둘러보기", "Touring Palaces in Seoul", "#D3C9D9", "/audio-guides/category-icon-images/palace.svg", "\uD83C\uDDF0\uD83C\uDDF7", AudioGuideMainCategory.HISTORY),
  MODERN_HISTORY_COEXIST("현대와 역사가 공존하는 서울", "Seoul Where Modernity and History Coexist", "#E8D1B8", "/audio-guides/category-icon-images/train.svg", "\uD83D\uDE9E", AudioGuideMainCategory.HISTORY),
  K_POP_LAN("K-pop 랜선투어", "K-pop LAN Tour", "#C4D6E1", "/audio-guides/category-icon-images/note.svg", "\uD83C\uDFB5", AudioGuideMainCategory.MUSIC),
  SEOUL_SHOPPING("서울에서 쇼핑 즐기기", "Enjoying Shopping in Seoul", "#CBDCB7", "/audio-guides/category-icon-images/shopping_bag.svg", "\uD83D\uDECD", AudioGuideMainCategory.SHOPPING),
  BECOME_ARTIST("예술가 되어보기", "Becoming an Artist", "#F0E4A9", "/audio-guides/category-icon-images/palette.svg", "\uD83C\uDFA8", AudioGuideMainCategory.ART),
  NATURE_IN_CITY("도심 속 자연 둘러보기", "Touring Nature in the City Center", "#EDD4D1", "/audio-guides/category-icon-images/flower.svg", "\uD83C\uDF3A", AudioGuideMainCategory.NATURE),
  ENJOY_DAY("서울에서 즐기는 하루", "Enjoying a Day in Seoul", "#DDDBD6", "/audio-guides/category-icon-images/cloud.svg", "⛅", AudioGuideMainCategory.EXCURSION);

  private final String korName;
  private final String engName;
  private final String bannerBackgroundColor;
  private final String bannerIconImage;
  private final String emojiUnicode;
  private final AudioGuideMainCategory parentMainCategory;
}
