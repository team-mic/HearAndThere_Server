package team_mic.here_and_there.backend.trips_tip.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.trips_tip.domain.entity.AudioGuideTripsTipContainer;

@Repository
public interface AudioGuideTripsTipContainerRepository extends JpaRepository<AudioGuideTripsTipContainer, Long> {
}
