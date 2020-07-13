package team_mic.here_and_there.backend.location_tag.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
