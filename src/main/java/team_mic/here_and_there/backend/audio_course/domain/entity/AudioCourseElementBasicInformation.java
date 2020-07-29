package team_mic.here_and_there.backend.audio_course.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_course_element_basic_informations")
@Entity
public class AudioCourseElementBasicInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String address;

  private String phoneNumber;

  private String webSite;

  @OneToOne(mappedBy = "basicInformation", fetch = FetchType.EAGER)
  private AudioCourseElement audioCourseElement;

  @Builder
  private AudioCourseElementBasicInformation(String address, String phoneNumber, String webSite) {
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.webSite = webSite;
  }
}
