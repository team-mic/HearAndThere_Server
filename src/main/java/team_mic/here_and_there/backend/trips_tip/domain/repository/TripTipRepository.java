package team_mic.here_and_there.backend.trips_tip.domain.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;

import java.util.List;

@Repository
public interface TripTipRepository extends JpaRepository<TripTip, Long> {
  Optional<TripTip> findByIdAndLanguage(Long id, Language language);
  List<TripTip> findAllByLanguageOrderByViewCountDesc(Language language);
  List<TripTip> findAllByLanguage(Language language);
  @Query("select distinct t "
      + " from TripTip t "
      + " where (t.language=:language and lower(t.title) like concat('%',:keyword,'%'))"
      + " or (t.language=:language and lower(t.thumbnailDescription) like concat('%',:keyword,'%'))")
  Page<TripTip> findDistinctByTitleAndDescriptionContaining(@Param("language")Language language, @Param("keyword")String keyword, Pageable pageable);
}
