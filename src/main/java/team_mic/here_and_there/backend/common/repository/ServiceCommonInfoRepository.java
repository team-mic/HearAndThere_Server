package team_mic.here_and_there.backend.common.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.entity.ServiceCommonInfo;

@Repository
public interface ServiceCommonInfoRepository extends JpaRepository<ServiceCommonInfo, String> {
  Optional<ServiceCommonInfo> findByKeyEquals(String key);
}
