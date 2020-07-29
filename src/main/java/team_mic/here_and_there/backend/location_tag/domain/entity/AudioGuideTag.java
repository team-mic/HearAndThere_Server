package team_mic.here_and_there.backend.location_tag.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_guide_tags")
@Entity
public class AudioGuideTag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private Tag tag;

  @ManyToOne
  @JoinColumn(name = "audio_guide_id")
  private AudioGuide audioGuide;

  @Builder
  private AudioGuideTag(Tag tag, AudioGuide audioGuide) {
    this.tag = tag;
    this.audioGuide = audioGuide;
  }
}
