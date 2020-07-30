package team_mic.here_and_there.backend.attraction.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import team_mic.here_and_there.backend.attraction.dto.response.AreaCodeAndNameListDto;
import team_mic.here_and_there.backend.attraction.dto.response.TourApiBaseResModelDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;
import team_mic.here_and_there.backend.attraction.exception.InvalidAreaCodeException;

@Service
public class AttractionService {

  private final RestTemplate restTemplate;

  @Value("${tour.api.url}")
  private String tourApiUrl;

  @Value("${tour.api.serviceKey}")
  private String serviceKey;

  public AttractionService(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public ResAreaAttractionsListDto getAreaAttractions(Integer areaCode)
      throws UnsupportedEncodingException {

    if (isInvalidAreaCode(areaCode)) {
      throw new InvalidAreaCodeException();
    }

    UriComponentsBuilder builder = createBaseUriBuilder("/areaBasedList");

    UriComponents components = builder.queryParam("contentTypeId", 76)
        .queryParam("areaCode", areaCode)
        .queryParam("sigunguCode")
        .queryParam("cat1")
        .queryParam("cat2")
        .queryParam("cat3")
        .queryParam("listYN", "Y")
        .queryParam("arrange", "A")
        .queryParam("numOfRows", 10)
        .queryParam("pageNo", 1)
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();

    TourApiBaseResModelDto<ResAreaAttractionsListDto> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<ResAreaAttractionsListDto>>() {
            }).getBody();

    ResAreaAttractionsListDto listDto = modelDto.getResponse().getBody().getItems();

    String areaName = getAreaNameFromAreaCode(areaCode);

    listDto.getAttractionList()
        .forEach(resAreaAttractionItemDto -> resAreaAttractionItemDto.setAreaName(areaName));

    return listDto;
  }

  private boolean isInvalidAreaCode(Integer areaCode) {
    return !new ArrayList<>(
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 31, 32, 33, 34, 35, 36, 37, 38, 39))
        .contains(areaCode);
  }

  private HttpEntity<?> createHttpEntityHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return new HttpEntity<>(headers);
  }

  private UriComponentsBuilder createBaseUriBuilder(String appendUrl)
      throws UnsupportedEncodingException {

    return UriComponentsBuilder.fromHttpUrl(tourApiUrl + appendUrl)
        .queryParam("ServiceKey", URLDecoder.decode(serviceKey, "UTF-8"))
        .queryParam("MobileOS", "ETC")
        .queryParam("MobileApp", "HearAndThere")
        .queryParam("_type", "json");
  }

  private String getAreaNameFromAreaCode(Integer areaCode) throws UnsupportedEncodingException {

    UriComponentsBuilder builder = createBaseUriBuilder("/areaCode");
    UriComponents components = builder.queryParam("numOfRows", 17)
        .queryParam("pageNo", 1)
        .build(false);

    HttpEntity<?> httpEntity = createHttpEntityHeader();

    TourApiBaseResModelDto<AreaCodeAndNameListDto> modelDto =
        restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<TourApiBaseResModelDto<AreaCodeAndNameListDto>>() {
            }).getBody();

    AreaCodeAndNameListDto listDto = modelDto.getResponse().getBody().getItems();

    String areaName = listDto.getAreaCodeAndNameItemList().stream()
        .filter(areaCodeAndNameItemDto -> areaCodeAndNameItemDto.getCode() == areaCode)
        .findFirst()
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST))
        .getAreaName();

    return areaName;
  }
}
