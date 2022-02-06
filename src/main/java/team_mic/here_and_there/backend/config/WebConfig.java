package team_mic.here_and_there.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team_mic.here_and_there.backend.common.converter.LanguageParamToEnumConverter;
import team_mic.here_and_there.backend.map_banner.converter.ZoneDataTypeParamToEnumConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   @Override
    public void addFormatters(FormatterRegistry registry) {
      registry.addConverter(new ZoneDataTypeParamToEnumConverter());
      registry.addConverter(new LanguageParamToEnumConverter());
    }
}
