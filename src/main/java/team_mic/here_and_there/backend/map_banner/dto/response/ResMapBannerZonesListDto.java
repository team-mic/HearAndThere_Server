package team_mic.here_and_there.backend.map_banner.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"language", "guideMessage", "areaName", "zoneDataType", "zoneData"})
public class ResMapBannerZonesListDto {

  private String language;

  private String guideMessage;

  private String areaName;

  private String zoneDataType;

  private Object zoneData; //map or list
}