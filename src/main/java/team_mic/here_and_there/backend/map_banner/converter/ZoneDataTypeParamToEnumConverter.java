package team_mic.here_and_there.backend.map_banner.converter;

import org.springframework.core.convert.converter.Converter;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;

public class ZoneDataTypeParamToEnumConverter implements Converter<String, DisplayDataType> {

  @Override
  public DisplayDataType convert(String zoneDataTypeParam) {
    for(DisplayDataType displayDataType : DisplayDataType.values()){
      if(displayDataType.getType().equals(zoneDataTypeParam)){
        return displayDataType;
      }
    }
    return null;
  }
}
