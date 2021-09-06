package team_mic.here_and_there.backend.common.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "service_common_infos")
@Entity
public class ServiceCommonInfo extends BaseTimeEntity {
  @Id
  private String infoName;

  private String value;

  public void updateValue(String updatedValue) {
    this.value = updatedValue;
  }

  @Builder
  private ServiceCommonInfo(String infoName, String value){
    this.infoName = infoName;
    this.value = value;
  }
}
