package team_mic.here_and_there.backend.attraction.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.attraction.domain.entity.TouristArea;
import team_mic.here_and_there.backend.attraction.domain.repository.TouristAreaRepository;
import team_mic.here_and_there.backend.attraction.dto.response.ResTouristAreaListDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResTouristAreaListItemDto;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.common.util.FunctionalUtil;

@RequiredArgsConstructor
@Service
public class TouristAreaService {

  private final TouristAreaRepository touristAreaRepository;
  private final AttractionService attractionService;

  public ResTouristAreaListDto getTouristAreasInformation(String language) {
    List<TouristArea> areaList = new ArrayList<>();

    if (language.equals(Language.ENGLISH.getVersion())) {
      areaList = touristAreaRepository.findAllByLanguageOrderByIdAsc(Language.ENGLISH);
    }

    if (language.equals(Language.KOREAN.getVersion())) {
      areaList = touristAreaRepository.findAllByLanguageOrderByIdAsc(Language.KOREAN);
    }

    if (areaList.isEmpty()) {
      throw new NoSuchElementException(); //TODO : custom exception
    }

    List<ResTouristAreaListItemDto> areaItemList = new ArrayList<>();
    areaList.forEach(
        FunctionalUtil.throwingConsumerWrapper(area -> areaItemList.add(toTouristAreaListItem(area)))
    );

    return ResTouristAreaListDto.builder()
        .language(language)
        .touristAreaList(areaItemList)
        .build();
  }

  private ResTouristAreaListItemDto toTouristAreaListItem(TouristArea area) throws UnsupportedEncodingException {
    return ResTouristAreaListItemDto.builder()
        .areaName(area.getAreaName())
        .areaCode(area.getAreaCode())
        .hasSigunguAreaCode(area.getSigunguCode() != null ? true : false)
        .sigunguAreaCode(area.getSigunguCode())
        .areaThumbnailImageUrl(area.getThumbnailImage())
        .totalAttractionsCount(attractionService.getAreaAttractionsCount(area.getAreaCode(), area.getSigunguCode(), area.getLanguage().getVersion()))
        .build();
  }

  public TouristArea getTouristArea(String languageVersion, Integer areaCode, Integer sigunguAreaCode) {
    TouristArea touristArea;
    Language language= null;

    if(languageVersion.equals(Language.KOREAN.getVersion())){
      language = Language.KOREAN;
    }
    if(languageVersion.equals(Language.ENGLISH.getVersion())){
      language = Language.ENGLISH;
    }

    if(sigunguAreaCode==null){
      touristArea = touristAreaRepository.findByAreaCodeAndLanguage(areaCode, language)
              .orElseThrow(NoSuchElementException::new);
    }else{
      touristArea = touristAreaRepository.findByAreaCodeAndSigunguCodeAndLanguage(areaCode, sigunguAreaCode, language)
          .orElseThrow(NoSuchElementException::new);
    }
    return touristArea;
  }

  public boolean isValidAreaCode(Integer areaCode, Integer sigunguAreaCode) {
    if(sigunguAreaCode == null){
      return touristAreaRepository.existsByAreaCode(areaCode);
    }
    return touristAreaRepository.existsByAreaCodeAndSigunguCode(areaCode, sigunguAreaCode);
  }
}
