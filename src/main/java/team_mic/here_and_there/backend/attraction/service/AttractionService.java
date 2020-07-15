package team_mic.here_and_there.backend.attraction.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import team_mic.here_and_there.backend.attraction.dto.response.AreaAttractionApiModelDto;
import team_mic.here_and_there.backend.attraction.dto.response.ResAreaAttractionsListDto;

import java.io.UnsupportedEncodingException;
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
        UriComponents components = UriComponentsBuilder.fromHttpUrl(tourApiUrl+"/areaBasedList")
                .queryParam("ServiceKey", URLDecoder.decode(serviceKey, "UTF-8"))
                .queryParam("contentTypeId",76)
                .queryParam("areaCode",1)
                .queryParam("sigunguCode")
                .queryParam("cat1")
                .queryParam("cat2")
                .queryParam("cat3")
                .queryParam("MobileOS","ETC")
                .queryParam("MobileApp","TourAPI3.0_Guide")
                .queryParam("listYN","Y")
                .queryParam("arrange","A")
                .queryParam("numOfRows",12)
                .queryParam("pageNo",1)
                .queryParam("_type","json")
                .build(false);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        AreaAttractionApiModelDto modelDto = restTemplate.exchange(components.toUriString(), HttpMethod.GET, httpEntity, AreaAttractionApiModelDto.class).getBody();
        ResAreaAttractionsListDto listDto = modelDto.getResponse().getBody().getItems();
        listDto.getAttractionList().forEach(resAreaAttractionItemDto -> resAreaAttractionItemDto.setArea(area));

        return listDto;
    }
}
