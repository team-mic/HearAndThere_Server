package team_mic.here_and_there.backend.audio_guide.domain.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.trips_tip.domain.entity.AudioGuideTripsTipContainer;

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

  private String location;

  private String estimatedTravelTime;

  private String distance;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "main_category_id")
  private MainCategory mainCategory;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sub_category_id")
  private SubCategory subCategory;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "audio_guide_images", joinColumns = {
      @JoinColumn(name = "audio_guide_id")})
  @Column(name = "image_url")
  private List<String> images = new ArrayList<>();

  @OneToMany(mappedBy = "audioGuide")
  @OrderBy(value = "orderNumber ASC")
  private Set<AudioGuideTrackContainer> tracks = new HashSet<>();

  @OneToMany(mappedBy = "audioGuide", fetch = FetchType.EAGER)
  private Set<AudioGuideTag> tags = new HashSet<>();

  @OneToMany(mappedBy = "audioGuide")
  @OrderBy(value = "orderNumber ASC")
  private Set<AudioGuideCourse> course = new HashSet<>();

  @OneToMany(mappedBy = "audioGuide", fetch = FetchType.EAGER)
  private Set<AudioGuideLanguageContent> languageContents = new HashSet<>();

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "recommended_audio_guide_ids", joinColumns = {
          @JoinColumn(name = "audio_guide_id")})
  @Column(name = "recommended_guide_ids")
  private List<Long> recommendedAudioGuideIds = new ArrayList<>();

  @OneToMany(mappedBy = "audioGuide")
  private Set<AudioGuideTripsTipContainer> relatedTripsTips = new HashSet<>();

  @Builder
  private AudioGuide(String location, String estimatedTravelTime, String distance,
                     List<String> images, List<Long> recommendedAudioGuideIds,
                      MainCategory mainCategory, SubCategory subCategory) {
    this.location = location;
    this.estimatedTravelTime = estimatedTravelTime;
    this.distance = distance;
    this.images = images;
    this.recommendedAudioGuideIds = recommendedAudioGuideIds;
    this.mainCategory = mainCategory;
    this.subCategory = subCategory;
  }

  public void updateCategory(MainCategory mainCategory, SubCategory subCategory){
    this.mainCategory = mainCategory;
    this.subCategory = subCategory;
  }
}
