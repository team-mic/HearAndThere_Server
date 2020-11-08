package team_mic.here_and_there.backend.attraction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.attraction.domain.entity.TouristArea;
import team_mic.here_and_there.backend.attraction.domain.repository.TouristAreaRepository;
import team_mic.here_and_there.backend.common.domain.Language;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TouristAreaDataService {

    private final TouristAreaRepository touristAreaRepository;
    private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

    @Transactional
    public void insertKoreanTouristAreaImages() {
        touristAreaRepository.save(TouristArea.builder()
                .areaName("서울")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/seoul.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("부산")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/busan.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("제주")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/jeju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("인천")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/incheon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("속초")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/sokcho.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("경주")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/kyungju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("대구")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/daegu.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("전주")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/jeonju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("광주")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/gwangju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("강릉")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/gangneung.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("순천")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/sooncheon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("울산")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/ulsan.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("대전")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/daejeon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("평창")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/pyeongchang.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("춘천")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/chuncheon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("안동")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/andong.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("가평")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/gapyeong.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("창원")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/changwon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("수원")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/suwon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("여수")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/yeosu.png")
                .build());
        touristAreaRepository.save(TouristArea.builder()
                .areaName("파주")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/paju.png")
                .build());
        touristAreaRepository.save(TouristArea.builder()
                .areaName("고양")
                .language(Language.KOREAN)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/goyang.png")
                .build());
    }

    @Transactional
    public void insertEnglishTouristAreaImages() {
        touristAreaRepository.save(TouristArea.builder()
                .areaName("Seoul")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/seoul.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Busan")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/busan.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Jeju")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/jeju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Incheon")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/incheon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Sokcho")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/sokcho.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Gyeongju")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/kyungju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Daegu")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/daegu.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Jeonju")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/jeonju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Gwangju")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/gwangju.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Gangneung")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/gangneung.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Suncheon")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/sooncheon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Ulsan")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/ulsan.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Daejeon")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/daejeon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Pyeongchang")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/pyeongchang.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Chuncheon")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/chuncheon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Andong")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/andong.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Gapyeong")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/gapyeong.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Changwon")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/changwon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Suwon")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/suwon.png")
                .build());

        touristAreaRepository.save(TouristArea.builder()
                .areaName("Yeosu")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/yeosu.png")
                .build());
        touristAreaRepository.save(TouristArea.builder()
                .areaName("Paju")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/paju.png")
                .build());
        touristAreaRepository.save(TouristArea.builder()
                .areaName("Goyang")
                .language(Language.ENGLISH)
                .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX + "/attractions/local_main_images/goyang.png")
                .build());

    }
}
