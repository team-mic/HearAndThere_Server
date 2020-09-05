package team_mic.here_and_there.backend.audio_course.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.audio_course.domain.entity.AudioGuideCourse;

@Repository
public interface AudioGuideCourseRepository extends JpaRepository<AudioGuideCourse, Long> {
}
