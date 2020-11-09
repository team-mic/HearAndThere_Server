package team_mic.here_and_there.backend.audio_guide.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_guide_track_containers")
@Entity
public class AudioGuideTrackContainer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "audio_guide_id")
  private AudioGuide audioGuide;

  @ManyToOne
  @JoinColumn(name = "audio_track_id")
  private AudioTrack audioTrack;

  private Integer orderNumber;

  @Builder
  private AudioGuideTrackContainer(AudioGuide audioGuide, AudioTrack audioTrack,
      Integer orderNumber) {
    this.audioGuide = audioGuide;
    this.audioTrack = audioTrack;
    this.orderNumber = orderNumber;
  }
}
