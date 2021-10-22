package team_mic.here_and_there.backend.audio_guide.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;

import java.util.Optional;
import team_mic.here_and_there.backend.audio_guide.domain.entity.MainCategory;
import team_mic.here_and_there.backend.audio_guide.domain.entity.SubCategory;

@Repository
public interface AudioGuideRepository extends JpaRepository<AudioGuide, Long> {
  Optional<AudioGuide> findById(Long id);
  List<AudioGuide> findAllByMainCategory(MainCategory mainCategory);
  List<AudioGuide> findAllBySubCategory(SubCategory subCategory);
}
