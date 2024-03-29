package team_mic.here_and_there.backend.trips_tip.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.common.domain.Language;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "trip_tips")
@Entity
public class TripTip extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Language language;

  private String title;

  @Lob
  private String thumbnailDescription;

  @OneToMany(mappedBy = "tripTip")
  private Set<AudioGuideTripsTipContainer> guides = new HashSet<>();

  private String contentsUrl;

  private String thumbnailImage;

  private Long viewCount;

  public void updateViewCount(){
    this.viewCount =  viewCount + 1L;
  }

  @Builder
  private TripTip(Language language, String title, String thumbnailDescription,
                  String contentsUrl, String thumbnailImage, Long viewCount) {
    this.language = language;
    this.title = title;
    this.thumbnailDescription = thumbnailDescription;
    this.contentsUrl = contentsUrl;
    this.thumbnailImage = thumbnailImage;
    this.viewCount = viewCount;
  }
}
