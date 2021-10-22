package team_mic.here_and_there.backend.audio_guide.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.AudioGuide;
import team_mic.here_and_there.backend.audio_guide.domain.entity.MainCategory;
import team_mic.here_and_there.backend.audio_guide.domain.entity.SubCategory;
import team_mic.here_and_there.backend.audio_guide.domain.repository.AudioGuideRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.MainCategoryRepository;
import team_mic.here_and_there.backend.audio_guide.domain.repository.SubCategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryDataService {
  private final MainCategoryRepository mainCategoryRepository;
  private final SubCategoryRepository subCategoryRepository;
  private final AudioGuideRepository audioGuideRepository;

  private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";


  @Transactional
  public void insertCategories() {
    MainCategory historyCategory = mainCategoryRepository.save(MainCategory.builder()
        .engName("History and Culture")
        .korName("역사와 문화")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(historyCategory)
        .korName("서울의 궁궐 둘러보기")
        .engName("Touring Palaces in Seoul")
        .bannerBackgroundColor("#D3C9D9")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/palace.svg")
        .emojiUnicode("\uD83C\uDDF0\uD83C\uDDF7")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(historyCategory)
        .korName("현대와 역사가 공존하는 서울")
        .engName("Seoul Where Modernity and History Coexist")
        .bannerBackgroundColor("#E8D1B8")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/train.svg")
        .emojiUnicode("\uD83D\uDE9E")
        .build());

    MainCategory musicCategory = mainCategoryRepository.save(MainCategory.builder()
        .engName("Music")
        .korName("음악")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(musicCategory)
        .korName("K-pop 랜선투어")
        .engName("K-pop LAN Tour")
        .bannerBackgroundColor("#C4D6E1")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/note.svg")
        .emojiUnicode("\uD83C\uDFB5")
        .build());

    MainCategory shoppingCategory = mainCategoryRepository.save(MainCategory.builder()
        .engName("Fashion and Shopping")
        .korName("패션과 쇼핑")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(shoppingCategory)
        .korName("서울에서 쇼핑 즐기기")
        .engName("Enjoying Shopping in Seoul")
        .bannerBackgroundColor("#CBDCB7")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/shopping_bag.svg")
        .emojiUnicode("\uD83D\uDECD")
        .build());

    MainCategory artCategory = mainCategoryRepository.save(MainCategory.builder()
        .engName("Art and Design")
        .korName("예술과 디자인")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(artCategory)
        .korName("예술가 되어보기")
        .engName("Becoming an Artist")
        .bannerBackgroundColor("#F0E4A9")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/palette.svg")
        .emojiUnicode("\uD83C\uDFA8")
        .build());

    MainCategory natureCategory = mainCategoryRepository.save(MainCategory.builder()
        .engName("Nature")
        .korName("자연")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(natureCategory)
        .korName("도심 속 자연 둘러보기")
        .engName("Touring Nature in the City Center")
        .bannerBackgroundColor("#EDD4D1")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/flower.svg")
        .emojiUnicode("\uD83C\uDF3A")
        .build());

    MainCategory excursionCategory = mainCategoryRepository.save(MainCategory.builder()
        .engName("Healing in Everyday Life")
        .korName("일상 속 힐링")
        .build());

    subCategoryRepository.save(SubCategory.builder()
        .parentMainCategory(excursionCategory)
        .korName("서울에서 즐기는 하루")
        .engName("Enjoying a Day in Seoul")
        .bannerBackgroundColor("#DDDBD6")
        .bannerIconImage(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/category-icon-images/cloud.svg")
        .emojiUnicode("⛅")
        .build());
  }

  @Transactional
  public void insertCategoriesToGuides() {
    List<AudioGuide> guides = audioGuideRepository.findAll();
    List<MainCategory> mainCategories = mainCategoryRepository.findAll();
    List<SubCategory> subCategories = subCategoryRepository.findAll();

    Map<Long, AudioGuide> guidesMap = new HashMap<>();
    guides.stream()
        .forEach(guide -> guidesMap.put(guide.getId(), guide));

    Map<String, MainCategory> mainCategoryKorNameMap = new HashMap<>();
    Map<String, SubCategory> subCategoryKorNameMap = new HashMap<>();

    mainCategories.stream()
        .forEach(mainCategory -> mainCategoryKorNameMap.put(mainCategory.getKorName(), mainCategory));
    subCategories.stream()
        .forEach(subCategory -> subCategoryKorNameMap.put(subCategory.getKorName(), subCategory));

    //역사와 문화
    MainCategory historyCategory = mainCategoryKorNameMap.get("역사와 문화");
    SubCategory subCategory1 = subCategoryKorNameMap.get("서울의 궁궐 둘러보기");
    SubCategory subCategory2 = subCategoryKorNameMap.get("현대와 역사가 공존하는 서울");

    guidesMap.get(14L).updateCategory(historyCategory, subCategory1);
    guidesMap.get(15L).updateCategory(historyCategory, subCategory1);
    guidesMap.get(16L).updateCategory(historyCategory, subCategory1);

    guidesMap.get(7L).updateCategory(historyCategory, subCategory2);
    guidesMap.get(5L).updateCategory(historyCategory, subCategory2);
    guidesMap.get(11L).updateCategory(historyCategory, subCategory2);

    //음악
    MainCategory musicCategory = mainCategoryKorNameMap.get("음악");
    SubCategory subCategory3 = subCategoryKorNameMap.get("K-pop 랜선투어");

    guidesMap.get(1L).updateCategory(musicCategory, subCategory3);

    //패션과 쇼핑
    MainCategory shoppingCategory = mainCategoryKorNameMap.get("패션과 쇼핑");
    SubCategory subCategory4 = subCategoryKorNameMap.get("서울에서 쇼핑 즐기기");

    guidesMap.get(13L).updateCategory(shoppingCategory, subCategory4);
    guidesMap.get(2L).updateCategory(shoppingCategory, subCategory4);
    guidesMap.get(9L).updateCategory(shoppingCategory, subCategory4);
    guidesMap.get(18L).updateCategory(shoppingCategory, subCategory4);

    //예술과 디자인
    MainCategory artCategory = mainCategoryKorNameMap.get("예술과 디자인");
    SubCategory subCategory5 = subCategoryKorNameMap.get("예술가 되어보기");

    guidesMap.get(4L).updateCategory(artCategory, subCategory5);
    guidesMap.get(19L).updateCategory(artCategory, subCategory5);
    guidesMap.get(20L).updateCategory(artCategory, subCategory5);

    //자연
    MainCategory natureCategory = mainCategoryKorNameMap.get("자연");
    SubCategory subCategory6 = subCategoryKorNameMap.get("도심 속 자연 둘러보기");

    guidesMap.get(12L).updateCategory(natureCategory, subCategory6);
    guidesMap.get(17L).updateCategory(natureCategory, subCategory6);

    //일상 속 힐링
    MainCategory excursionCategory = mainCategoryKorNameMap.get("일상 속 힐링");
    SubCategory subCategory7 = subCategoryKorNameMap.get("서울에서 즐기는 하루");

    guidesMap.get(3L).updateCategory(excursionCategory, subCategory7);
    guidesMap.get(10L).updateCategory(excursionCategory, subCategory7);
    guidesMap.get(6L).updateCategory(excursionCategory, subCategory7);
    guidesMap.get(8L).updateCategory(excursionCategory, subCategory7);
  }
}
