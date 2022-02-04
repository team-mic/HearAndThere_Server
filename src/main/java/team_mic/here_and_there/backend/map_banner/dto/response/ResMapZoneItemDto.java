package team_mic.here_and_there.backend.map_banner.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonPropertyOrder({"zoneKey", "zoneName", "zoneImageUrl", "hashEntryPoint", "entryPointLatitude", "entryPointLongitude"})
public class ResMapZoneItemDto {

  private Integer zoneKey;

  private String zoneName;

  private String zoneImageUrl;

  private Boolean hashEntryPoint;

  private Double entryPointLatitude;

  private Double entryPointLongitude;
}
