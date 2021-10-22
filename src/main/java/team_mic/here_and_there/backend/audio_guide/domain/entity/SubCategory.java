package team_mic.here_and_there.backend.audio_guide.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "sub_categories")
@Entity
public class SubCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "parent_main_category_id")
  private MainCategory parentMainCategory;

  private String engName;

  private String korName;

  private String greetings;

  private String bannerBackgroundColor;

  private String bannerIconImage;

  private String emojiUnicode;

  @Builder
  private SubCategory(MainCategory parentMainCategory, String engName, String korName, String bannerBackgroundColor,
      String bannerIconImage, String emojiUnicode){
    this.parentMainCategory = parentMainCategory;
    this.engName = engName;
    this.korName = korName;
    this.bannerBackgroundColor = bannerBackgroundColor;
    this.bannerIconImage = bannerIconImage;
    this.emojiUnicode = emojiUnicode;
  }
}
