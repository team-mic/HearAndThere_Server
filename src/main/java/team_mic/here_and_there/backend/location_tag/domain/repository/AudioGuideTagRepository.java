package team_mic.here_and_there.backend.location_tag.domain.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;

@Repository
public interface AudioGuideTagRepository extends JpaRepository<AudioGuideTag, Long> {
  @Query("select distinct at "
      +  "from AudioGuideTag at "
      +  "where at.tag.language=:language and at.tag.name like concat('%',:keyword,'%')")
  List<AudioGuideTag> findDistinctByTagNameContaining(@Param("language")Language language, @Param("keyword")String keyword);
}
