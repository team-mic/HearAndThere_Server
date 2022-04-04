package team_mic.here_and_there.backend.audio_course.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_guide_courses")
@Entity
public class AudioGuideCourse extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "audio_guide_id")
  private AudioGuide audioGuide;

  @ManyToOne
  @JoinColumn(name = "audio_course_element_id")
  private AudioCourseElement audioCourseElement;

  private Integer orderNumber;

  @Builder
  private AudioGuideCourse(AudioGuide audioGuide, AudioCourseElement audioCourseElement,
      Integer orderNumber) {
    this.audioGuide = audioGuide;
    this.audioCourseElement = audioCourseElement;
    this.orderNumber = orderNumber;
  }
}
