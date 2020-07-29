package team_mic.here_and_there.backend.audio_course.domain.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_course_elements")
@Entity
public class AudioCourseElement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String estimatedTravelTime;

  @Lob
  private String overviewDescription;

  @ElementCollection(fetch= FetchType.EAGER)
  @CollectionTable(name = "audio_course_element_images", joinColumns = {@JoinColumn(name = "audio_course_element_id")})
  @Column(name = "image_url")
  private Set<String> images = new HashSet<>();

  @OneToMany(mappedBy = "audioCourseElement", fetch = FetchType.EAGER)
  private Set<AudioGuideCourse> guides = new HashSet<>();

  @OneToOne
  @JoinColumn(name = "audio_course_element_basic_information_id")
  private AudioCourseElementBasicInformation basicInformation;

  @OneToOne
  @JoinColumn(name = "audio_course_element_detail_information_id")
  private AudioCourseElementDetailInformation detailInformation;

  @Builder
  private AudioCourseElement(String title, String estimatedTravelTime, String overviewDescription,
      Set<String> images, AudioCourseElementBasicInformation basicInformation, AudioCourseElementDetailInformation detailInformation){
    this.title=title;
    this.estimatedTravelTime=estimatedTravelTime;
    this.overviewDescription=overviewDescription;
    this.images=images;
    this.basicInformation=basicInformation;
    this.detailInformation=detailInformation;
  }
}
