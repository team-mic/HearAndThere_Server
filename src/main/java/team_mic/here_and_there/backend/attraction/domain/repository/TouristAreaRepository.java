package team_mic.here_and_there.backend.attraction.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.attraction.domain.entity.TouristArea;

@Repository
public interface TouristAreaRepository extends JpaRepository<TouristArea, Long> {
}
