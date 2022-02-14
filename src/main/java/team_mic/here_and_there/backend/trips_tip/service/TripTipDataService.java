package team_mic.here_and_there.backend.trips_tip.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.trips_tip.domain.entity.TripTip;
import team_mic.here_and_there.backend.trips_tip.domain.repository.AudioGuideTripsTipContainerRepository;
import team_mic.here_and_there.backend.trips_tip.domain.repository.TripTipRepository;

@RequiredArgsConstructor
@Service
public class TripTipDataService {

  private final AudioGuideTripsTipContainerRepository audioGuideTripsTipContainerRepository;
  private final TripTipRepository tripTipRepository;

  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

  public void insertTripTips() {
    tripTipRepository.save(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Transportation in Seoul")
        .contentsUrl(
            "https://www.notion.so/Transportation-in-Seoul-496362eeeadc4cea9cf047412dc72576")
        .thumbnailDescription(
            "Seoul has an extensive network of the high quality of public transportation and taxis. Especially, the city has one of the cleanest, efficient public bus and subway system. I’ll give you a guide to public transportation in Seoul!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/transportation_in_seoul.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Pre-Travel Checklist")
        .contentsUrl(
            "https://www.notion.so/Pre-Travel-Checklist-735934fd77e147938a3e152b55760108")
        .thumbnailDescription(
            "Are you planning a trip to Korea? Are you struggling what to bring on your Korea trip? Our pre-travel checklist will help you take care of essential tasks before traveling!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/pre-travel_checklist.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Basic Korean Expressions")
        .contentsUrl(
            "https://www.notion.so/Basic-Korean-Expressions-1c3efbfa9aed404392502908b974f4df")
        .thumbnailDescription(
            "When you visit Korea, why don’t you try using basic phrases to communicate with Korean people? Most Korean understand English, even though they can’t speak it well. Plus, there’s enough English in the major public areas and tourist zones, which means although you can’t read any Korean, you would have no problems. However, it’ll be helpful for your smooth travel, and you can get a chance to converse in Korean with locals if you know some phases for tourists. Here, we have some useful expressions for you that can be applied in numerous circumstances. It’s okay if the pronunciation doesn’t go right. Just give it a try! Before we begin, don’t forget to start things off with “Jeogiyo” which means “Excuse me” before asking something to a stranger and then get to chat!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/basic_korean_expressions.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Seasons Guide On What To Wear")
        .contentsUrl(
            "https://www.notion.so/Seasons-Guide-On-What-To-Wear-fc7a58a340714f9c8238c1ef0b0f624d")
        .thumbnailDescription(
            "Korea has 4 different weathers and unique fashion style. To fit in here, Let's get to know about everything about fashion.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/seasons_guide_on_what_to_wear.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("About Corona virus")
        .contentsUrl(
            "https://www.notion.so/About-Corona-virus-11d4031a759340fcb5395dbc35cdd5d4")
        .thumbnailDescription(
            "We should know about Corona virus to protect ourselves and others. Here are some COVID-19 guidelines.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/about_corona_virus.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("여행 갈때 듣기 좋은 신나고 청명한 노래")
        .contentsUrl(
            "https://www.notion.so/3898127fd2324e519c5ec78a6e9362e3")
        .thumbnailDescription(
            "여행할 때 빠질 수 없는 것 중 하나가 무엇일까요? 바로 음악이죠 \uD83D\uDE42 여행 갈 때 듣기 좋은 신나고 청명한 노래를 찾고 계신가요? 여행 할 때 듣기 좋은 최적의 노래를 추천해드릴게요!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/%EC%97%AC%ED%96%89+%EA%B0%88%EB%95%8C+%EB%93%A3%EA%B8%B0+%EC%A2%8B%EC%9D%80+%EC%8B%A0%EB%82%98%EA%B3%A0+%EC%B2%AD%EB%AA%85%ED%95%9C+%EB%85%B8%EB%9E%98.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("망원 맛집 추천 : 소금집 델리")
        .contentsUrl(
            "https://www.notion.so/189228cc56e94f6a8e19de65669bc22f")
        .thumbnailDescription(
            "망원동 맛집을 찾고 계신가요? 요즘 가장 인기있는 음식점 중 하나인 잠봉뵈르 맛집, 소금집 델리 망원점은 어떠신가요? 소금집 델리 후기를 들려드릴게요.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/%EB%A7%9D%EC%9B%90+%EB%A7%9B%EC%A7%91+%EC%B6%94%EC%B2%9C_%EC%86%8C%EA%B8%88%EC%A7%91+%EB%8D%B8%EB%A6%AC.jpg")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("서울의 교통수단")
        .contentsUrl(
            "https://www.notion.so/0a87b020c62b43069430b6f101ce72d5")
        .thumbnailDescription(
            "서울에서 이용할 수 있는 교통수단으로는 크게 버스, 지하철, 택시가 있습니다. 이미 익숙한 이 교통수단들에 대해 얼마나 잘 알고 계신가요? 여러분들이 모르실 수 있는 정보를 알려드릴게요 :)")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/%EC%84%9C%EC%9A%B8%EC%9D%98+%EA%B5%90%ED%86%B5%EC%88%98%EB%8B%A8.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("여행 전 체크리스트")
        .contentsUrl(
            "https://www.notion.so/c62ab752002a4444af2cc4185f61eab9")
        .thumbnailDescription(
            "국내 여행 준비를 하고 계신가요? 뭐부터 챙겨야할지 막막하시죠? 저희가 여행에 꼭 필요한 필수 아이템을 알려드릴게요! :)")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/%EC%97%AC%ED%96%89+%EC%A0%84+%EC%B2%B4%ED%81%AC%EB%A6%AC%EC%8A%A4%ED%8A%B8.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("날씨별 옷차림")
        .contentsUrl(
            "https://www.notion.so/3c3c3c93877346c08b645f0e7b6b4ebf")
        .thumbnailDescription(
            "일교차가 심한 날에는 어떤 옷을 입어야 할지 외출 전 망설여집니다. 봄과 가을이 점점 짧아짐에 따라 급격하게 날씨가 변하기도 하죠! 기온과 날씨에 따라 어떤 옷을 입어야 하는지 알려드릴게요 :)")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/%EB%82%A0%EC%94%A8%EB%B3%84+%EC%98%B7%EC%B0%A8%EB%A6%BC.png")
        .build());

    tripTipRepository.save(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("코로나 대비하며 안전한 여행하기")
        .contentsUrl(
            "https://www.notion.so/2ff29eddce87499383bfe8da2b89eeeb")
        .thumbnailDescription(
            "코로나 바이러스를 예방하기 위해서는 잘 알아두고 항상 조심해야겠죠? 코로나 예방수칙을 읽어보아요 :)")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/%EC%BD%94%EB%A1%9C%EB%82%98+%EB%8C%80%EB%B9%84%ED%95%98%EB%A9%B0+%EC%95%88%EC%A0%84%ED%95%9C+%EC%97%AC%ED%96%89%ED%95%98%EA%B8%B0.png")
        .build());
  }

  @Transactional
  public void insertTripTipsVersion2() {
    List<TripTip> dataList = new ArrayList<>();

    dataList.add(TripTip.builder()
            .language(Language.ENGLISH)
            .viewCount(0L)
            .title("Special Food of Traditional Market")
            .contentsUrl("https://www.notion.so/Special-Food-of-Traditional-Market-96ba5ff2e28145498654de6bacd2e555")
            .thumbnailDescription("There are many different traditional markets in Korea. Unlike a uniform mart, traditional markets have different products and atmospheres depending on the region. Due to the unique foods of traditional markets, more and more people are visiting the traditional markets recently. Today, let me introduce various markets in Seoul!")
            .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/special_food_of_traditional_market.jpg")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("How to Use Seoul Public Bike, 'Ttareungi'")
        .contentsUrl("https://www.notion.so/How-to-Use-Seoul-Public-Bike-Ttareungi-6ca0394d47ba4f9ba1e9c6d3591a2301")
        .thumbnailDescription("Want to rent a bike in Seoul? If so, try using 'Ttareungi'. Ttareungi is a system through which you can rent a bike in Seoul. Since there are Ttareungi rental stations at the subway entrance, bus stop, school, and other places used by many people, anyone, regardless of age or gender, can easily rent it if they have the 'Seoul Public Bike' application.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/how_to_use_seoul_public_bike.png")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("How to Get to Seoul from the Airport")
        .contentsUrl("https://www.notion.so/How-to-Get-to-Seoul-from-the-Airport-290c23fcd36c43c8afc6069e11392ed5")
        .thumbnailDescription("Since Incheon International Airport is a bit far from Seoul, you have no choice but to use means of transportation if you do not use a rental car to get to downtown Seoul. There are several ways to get to Seoul by means of transportation, and today I would like to introduce them.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/how_to_get_to_seoul_from_the_airport.png")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("How to Purchase and Charge a Transportation Card")
        .contentsUrl("https://www.notion.so/How-to-Purchase-and-Charge-a-Transportation-Card-abacf2585e4540339b3dbdebc78da9c9")
        .thumbnailDescription("You must have a 'transportation card' to ride subways and buses in Korea. Let me tell you where and how to buy a transportation card in Korea.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/how_to_purchase_and_charge_a_transportation_card.png")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Gyeongbokgung Palace Opening at Night")
        .contentsUrl("https://www.notion.so/Gyeongbokgung-Palace-Opening-at-Night-ded0fdaffc224f56aa1ae0e42c25c396")
        .thumbnailDescription("In Korea, there is a special event where you can visit ancient palaces such as Gyeongbokgung Palace, Changgyeonggung Palace, and Suwon Hwaseong Temporary Palace at night for a certain period of time. Among them, today, I would like to introduce the Gyeongbokgung Palace opening at night.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/gyeongbokgung_palace_opening_at_night+.jpg")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Great Places to Play Indoors")
        .contentsUrl("https://www.notion.so/Great-Places-to-Play-Indoors-f28dd357eac74ac2ab362d3679b55c1d")
        .thumbnailDescription("There are times when you have to play indoors because of hot or cold weather or unexpected natural disasters such as typhoons. In Seoul, there are many facilities where you can see and eat indoors. Among them, I would like to introduce the so-called 'Hot Place', which many people visit these days.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/great_places_to_play_indoors.jpg")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.ENGLISH)
        .viewCount(0L)
        .title("Good Places to Hike")
        .contentsUrl("https://www.notion.so/Good-Places-to-Hike-0425d10b894f4867a7ac4b4c158f89f6")
        .thumbnailDescription("If you take the subway on a weekend morning, the subway is full of hikers in hiking clothes. Why? Since about 70% of Korea is made up of mountains, you can see many mountains even in downtown Seoul. Therefore, people can climb various mountains without going far from Seoul, and in fact, many people go climbing. So, I would like to introduce some famous mountains that you can climb in Seoul!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/eng/good_places_to_hike.png")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("시장별 특색 음식")
        .contentsUrl("https://www.notion.so/655ea177be7e4411861dee336b6eebe2")
        .thumbnailDescription("한국에는 다양하고 많은 전통시장들이 있습니다. 획일적인 마트와는 달리 전통시장은 그 지역에 따라 판매품, 분위기 등이 다른데요, 전통시장만의 특색있는 음식들로 인해 최근 전통시장을 찾는 사람들이 많아지고 있습니다. 오늘은 서울에 있는 다양한 시장들에 대해 소개해드릴게요!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/special_food_of_traditional_market_kor.jpg")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("경복궁 야간개장")
        .contentsUrl("https://www.notion.so/ed38872de39442dd8ea8d674fbdeac9d")
        .thumbnailDescription("한국에는 일정 기간동안 경복궁, 창경궁, 수원 화성행궁 등 고궁을 일정 기간동안 야간에 관람할 수 있는 특별 관람 행사가 있습니다.  그 중에서 오늘은 경복궁 야간 개장을 소개해드리려고 합니다.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/gyeongbokgung_palace_opening_at_night_kor.jpg")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("실내에서 놀기 좋은곳")
        .contentsUrl("https://www.notion.so/ceb423fbe8844ed18f0510bd97d5c8f0")
        .thumbnailDescription("덥거나 추운 날씨, 혹은 태풍같이 뜻하지 않은 자연재해 때문에 실내에서 놀아야 할 때가 생깁니다. 서울에서는 실내에서 다양하게 보고, 먹을 수 있는 시설이 많은데요, 그 중에서도 요즘 많이 방문하는 일명 '핫플레이스'를 소개하고자 합니다.")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/great_places_to_play_indoors_kor.jpg")
        .build());

    dataList.add(TripTip.builder()
        .language(Language.KOREAN)
        .viewCount(0L)
        .title("등산하기 좋은 곳")
        .contentsUrl("https://www.notion.so/7a1e50e3909c4d0fae12a1aacaa5df73")
        .thumbnailDescription("주말 아침에 지하철을 타면 지하철 안은 등산복을 차려 입은 등산객들로 가득차 있습니다. 왜 그럴까요? 한국은 약 70%가 산으로 이루어져있기에 서울 도심에서도 많은 산을 볼 수 있습니다. 따라서 사람들은 서울에서 멀리 나가지 않고도 다양한 산들로 등산을 갈 수 있고 실제로 많은 사람들이 등산을 갑니다. 따라서 서울에서 갈 수 있는 유명한 산들을 소개해 드리고자 합니다!")
        .thumbnailImage(AWS_CLOUD_FRONT_URL_PREFIX
            + "/trip_tips/thumbnail_images/kor/good_places_to_hike_kor.png")
        .build());

    saveTripTipData(dataList);
  }

  private void saveTripTipData(List<TripTip> tripTipList) {
    tripTipRepository.saveAll(tripTipList);
  }
}
