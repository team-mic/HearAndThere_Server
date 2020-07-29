package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_guides")
@Entity
public class AudioGuide extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String location;

  private String estimatedTravelTime;

  private String distance;

  @Lob
  private String overviewDescription;

  private String category;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "audio_guide_images", joinColumns = {
      @JoinColumn(name = "audio_guide_id")})
  @Column(name = "image_url")
  private Set<String> images = new HashSet<>();

  @OneToMany(mappedBy = "audioGuide", fetch = FetchType.EAGER)
  @OrderBy(value = "orderNumber ASC")
  private Set<AudioGuideTrackContainer> tracks = new HashSet<>();

  @OneToMany(mappedBy = "audioGuide", fetch = FetchType.EAGER)
  private Set<AudioGuideTag> tags = new HashSet<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "audio_guide_tourapi_attraction_ids", joinColumns = {
      @JoinColumn(name = "audio_guide_id")})
  private Set<Long> tourApiAttractionIds = new HashSet<>();

  @OneToMany(mappedBy = "audioGuide", fetch = FetchType.EAGER)
  private Set<AudioGuideCourse> course = new HashSet<>();

  @Builder
  private AudioGuide(String title, String location, String estimatedTravelTime, String distance,
      String overviewDescription, String category, Set<String> images,
      Set<Long> tourApiAttractionIds) {
    this.title = title;
    this.location = location;
    this.estimatedTravelTime = estimatedTravelTime;
    this.distance = distance;
    this.overviewDescription = overviewDescription;
    this.category = category;
    this.images = images;
    this.tourApiAttractionIds = tourApiAttractionIds;
  }
}
