package team_mic.here_and_there.backend.audio_course.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

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

  private String estimatedTravelTime;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "audio_course_element_images", joinColumns = {
      @JoinColumn(name = "audio_course_element_id")})
  @Column(name = "image_url")
  private List<String> images = new ArrayList<>();

  @OneToMany(mappedBy = "audioCourseElement", fetch = FetchType.EAGER)
  private Set<AudioGuideCourse> guides = new HashSet<>();

  @OneToMany(mappedBy = "audioCourseElement", fetch = FetchType.EAGER)
  private Set<AudioCourseElementLanguageContent> languageContents = new HashSet<>();

  @Builder
  private AudioCourseElement(String estimatedTravelTime,
      List<String> images) {
    this.estimatedTravelTime = estimatedTravelTime;
    this.images = images;
  }
}
