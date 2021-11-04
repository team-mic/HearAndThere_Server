package team_mic.here_and_there.backend.search.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"language", "searchKeywordType", "updatedTargetIds", "updatedHitCounts"})
public class ResPatchedSearchKeywordDto {
    @ApiModelProperty(notes = "언어 버전")
    private String language;

    @ApiModelProperty(notes = "검색 키워드 타입")
    private String searchKeywordType;

    @ApiModelProperty(notes = "업데이트 된 검색 대상의 id(type=attraction의 경우 contentTypeId, contentId 순서)")
    private List<Long> updatedTargetIds;

    @ApiModelProperty(notes = "업데이트 이후 검색 대상의 검색 hit 수")
    private Long updatedHitCounts;
}
