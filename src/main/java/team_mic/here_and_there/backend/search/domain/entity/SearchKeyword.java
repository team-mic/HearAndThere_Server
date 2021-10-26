package team_mic.here_and_there.backend.search.domain.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.BaseTimeEntity;
import team_mic.here_and_there.backend.common.domain.Language;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "search_keywords")
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="search_keyword_type")
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

  protected SearchKeyword(Language language){
    this.language = language;
    this.searchHitCounts = 0L;
  }
}
