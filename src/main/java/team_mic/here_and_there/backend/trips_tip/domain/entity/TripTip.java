package team_mic.here_and_there.backend.trips_tip.domain.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;

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

  private String title;

  @Lob
  private String description;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "trip_tip_images", joinColumns = {@JoinColumn(name = "trip_tip_id")})
  @Column(name = "image_url")
  private List<String> images = new ArrayList<>();

  @Builder
  private TripTip(String title, String description, List<String> images) {
    this.title = title;
    this.description = description;
    this.images = images;
  }
}
