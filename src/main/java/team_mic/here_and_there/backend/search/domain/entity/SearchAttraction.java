package team_mic.here_and_there.backend.search.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team_mic.here_and_there.backend.common.domain.Language;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DiscriminatorValue("attraction")
public class SearchAttraction extends SearchKeyword{
  private Long contentId;
  private Integer contentTypeId;

  @Builder
  private SearchAttraction(Language language, Long contentId, Integer contentTypeId){
    super(language);
    this.contentId = contentId;
    this.contentTypeId = contentTypeId;
  }
}
