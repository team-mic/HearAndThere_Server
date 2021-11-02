package team_mic.here_and_there.backend.search.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DiscriminatorValue("trip-tip")
public class SearchTripTip extends SearchKeyword{
  @ManyToOne
  @JoinColumn(name = "trip_tip_id")
  private TripTip tripTip;

  @Builder
  private SearchTripTip(Language language, TripTip tripTip){
    super(language);
    this.tripTip = tripTip;
  }
}
