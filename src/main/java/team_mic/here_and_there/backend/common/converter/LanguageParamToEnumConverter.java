package team_mic.here_and_there.backend.common.converter;

import org.springframework.core.convert.converter.Converter;
import team_mic.here_and_there.backend.common.domain.Language;

public class LanguageParamToEnumConverter implements Converter<String, Language> {

  @Override
  public Language convert(String languageParam) {
    for(Language language : Language.values()){
      if(language.getVersion().equals(languageParam)){
        return language;
      }
    }
    return null;
  }
}