package team_mic.here_and_there.backend.map_banner.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.common.domain.Language;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "map_zones")
@Entity
public class MapZone extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String parentArea;

  private String zoneName;

  @Enumerated(EnumType.STRING)
  private Language language;

  private String imageUrl;

  private String entryPointLongitude;

  private String entryPointLatitude;
}
