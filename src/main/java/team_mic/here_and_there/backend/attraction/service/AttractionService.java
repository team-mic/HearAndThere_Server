package team_mic.here_and_there.backend.attraction.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import team_mic.here_and_there.backend.attraction.dto.response.TourApiBaseResModelDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;

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

    public ResAreaAttractionsListDto getAreaAttractions(String area) throws UnsupportedEncodingException {

        Integer areaCode = getAreaCodeFromAreaName(area);
        UriComponentsBuilder builder = createBaseUriBuilder("/areaBasedList");

        UriComponents components = builder.queryParam("contentTypeId",76)
                .queryParam("areaCode",areaCode)
                .queryParam("sigunguCode")
                .queryParam("cat1")
                .queryParam("cat2")
                .queryParam("cat3")
                .queryParam("listYN","Y")
                .queryParam("arrange","A")
                .queryParam("numOfRows",10)
                .queryParam("pageNo",1)
                .build(false);

        HttpEntity<?> httpEntity = createHttpEntityHeader();

        TourApiBaseResModelDto<ResAreaAttractionsListDto> modelDto =
               restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<TourApiBaseResModelDto<ResAreaAttractionsListDto>>() {}).getBody();

        ResAreaAttractionsListDto listDto =  modelDto.getResponse().getBody().getItems();
        listDto.getAttractionList().forEach(resAreaAttractionItemDto -> resAreaAttractionItemDto.setArea(area));

        return listDto;
    }

    private HttpEntity<?> createHttpEntityHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

    private UriComponentsBuilder createBaseUriBuilder(String appendUrl) throws UnsupportedEncodingException {

        return UriComponentsBuilder.fromHttpUrl(tourApiUrl+appendUrl)
                .queryParam("ServiceKey", URLDecoder.decode(serviceKey, "UTF-8"))
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","HearAndThere")
                .queryParam("_type","json");
    }

    private Integer getAreaCodeFromAreaName(String area) throws UnsupportedEncodingException {
        UriComponentsBuilder builder = createBaseUriBuilder("/areaCode");
        UriComponents components = builder.queryParam("numOfRows",17)
                .queryParam("pageNo",1)
                .build(false);

        HttpEntity<?> httpEntity = createHttpEntityHeader();

        return 1;
    }
}
