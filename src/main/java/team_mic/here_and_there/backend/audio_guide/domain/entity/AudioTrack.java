package team_mic.here_and_there.backend.audio_guide.domain.entity;

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
@Table(name = "audio_tracks")
@Entity
public class AudioTrack extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "audio_track_images", joinColumns = {
      @JoinColumn(name = "audio_track_id")})
  @Column(name = "image_url")
  private List<String> images = new ArrayList<>();

  @OneToMany(mappedBy = "audioTrack", fetch = FetchType.EAGER)
  private Set<AudioGuideTrackContainer> guides = new HashSet<>();

  private Double locationLatitude;

  private Double locationLongitude;

  @OneToMany(mappedBy = "audioTrack", fetch = FetchType.EAGER)
  private Set<AudioTrackLanguageContent> languageContents = new HashSet<>();

  @Builder
  private AudioTrack(List<String> images,Double locationLatitude, Double locationLongitude) {
    this.images = images;
    this.locationLatitude = locationLatitude;
    this.locationLongitude = locationLongitude;
  }
}
