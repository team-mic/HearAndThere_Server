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
  private String title;
  private Long contentId;
  private Integer contentTypeId;

  public void updateTitle(String title){
    this.title = title;
  }

  @Builder
  private SearchAttraction(Language language, String title, Long contentId, Integer contentTypeId){
    super(language);
    this.title = title;
    this.contentId = contentId;
    this.contentTypeId = contentTypeId;
  }
}
