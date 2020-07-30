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

  private String audioFileUrl;

  private String runningTime;

  private String title;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "audio_track_images", joinColumns = {
      @JoinColumn(name = "audio_track_id")})
  @Column(name = "image_url")
  private List<String> images = new ArrayList<>();

  @OneToMany(mappedBy = "audioTrack", fetch = FetchType.EAGER)
  private Set<AudioGuideTrackContainer> guides = new HashSet<>();

  private String placeName;

  private String placeAddress;

  private Double locationLatitude;

  private Double locationLongitude;

  @Builder
  private AudioTrack(String audioFileUrl, String runningTime, String title, List<String> images,
      String placeName, String placeAddress, Double locationLatitude, Double locationLongitude) {
    this.audioFileUrl = audioFileUrl;
    this.runningTime = runningTime;
    this.title = title;
    this.images = images;
    this.placeName = placeName;
    this.placeAddress = placeAddress;
    this.locationLatitude = locationLatitude;
    this.locationLongitude = locationLongitude;
  }
}
