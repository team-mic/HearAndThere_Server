package team_mic.here_and_there.backend.audio_course.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.Language;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "audio_course_element_language_contents")
@Entity
public class AudioCourseElementLanguageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String title;

    private Long tourApiAttractionContentId;

    @ManyToOne
    @JoinColumn(name = "audio_course_element_id")
    private AudioCourseElement audioCourseElement;

    @Builder
    private AudioCourseElementLanguageContent(Language language, String title, Long tourApiAttractionContentId, AudioCourseElement audioCourseElement){
        this.language = language;
        this.title = title;
        this.tourApiAttractionContentId = tourApiAttractionContentId;
        this.audioCourseElement = audioCourseElement;
    }
}
