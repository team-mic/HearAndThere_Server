package team_mic.here_and_there.backend.map_banner.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team_mic.here_and_there.backend.common.domain.DisplayDataType;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.map_banner.dto.response.ResMapBannerZonesListDto;
import team_mic.here_and_there.backend.map_banner.service.MapZoneService;

@WebMvcTest(MapZoneController.class)
public class MapZoneControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MapZoneService mapZoneService;

  @Test
  public void getMapBannerZonesList_should_success_테스트()
      throws Exception {
    String givenAreaName = "seoul";
    Language givenLanguage = Language.KOREAN;
    DisplayDataType givenDataType = DisplayDataType.LIST;
    String languageParam = givenLanguage.getVersion();
    String dataTypeParam = givenDataType.getType();

    given(mapZoneService.getMapBannerZonesList(givenLanguage, givenAreaName, givenDataType))
        .willReturn(ResMapBannerZonesListDto.builder()
            .language(languageParam)
            .build());

    mockMvc.perform(
        MockMvcRequestBuilders.get("/v1/map-banners/area/{area-name}/zones", givenAreaName)
            .param("lan", languageParam)
            .param("data-type", dataTypeParam)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(languageParam)))
        .andExpect(content().json("{}"));
  }

  @Test
  public void getMapBannerZonesList_invalid_data_type_테스트()
      throws Exception {
    String givenAreaName = "seoul";
    String languageParam = Language.ENGLISH.getVersion();
    String invalidDataTypeParam = "invalid-type";

    mockMvc.perform(
        MockMvcRequestBuilders.get("/v1/map-banners/area/{area-name}/zones", givenAreaName)
            .param("lan", languageParam)
            .param("data-type", invalidDataTypeParam)
    ).andExpect(status().isBadRequest());
  }

  @Test
  public void getMapBannerZonesList_invalid_lan_type_테스트()
      throws Exception {
    String givenAreaName = "seoul";
    String invalidLanguageParam = "invalid-lan";
    String dataTypeParam = DisplayDataType.LIST.getType();

    mockMvc.perform(
        MockMvcRequestBuilders.get("/v1/map-banners/area/{area-name}/zones", givenAreaName)
            .param("lan", invalidLanguageParam)
            .param("data-type", dataTypeParam)
    ).andExpect(status().isBadRequest());
  }
}
