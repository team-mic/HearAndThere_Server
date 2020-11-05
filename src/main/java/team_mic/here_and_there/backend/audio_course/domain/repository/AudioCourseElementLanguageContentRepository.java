package team_mic.here_and_there.backend.audio_course.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioCourseElementLanguageContent;

@Repository
public interface AudioCourseElementLanguageContentRepository extends JpaRepository<AudioCourseElementLanguageContent, Long> {
}
