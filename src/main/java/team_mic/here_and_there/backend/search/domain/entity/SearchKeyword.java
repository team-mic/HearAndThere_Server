package team_mic.here_and_there.backend.search.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.common.domain.Language;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "search_keywords")
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class SearchKeyword extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Language language;

  private Long searchHitCounts;

  public void updateSearchHitCount(){
    this.searchHitCounts = this.searchHitCounts + 1;
  }

  public String getDiscriminatorValue(){
    DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);

    return val == null ? null : val.value();
  }

  protected SearchKeyword(Language language){
    this.language = language;
    this.searchHitCounts = 0L;
  }
}
