package team_mic.here_and_there.backend.audio_guide.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team_mic.here_and_there.backend.audio_guide.domain.entity.*;
import team_mic.here_and_there.backend.audio_guide.domain.repository.*;
import team_mic.here_and_there.backend.common.domain.Language;
import team_mic.here_and_there.backend.location_tag.domain.entity.AudioGuideTag;
import team_mic.here_and_there.backend.location_tag.domain.entity.Tag;
import team_mic.here_and_there.backend.location_tag.domain.repository.AudioGuideTagRepository;
import team_mic.here_and_there.backend.location_tag.domain.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class AudioGuideDataService {

    private final AudioGuideRepository audioGuideRepository;
    private final AudioGuideLanguageContentRepository audioGuideLanguageContentRepository;
    private final AudioTrackRepository audioTrackRepository;
    private final AudioTrackLanguageContentRepository audioTrackLanguageContentRepository;
    private final AudioGuideTrackContainerRepository audioGuideTrackContainerRepository;

    private static final String AWS_CLOUD_FRONT_URL_PREFIX = "http://d2gqdan1weqbf0.cloudfront.net";

    public void insertGongneungGuides() {
        AudioGuide audioGuide = audioGuideRepository.save(
                AudioGuide.builder()
                        .distance("4.9km")
                        .estimatedTravelTime("2 hours")
                        .location("Nowon-gu")
                        .images(new ArrayList<String>() {{
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Gongneung/1.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Gongneung/2.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Gongneung/3.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Gongneung/4.jpg");
                        }})
                        .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.KOREAN)
                .category(AudioGuideCategory.MUSIC.getDatabaseKoreanName())
                .overviewDescription("방탄소년단의 RM 팬이라면 RM이 공릉을 방문하여 사진을 찍어 트위터에 올린 사진들을 보셨을 거예요. 공릉은 사람들에게 많이 알려진 유명한 지역은 아니지만, 사람들 사이에서 입소문을 타고 점점 뜨고 있는 지역이에요. 공릉에 대한 소개와 RM이 다녀갔던 장소, 사진찍은 장소까지 다 알려드릴게요. 중간중간 RM에 관한 TMI도 많으니 놓치지 마세요!")
                .playingCount("0")
                .title("RM이 사랑하는 아름다운 동네")
                .viewCount("0")
                .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.ENGLISH)
                .category(AudioGuideCategory.MUSIC.getDatabaseEnglishName())
                .overviewDescription("If you're a fan of RM of BTS, you've probably seen the photos RM posted on his Twitter after visiting Gongneung. Gongneung is not a well-known and popular area, but it's getting popular among people. I'll give you an introduction to Gongneung, and tell you the places where RM went and took pictures. Also, there are some interesting information and facts about RM in between, so don't miss them!")
                .playingCount("0")
                .title("The Neighborhood RM Loves")
                .viewCount("0")
                .build());
    }

    public void insertGongneungTracks() {
        AudioGuide audioGuide = audioGuideRepository.findById(1L).orElseThrow(() -> new NoSuchElementException());

        List<AudioTrack> list = new ArrayList<>();

        AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.084547)
                .locationLatitude(37.620615)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/1-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/1-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/1-3.png");
                }})
                .build());
        list.add(track1);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track1)
                .title("공릉 소개")
                .runningTime("00:28")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/1.+%ED%99%94%EB%9E%91%EB%8C%80%EC%97%AD.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track1)
                .title("About Gongneung")
                .runningTime("00:34")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/1_+Hwarangdae+Station.mp3")
                .build());

        AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.090499)
                .locationLatitude(37.623106)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/2-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/2-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/2-3.jpg");
                }})
                .build());
        list.add(track2);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track2)
                .title("화랑대 철도공원")
                .runningTime("02:34")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/2.+%ED%99%94%EB%9E%91%EB%8C%80+%EC%B2%A0%EB%8F%84%EA%B3%B5%EC%9B%90.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track2)
                .title("Hwarangdae Railroad Park")
                .runningTime("01:52")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/2_+Hwarangdae+Railroad+Park.mp3")
                .build());

        AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.093234)
                .locationLatitude(37.624266)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/3-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/3-2.jpg");
                }})
                .build());
        list.add(track3);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track3)
                .title("화랑대 역사 전시관")
                .runningTime("02:34")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/3.+%ED%99%94%EB%9E%91%EB%8C%80+%EC%97%AD%EC%82%AC+%EC%A0%84%EC%8B%9C%EA%B4%80.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track3)
                .title("Hwarangdae History Museum")
                .runningTime("02:06")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/3_+Hwarangdae+History+Museum.mp3")
                .build());

        AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.079982)
                .locationLatitude(37.619932)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/4-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/4-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/4-3.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/4-4.jpg");
                }})
                .build());
        list.add(track4);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track4)
                .title("도깨비 시장 가는 길")
                .runningTime("02:34")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/4.+%EB%8F%84%EA%B9%A8%EB%B9%84%EC%8B%9C%EC%9E%A5+%EA%B0%80%EB%8A%94+%EA%B8%B8.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track4)
                .title("On the Way to Traditional Market")
                .runningTime("02:10")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/4_+On+the+way+to+Traditional+Market.mp3")
                .build());
        AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.077689)
                .locationLatitude(37.622852)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/5-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/5-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/5-3.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/5-4.png");
                }})
                .build());
        list.add(track5);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track5)
                .title("도깨비 시장")
                .runningTime("01:46")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/5.+%EB%8F%84%EA%B9%A8%EB%B9%84%EC%8B%9C%EC%9E%A5.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track5)
                .title("Dokkaebi Traditional Market")
                .runningTime("02:00")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/5_+Dokkaebi+Traditional+Market.mp3")
                .build());

        AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.077639)
                .locationLatitude(37.623791)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/6-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/6-2.jpg");
                }})
                .build());
        list.add(track6);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track6)
                .title("공트럴파크")
                .runningTime("01:03")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/6.+%EA%B3%B5%ED%8A%B8%EB%9F%B4%ED%8C%8C%ED%81%AC.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track6)
                .title("Gyeongchun line Forest Trail")
                .runningTime("00:57")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/6_+Gyeongchun+line+Forest+Trail.mp3")
                .build());

        AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.077634)
                .locationLatitude(37.623816)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/7-1.jpg");
                }})
                .build());
        list.add(track7);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track7)
                .title("RM 벤치 앞")
                .runningTime("02:12")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/7.+RM+%EB%B2%A4%EC%B9%98+%EC%95%9E.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track7)
                .title("In Front of RM Bench")
                .runningTime("01:57")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/7_+In+front+of+RM+bench.mp3")
                .build());

        AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.076780)
                .locationLatitude(37.626721)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/8-1.png");
                }})
                .build());
        list.add(track8);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track8)
                .title("RM 벽화 앞")
                .runningTime("01:23")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/8.+RM+%EB%B2%BD%ED%99%94+%EC%95%9E.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track8)
                .title("In Front of Wall Painting")
                .runningTime("01:22")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/8_+In+front+of+wall+painting.mp3")
                .build());

        AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.066771)
                .locationLatitude(37.640755)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/9-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/9-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/9-3.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Gongneung/9-4.jpg");
                }})
                .build());
        list.add(track9);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track9)
                .title("북서울미술관")
                .runningTime("01:55")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/kor/9.+%EB%B6%81%EC%84%9C%EC%9A%B8%EB%AF%B8%EC%88%A0%EA%B4%80.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track9)
                .title("The Buk-Seoul Museum of Art")
                .runningTime("01:48")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Gongneung/eng/9_+Buk+Seoul+Museum+of+Art.mp3")
                .build());

        for (int order = 0; order < list.size(); order++) {
            audioGuideTrackContainerRepository.save(
                    AudioGuideTrackContainer.builder()
                            .audioTrack(list.get(order))
                            .orderNumber(order + 1)
                            .audioGuide(audioGuide)
                            .build());
        }
    }

    public void insertNamsanGuides() {
        AudioGuide audioGuide = audioGuideRepository.save(
                AudioGuide.builder()
                        .distance("6.4km")
                        .estimatedTravelTime("2hours 20minutes")
                        .location("Yongsan-gu, Seoul")
                        .images(new ArrayList<String>() {{
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Namsan/1.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Namsan/2.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Namsan/3.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Namsan/4.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Namsan/5.png");
                        }})
                        .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.KOREAN)
                .category(AudioGuideCategory.SHOPPING.getDatabaseKoreanName())
                .overviewDescription("서울하면 남산타워 다들 들어보셨죠? 남산타워는 k-드라마에서도 많이 나왔던 유명한 촬영지인데요. 이번 코스는 서울의 랜드마크인 남산입니다. 남산은 서울 중심에 있어 어디서든 방문하기 좋은 지역입니다. 주변에 명동도 있어 쇼핑하기 참 좋아요. 이번 코스에서는 남산은 어떤 지역인지, 명동에서 쇼핑하기 좋은 브랜드 등 다양한 설명과 꿀팁을 제공할 예정이니 놓치지 마세요!")
                .playingCount("0")
                .title("서울의 랜드마크")
                .viewCount("0")
                .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.ENGLISH)
                .category(AudioGuideCategory.SHOPPING.getDatabaseEnglishName())
                .overviewDescription("When you think about attractions in Seoul, what pops into your mind? I'm sure one of them is the Namsan Tower. It is a famous K-drama filming spot. This course is Namsan, a landmark of Seoul. Namsan is located in the center of Seoul, so you can visit anywhere. And also Myeong-dong is nearby, it's great to go shopping. Don't miss this course! I will provide a lot of tips and explanations on Namsan, and recommend nice brands to shop in Myeong-dong!")
                .playingCount("0")
                .title("A Landmark of Seoul")
                .viewCount("0")
                .build());
    }

    public void insertNamsanTracks() {
        AudioGuide audioGuide = audioGuideRepository.findById(2L).orElseThrow(() -> new NoSuchElementException());

        List<AudioTrack> list = new ArrayList<>();

        AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.994916)
                .locationLatitude(37.561284)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/1-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/1-2.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/1-3.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/1-4.png");
                }})
                .build());
        list.add(track1);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track1)
                .title("남산소개")
                .runningTime("01:53")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/1.+%EB%82%A8%EC%82%B0+%EC%86%8C%EA%B0%9C+%EC%B5%9C%EC%A2%85.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track1)
                .title("Namsan")
                .runningTime("01:56")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/eng/01+Namsan.mp3")
                .build());

        AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.991334)
                .locationLatitude(37.550724)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/2-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/2-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/2-3.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/2-4.jpg");
                }})
                .build());
        list.add(track2);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track2)
                .title("남산타워")
                .runningTime("02:56")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/2.+%EB%82%A8%EC%82%B0%ED%83%80%EC%9B%8C+%EC%B5%9C%EC%A2%85.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track2)
                .title("Namsan Tower")
                .runningTime("03:01")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/eng/02+Namsan+Tower.mp3")
                .build());

        AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.987548)
                .locationLatitude(37.551937)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/3-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/3-2.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/3-3.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/3-4.jpeg");
                }})
                .build());
        list.add(track3);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track3)
                .title("봉수대")
                .runningTime("01:34")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/3.+%EB%B4%89%EC%88%98%EB%8C%80+%EC%B5%9C%EC%A2%85.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track3)
                .title("Beacon Fire Station")
                .runningTime("01:43")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/eng/03+Beacon+Fire+Station.mp3")
                .build());

        AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.984041)
                .locationLatitude(37.556582)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/4-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/4-2.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/4-3.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/4-4.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/4-5.png");
                }})
                .build());
        list.add(track4);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track4)
                .title("명동")
                .runningTime("02:22")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/4.+%EB%82%A8%EC%82%B0%ED%83%80%EC%9B%8C%EC%9D%98+%EB%B9%84%EB%B0%80%EA%B3%BC+%EB%AA%85%EB%8F%99.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track4)
                .title("Myeong-dong")
                .runningTime("02:25")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/eng/04+Myeongdong.mp3")
                .build());
        AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.983569)
                .locationLatitude(37.560644)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/5-1.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/5-2.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/5-3.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/5-4.jpeg");
                }})
                .build());
        list.add(track5);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track5)
                .title("쇼핑 브랜드 추천")
                .runningTime("04:00")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/5.+%EC%87%BC%ED%95%91+%EB%B8%8C%EB%9E%9C%EB%93%9C+%EC%B6%94%EC%B2%9C.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track5)
                .title("Shopping Recommendations")
                .runningTime("03:48")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/eng/05+Shopping+Recommendations.mp3")
                .build());

        AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.984506)
                .locationLatitude(37.563627)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/6-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/6-2.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/6-3.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/6-4.png");
                }})
                .build());
        list.add(track6);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track6)
                .title("명동예술극장")
                .runningTime("01:06")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/6.+%EB%AA%85%EB%8F%99%EC%98%88%EC%88%A0%EA%B7%B9%EC%9E%A5.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track6)
                .title("Myeongdong Theater")
                .runningTime("00:55")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/eng/06+Myeongdong+Theater.mp3")
                .build());

        AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.986558)
                .locationLatitude(37.564299)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/7-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/7-2.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/7-3.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/7-4.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Namsan/7-5.png");
                }})
                .build());
        list.add(track7);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track7)
                .title("명동성당")
                .runningTime("02:29")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Namsan/kor/7.+%EB%AA%85%EB%8F%99%EC%84%B1%EB%8B%B9.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track7)
                .title("Myeong-dong Cathedral")
                .runningTime("02:26")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/Namsan/eng/07+Myeongdong+Cathedral.mp3")
                .build());

        for (int order = 0; order < list.size(); order++) {
            audioGuideTrackContainerRepository.save(
                    AudioGuideTrackContainer.builder()
                            .audioTrack(list.get(order))
                            .orderNumber(order + 1)
                            .audioGuide(audioGuide)
                            .build());
        }
    }

    public void insertNoryanjinGuides() {
        AudioGuide audioGuide = audioGuideRepository.save(
                AudioGuide.builder()
                        .distance("3.7km")
                        .estimatedTravelTime("1hour 50minutes")
                        .location("Dongjak-gu, Seoul")
                        .images(new ArrayList<String>() {{
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Noryanjin/1.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Noryanjin/2.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Noryanjin/3.jpg");
                        }})
                        .recommendedAudioGuideIds(new ArrayList<Long>(){{
                          add(10L);
                          add(11L);
                        }})
                        .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.KOREAN)
                .category(AudioGuideCategory.EXCURSION.getDatabaseKoreanName())
                .overviewDescription("한국에서 노량진은 수산시장과 고시촌으로 유명한 곳입니다. 하지만 한강 근처에 있고 곳곳에 공원이 많아 한강과 함께 서울을 여유롭게 구경할 수 있는 곳이 많습니다. 수산시장에서 수산물을 쇼핑하는 팁부터 숨겨진 명소까지! 한국사람들도 잘 모르는 노량진에 대한 모든 것을 알려드릴게요!")
                .playingCount("0")
                .title("서울을 한눈에 볼 수 있는 숨겨진 명소")
                .viewCount("0")
                .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.ENGLISH)
                .category(AudioGuideCategory.EXCURSION.getDatabaseEnglishName())
                .overviewDescription("Noryangjin is famous for its fish market and gosichon, a town full of cram school for the exam. However, there are many places where you can enjoy the view of Seoul because it is near the Han River and there are many parks. From tips on buying seafood at the fish market to hidden attractions! I'll tell you everything about Noryangjin that even Koreans don't know!")
                .playingCount("0")
                .title("A Hidden Attractive Area Where You Can See in One Glance the Whole Area of Seoul")
                .viewCount("0")
                .build());
    }

    public void insertNoryanjinTracks() {
        AudioGuide audioGuide = audioGuideRepository.findById(3L).orElseThrow(() -> new NoSuchElementException());

        List<AudioTrack> list = new ArrayList<>();

        AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.940036)
                .locationLatitude(37.513695)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/1-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/1-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/1-3.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/1-4.jpg");
                }})
                .build());
        list.add(track1);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track1)
                .title("노량진 수산시장")
                .runningTime("03:06")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/kor/1.+%EB%85%B8%EB%9F%89%EC%A7%84+%EC%88%98%EC%82%B0%EC%8B%9C%EC%9E%A5.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track1)
                .title("Noryangjin Fisheries Wholesale Market")
                .runningTime("03:01")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/eng/01+Noryangjin+Fisheries+Wholesale+Market.mp3")
                .build());

        AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.942139)
                .locationLatitude(37.513502)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/2-1.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/2-2.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/2-3.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/2-4.jpeg");
                }})
                .build());
        list.add(track2);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track2)
                .title("노량진 컵밥거리")
                .runningTime("02:04")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/kor/2.+%EB%85%B8%EB%9F%89%EC%A7%84+%EC%BB%B5%EB%B0%A5%EA%B1%B0%EB%A6%AC.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track2)
                .title("Noryangjin Cup Rice Alley")
                .runningTime("02:03")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/eng/02+Noryangjin+Cup+Rice+Alley.mp3")
                .build());

        AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.946253)
                .locationLatitude(37.513368)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/3-1.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/3-2.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/3-3.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/3-4.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/3-5.jpeg");
                }})
                .build());
        list.add(track3);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track3)
                .title("사육신역사공원")
                .runningTime("05:41")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/kor/3.+%EC%82%AC%EC%9C%A1%EC%8B%A0+%EC%97%AD%EC%82%AC%EA%B3%B5%EC%9B%90.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track3)
                .title("Sayuksin Park")
                .runningTime("05:25")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/eng/03+Sayuksin+Park.mp3")
                .build());

        AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.948834)
                .locationLatitude(37.514049)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/4-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/4-2.png");
                }})
                .build());
        list.add(track4);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track4)
                .title("사육신공원 전망대")
                .runningTime("01:20")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/kor/4.+%EC%82%AC%EC%9C%A1%EC%8B%A0+%EC%97%AD%EC%82%AC%EA%B3%B5%EC%9B%90+%EC%A0%84%EB%A7%9D%EB%8C%80.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track4)
                .title("Observatory in Sayuksin Park")
                .runningTime("01:07")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/eng/04+Observatory+in+Sayuksin+Park.mp3")
                .build());
        AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.955631)
                .locationLatitude(37.513504)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/5-1.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/5-2.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/5-3.jpeg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/5-4.jpg");
                }})
                .build());
        list.add(track5);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track5)
                .title("한강대교")
                .runningTime("01:00")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/kor/5.+%ED%95%9C%EA%B0%95%EB%8C%80%EA%B5%90.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track5)
                .title("Han River Bridge")
                .runningTime("00:52")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/eng/05+Han+River+Bridge.mp3")
                .build());

        AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.958179)
                .locationLatitude(37.516898)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/6-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/6-2.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/6-3.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/6-4.jpg");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Noryanjin/6-5.jpg");
                }})
                .build());
        list.add(track6);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track6)
                .title("노들섬")
                .runningTime("02:02")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/kor/6.+%EB%85%B8%EB%93%A4%EC%84%AC.m4a")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track6)
                .title("Nodeul Island")
                .runningTime("01:45")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Noryanjin/eng/06+Nodel+Island.mp3")
                .build());

        for (int order = 0; order < list.size(); order++) {
            audioGuideTrackContainerRepository.save(
                    AudioGuideTrackContainer.builder()
                            .audioTrack(list.get(order))
                            .orderNumber(order + 1)
                            .audioGuide(audioGuide)
                            .build());
        }
    }

    public void insertDaehakRoGuides() {
        AudioGuide audioGuide = audioGuideRepository.save(
                AudioGuide.builder()
                        .distance("2.7km")
                        .estimatedTravelTime("2 hours")
                        .location("Jongno-gu, Seoul")
                        .images(new ArrayList<String>() {{
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Daehak-Ro/1.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Daehak-Ro/2.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Daehak-Ro/3.jpg");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Daehak-Ro/4.jpg");
                        }})
                        .recommendedAudioGuideIds(new ArrayList<Long>(){{
                          add(5L);
                        }})
                        .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.KOREAN)
                .category(AudioGuideCategory.ART.getDatabaseKoreanName())
                .overviewDescription("연극의 중심, 한국의 브로드웨이 대학로에 오신 걸 환영합니다! 대학로라는 명칭은 예전에 여기에 서울대학교 인문사회대학, 자연과학대학, 법학대학이 있었기 때문인데요. 여기 있던 서울대학교 건물들이 1975년에 지금의 서울대학교 자리로 다 이동하고 서울대 의과대학과 대학병원만 남아 있습니다. 서울대학교 이전 후 빈 땅이 생겼고 이곳에 아르코 예술극장이 생기면서 파랑새극장, 동숭아트센터, 연우소극장 등 극장들이 많이 문을 열게 되었습니다. ")
                .playingCount("0")
                .title("연극의 중심")
                .viewCount("0")
                .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.ENGLISH)
                .category(AudioGuideCategory.ART.getDatabaseEnglishName())
                .overviewDescription("Welcome to Korean Broadway, Daehak-Ro, the center for theatrical performances! The place was named Daehak-Ro because there used to be Seoul National University's main campus(College of Humanities and Sciences and College of Law) in this area. Since most colleges of the university relocated to the new Gwanak Campus in 1975, only College of Medicine and Seoul national university hospital have remained there. The relocation made empty lot, and it was developed to be a place for theatrical performances with the new buildings such as the Arko Arts Theater , Blue Bird Theater, Dongsung Art Center, and Yeonwoo Theater.")
                .playingCount("0")
                .title("The Center for Theatrical Performances")
                .viewCount("0")
                .build());
    }

    public void insertDaehakRoTracks() {
        AudioGuide audioGuide = audioGuideRepository.findById(4L).orElseThrow(() -> new NoSuchElementException());

        List<AudioTrack> list = new ArrayList<>();

        AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.001958)
                .locationLatitude(37.581565)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/1-1.png");
                }})
                .build());
        list.add(track1);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track1)
                .title("대학로")
                .runningTime("03:14")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/kor/1.+%EB%8C%80%ED%95%99%EB%A1%9C+%EC%86%8C%EA%B0%9C.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track1)
                .title("Daehak-Ro")
                .runningTime("03:11")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/eng/1_+Daehak-Ro.mp3")
                .build());

        AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.942139)
                .locationLatitude(37.513502)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/2-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/2-2.png");
                }})
                .build());
        list.add(track2);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track2)
                .title("마로니에공원")
                .runningTime("01:37")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/kor/2.+%EB%A7%88%EB%A1%9C%EB%8B%88%EC%97%90+%EA%B3%B5%EC%9B%90.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track2)
                .title("Marronnier Park")
                .runningTime("01:44")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/eng/2_+Marronnier+Park.mp3")
                .build());

        AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.007523)
                .locationLatitude(37.580588)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/3-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/3-2.png");
                }})
                .build());
        list.add(track3);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track3)
                .title("낙산공원")
                .runningTime("00:39")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/kor/3.+%EB%82%99%EC%82%B0%EA%B3%B5%EC%9B%90.MP3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track3)
                .title("Naksan Park")
                .runningTime("00:48")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/eng/3_+Naksan+Park.mp3")
                .build());

        AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.001638)
                .locationLatitude(37.581888)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/4-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/4-2.png");
                }})
                .build());
        list.add(track4);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track4)
                .title("학림다방")
                .runningTime("01:47")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/kor/4.+%ED%95%99%EB%A6%BC%EB%8B%A4%EB%B0%A9.MP3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track4)
                .title("Hakrim Dabang")
                .runningTime("01:39")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/eng/4_+Hakrim+Dabang.mp3")
                .build());

        AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.000802)
                .locationLatitude(37.583500)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/5-1.png");
                }})
                .build());
        list.add(track5);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track5)
                .title("대명거리")
                .runningTime("00:38")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/kor/5.+%EB%8C%80%EB%AA%85%EA%B1%B0%EB%A6%AC.MP3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track5)
                .title("Daemyung street")
                .runningTime("00:40")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/eng/5_+Daemyung+street.mp3")
                .build());

        AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(126.995963)
                .locationLatitude(37.585711)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/6-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/6-2.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Daehak-Ro/6-3.png");
                }})
                .build());
        list.add(track6);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track6)
                .title("성균관")
                .runningTime("03:42")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/kor/6.+%EC%84%B1%EA%B7%A0%EA%B4%80.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track6)
                .title("Sungkyunkwan")
                .runningTime("03:52")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Daehak-Ro/eng/6_+Sungkyunkwan.mp3")
                .build());

        for (int order = 0; order < list.size(); order++) {
            audioGuideTrackContainerRepository.save(
                    AudioGuideTrackContainer.builder()
                            .audioTrack(list.get(order))
                            .orderNumber(order + 1)
                            .audioGuide(audioGuide)
                            .build());
        }
    }

    public void insertDongdaemunGuides() {
        AudioGuide audioGuide = audioGuideRepository.save(
                AudioGuide.builder()
                        .distance("3.9km")
                        .estimatedTravelTime("2 hours")
                        .location("Jongno-gu, Seoul")
                        .images(new ArrayList<String>() {{
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Dongdaemun/1.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Dongdaemun/2.png");
                            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Dongdaemun/3.png");
                        }})
                        .recommendedAudioGuideIds(new ArrayList<Long>(){{
                          add(4L);
                        }})
                        .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.KOREAN)
                .category(AudioGuideCategory.HISTORY.getDatabaseKoreanName())
                .overviewDescription("동대문은 한국의 보물 1호로, 조선시대 때 성곽의 동쪽에 있던 문입니다. 조선시대때는 한양도성의 동서남북에 4개의 문이 있었는데 동대문만 유일하게 조선시대때 지어진 그대로 남아있습니다. 동대문 바깥쪽에 반달모양으로 쌓은 성이 있는데, 이것을 '옹성'이라고 하며 군사방어용으로 만든 것입니다.")
                .playingCount("0")
                .title("조선의 성곽을 따라 산책하기")
                .viewCount("0")
                .build());

        audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
                .audioGuide(audioGuide)
                .language(Language.ENGLISH)
                .category(AudioGuideCategory.HISTORY.getDatabaseEnglishName())
                .overviewDescription("Dongdaemun is Korea's No. 1 treasure, and it was located on the east side of Seoul fortress wall during the Joseon Dynasty. There are four large gates in the east, west, north, and south of the fortress, and Dongdaemun is the only gate that remained the same without damage since it was built in Joseon Dynasty. There is a half-moon-shaped fortress outside Dongdaemun, which is called 'Ongseong' and was built for military defense. ")
                .playingCount("0")
                .title("Walk along the Joseon Castle")
                .viewCount("0")
                .build());
    }

    public void insertDongdaemunTracks() {
        AudioGuide audioGuide = audioGuideRepository.findById(5L).orElseThrow(() -> new NoSuchElementException());

        List<AudioTrack> list = new ArrayList<>();

        AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.009207)
                .locationLatitude(37.571656)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/1-1.png");
                }})
                .build());
        list.add(track1);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track1)
                .title("동대문")
                .runningTime("01:27")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/kor/1.+%EB%8F%99%EB%8C%80%EB%AC%B8.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track1)
                .title("Dongdaemun")
                .runningTime("01:05")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/eng/1+Dongdaemun.mp3")
                .build());

        AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.008818)
                .locationLatitude(37.574544)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/2-1.png");
                }})
                .build());
        list.add(track2);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track2)
                .title("서울성곽")
                .runningTime("00:38")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/kor/2.+%EC%84%9C%EC%9A%B8%EC%84%B1%EA%B3%BD.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track2)
                .title("Fortress Wall of Seoul")
                .runningTime("00:45")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/eng/2+Fortress+Wall+of+Seoul.mp3")
                .build());

        AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.012973)
                .locationLatitude(37.581120)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/3-1.png");
                }})
                .build());
        list.add(track3);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track3)
                .title("비우당과 자주동샘")
                .runningTime("02:45")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/kor/3.+%EB%B9%84%EC%9A%B0%EB%8B%B9%EA%B3%BC+%EC%9E%90%EC%A3%BC%EB%8F%99%EC%83%98.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track3)
                .title("Biudang House and Jajidongcheon Well")
                .runningTime("03:10")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/eng/3+Biudang+House+and+Jajidongcheon+.mp3")
                .build());

        AudioTrack track4 = audioTrackLanguageContentRepository.findByTitle("낙산공원").orElseThrow(NoSuchElementException::new)
            .getAudioTrack();
        list.add(track4);

        AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.007052)
                .locationLatitude(37.580388)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/5-1.png");
                }})
                .build());
        list.add(track5);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track5)
                .title("낙산전시관")
                .runningTime("00:17")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/kor/5.+%EB%82%99%EC%82%B0%EC%A0%84%EC%8B%9C%EA%B4%80.MP3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track5)
                .title("Naksan exhibition hall")
                .runningTime("00:15")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/eng/5+Naksan+Exhibition+Hall.mp3")
                .build());

        AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
                .locationLongitude(127.006278)
                .locationLatitude(37.578325)
                .images(new ArrayList<String>() {{
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/6-1.png");
                    add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/6-2.png");
                }})
                .build());
        list.add(track6);
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.KOREAN)
                .audioTrack(track6)
                .title("이화장")
                .runningTime("00:52")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/kor/6.+%EC%9D%B4%ED%99%94%EC%9E%A5.mp3")
                .build());
        audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
                .language(Language.ENGLISH)
                .audioTrack(track6)
                .title("Iwhajang")
                .runningTime("01:01")
                .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/eng/6+Ihwajang.mp3")
                .build());

      AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
          .locationLongitude(127.007109)
          .locationLatitude(37.577892)
          .images(new ArrayList<String>() {{
            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/7-1.png");
            add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Dongdaemun/7-2.png");
          }})
          .build());
      list.add(track7);
      audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
          .language(Language.KOREAN)
          .audioTrack(track7)
          .title("이화동 벽화마을")
          .runningTime("00:49")
          .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/kor/7.+%EC%9D%B4%ED%99%94%EB%B2%BD%ED%99%94%EB%A7%88%EC%9D%84.mp3")
          .build());
      audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
          .language(Language.ENGLISH)
          .audioTrack(track7)
          .title("Ihwa Mural Village")
          .runningTime("00:57")
          .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Dongdaemun/eng/7+Ihwa+Mural+Village.mp3")
          .build());

        for (int order = 0; order < list.size(); order++) {
            audioGuideTrackContainerRepository.save(
                    AudioGuideTrackContainer.builder()
                            .audioTrack(list.get(order))
                            .orderNumber(order + 1)
                            .audioGuide(audioGuide)
                            .build());
        }
    }

  public void insertMangwonGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("3.8km")
            .estimatedTravelTime("2 hours")
            .location("Mapo-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Mangwon/1.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Mangwon/2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Mangwon/3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Mangwon/4.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Mangwon/5.jpg");
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.EXCURSION.getDatabaseKoreanName())
        .overviewDescription("홍대와 합정의 옆동네인 망원은 홍대와는 사뭇 다른 분위기를 풍기며 사람들의 입소문을 타고 유명해진 동네입니다. 먹거리, 볼거리가 많은 망원시장과 아기자기한 소품샵과 맛집들이 골목골목 숨어있어 느긋하게 탐험하는 재미가 있어요. 이 오디오 가이드는 망원역부터 시작해서 망원정, 망리단길, 망원시장, 망원한강공원까지 둘러볼 수 있는 코스입니다.  망원에 대한 소개부터 망원시장과 망원한강공원의 꿀팁까지 알찬 정보를 얻을 수 있을 거예요!")
        .playingCount("0")
        .title("여유로운 동네")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.EXCURSION.getDatabaseEnglishName())
        .overviewDescription("Mangwon, next to Hongdae and Hapjeong, has become famous through social media. There are many things to explore such as Mangwon Market, which has a lot of food and things to see, and cute prop shops and restaurants hidden in alleys. The audio guide offers a course including Mangwon Station, Mangwonjeong, Mangnidan-gil, Mangwon Market and Mangwon Hangang Park. From the introduction of Mangwon to the great tips of Mangwon Market and Mangwon Han River Park, you will get helpful information.")
        .playingCount("0")
        .title("Relaxing town")
        .viewCount("0")
        .build());
  }

  public void insertMangwonTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(6L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.910100)
        .locationLatitude(37.555900)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/1-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/1-2.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("망원 소개")
        .runningTime("01:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/1.+%EB%A7%9D%EC%9B%90%EB%8F%99+%EC%86%8C%EA%B0%9C.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Mangwon")
        .runningTime("01:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/01+Mangwon.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.905891)
        .locationLatitude(37.550279)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/2-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/2-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/2-3.jpg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("망원정")
        .runningTime("02:39")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/2.+%EB%A7%9D%EC%9B%90%EC%A0%95.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Mangwonjeong Pavilion Site")
        .runningTime("02:49")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/02+Mangwonjeong+Pavilion+Site.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.906106)
        .locationLatitude(37.553311)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/3-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/3-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/3-3.jpg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("망리단길")
        .runningTime("01:29")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/3+%EB%A7%9D%EB%A6%AC%EB%8B%A8%EA%B8%B8%EC%97%90+%EB%8C%80%ED%95%98%EC%97%AC.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Mangnidangil")
        .runningTime("01:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/03+Mangnidan-gil.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.905593)
        .locationLatitude(37.557412)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/4-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/4-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/4-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/4-4.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/4-5.jpg");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("망원시장")
        .runningTime("01:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/4.+%EB%A7%9D%EC%9B%90%EC%8B%9C%EC%9E%A5%EC%97%90+%EB%8C%80%ED%95%9C+%EC%86%8C%EA%B0%9C.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Mangwon Market")
        .runningTime("03:29")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/04+Mangwon+Market.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.898808)
        .locationLatitude(37.556081)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/5-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/5-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/5-4.jpg");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("망원한강공원")
        .runningTime("01:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/5.+%EB%A7%9D%EC%9B%90%ED%95%9C%EA%B0%95%EA%B3%B5%EC%9B%90.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Mangwon Hangang Park")
        .runningTime("01:06")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/05+Mangwon+Hangang+Park.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.896318)
        .locationLatitude(37.553918)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Mangwon/6-1.jpg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("서울함공원")
        .runningTime("01:41")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/kor/6.+%EC%84%9C%EC%9A%B8%ED%95%A8+%EA%B3%B5%EC%9B%90.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Seoul Battleship Park")
        .runningTime("01:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Mangwon/eng/06+Seoul+Battleship+park.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertBukchonGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("2.6km")
            .estimatedTravelTime("2 hours")
            .location("Jongno-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Bukchon/1.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Bukchon/2.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Bukchon/3.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Bukchon/4.jpeg");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(13L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.HISTORY.getDatabaseKoreanName())
        .overviewDescription("한옥의 정취를 느낄 수 있는 마을, 북촌에 오신 걸 환영합니다!  '북촌'은 청계천과 종로의 윗동네라는 뜻에서 '북촌(North Village)'이라고 불리어졌습니다. 북촌은 과거 조선시대 양반층의 주거지로서 아직까지도 아름다운 골목길과 한옥들을 그대로 보존하고 있어 간접적으로나마 조선시대의 분위기를 느낄 수 있는 곳이며, 현재까지도 많은 사람들에게 사랑을 받고 있는 관광지 입니다. 모두 함께 아름다운 북촌 한옥마을을 구경하러 떠나볼까요?")
        .playingCount("0")
        .title("한옥의 정취를 느낄 수 있는 마을")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.HISTORY.getDatabaseEnglishName())
        .overviewDescription("Welcome to Bukchon, a village where you can enjoy the peaceful ambience of hanok! 'Bukchon' ,which means 'North Village', was named this way because it lies north of  Cheonggyecheon Stream and Jongno area. Bukchon used to be a residential area for Yangban the ruling class of the Joseon Dynasty. Beautiful alleys and hanoks are still well preseved so you can feel the atmosphere of the Joseon Dynasty here. Because of these, It is still loved by many people. Let's go to see the beautiful Bukchon Hanok Village.")
        .playingCount("0")
        .title("A Village Where You Can Enjoy the Peaceful Ambience of Hanok")
        .viewCount("0")
        .build());
  }

  public void insertBukchonTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(7L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.986045)
        .locationLatitude(37.577182)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/1-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/1-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/1-3.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("북촌에 대한 전반적인 소개")
        .runningTime("01:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/1.+%EB%B6%81%EC%B4%8C%EC%97%90+%EB%8C%80%ED%95%9C+%EC%A0%84%EB%B0%98%EC%A0%81%EC%9D%B8+%EC%86%8C%EA%B0%9C.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Bukchon")
        .runningTime("01:48")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/01+Bukchon.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.986701)
        .locationLatitude(37.578952)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/2-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/2-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/2-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/2-5.jpeg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("북촌 한옥마을")
        .runningTime("01:54")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/2.+%EB%B6%81%EC%B4%8C%ED%95%9C%EC%98%A5%EB%A7%88%EC%9D%84.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Bukchon Hanok Village")
        .runningTime("02:08")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/02+Bukchon+Hanok+Village.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.986786)
        .locationLatitude(37.582091)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/3-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/3-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/3-3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/3-4.png");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("한옥에 대한 설명")
        .runningTime("02:11")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/3.+%ED%95%9C%EC%98%A5%EC%97%90+%EB%8C%80%ED%95%9C+%EC%86%8C%EA%B0%9C.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Hanok Introduction")
        .runningTime("01:55")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/03+Hankok+Introduction.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.985828)
        .locationLatitude(37.582679)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/4-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/4-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/4-3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/4-4.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/4-5.jpeg");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("북촌에 있는 다양한 공방들")
        .runningTime("02:43")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/4.+%EA%B0%80%ED%9A%8C%EB%8F%99+%EB%B0%95%EB%AC%BC%EA%B4%80%EA%B8%B8.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Various Workshops in Bukchon")
        .runningTime("02:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/04+Various+workshops+in+Bukchon.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.985120)
        .locationLatitude(37.581737)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/5-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/5-3.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("가회동 성당 소개")
        .runningTime("02:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/5.+%EA%B0%80%ED%9A%8C%EB%8F%99+%EC%84%B1%EB%8B%B9.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Gahoedong Catholic Church")
        .runningTime("02:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/05+Gahoedong+Catholic+Church.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.983617)
        .locationLatitude(37.582578)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/6-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/6-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/6-4.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/6-5.jpeg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("가회동 골목길과 사진 스팟")
        .runningTime("00:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/6.+%EA%B0%80%ED%9A%8C%EB%8F%99+%EA%B3%A8%EB%AA%A9%EA%B8%B8.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Gahoedong Alleyways and Photo Spots")
        .runningTime("00:46")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/06+Gahoedong+Alleyways+and+photo+spots.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.982171)
        .locationLatitude(37.582346)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/7-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/7-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/7-3.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("소격동의 역사와 설명")
        .runningTime("00:56")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/7.+%EC%86%8C%EA%B2%A9%EB%8F%99.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("History and Introduction of Sogyeok-dong")
        .runningTime("00:39")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/07+History+and+Introduction+of+Sogyeok-dong.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.982503)
        .locationLatitude(37.580334)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/8-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/8-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/8-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/8-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/8-5.png");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("독립운동가의 길과 정독도서관")
        .runningTime("01:54")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/8.+%EB%8F%85%EB%A6%BD%EC%9A%B4%EB%8F%99%EA%B0%80%EC%9D%98+%EA%B8%B8%EA%B3%BC+%EC%A0%95%EB%8F%85%EB%8F%84%EC%84%9C%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("The Road of Independence Activists and Jeongdok Public Library")
        .runningTime("01:42")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/08+The+road+of+independence+activists+and+Jeongdok+Public+Library.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.98211)
        .locationLatitude(37.578768)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/9-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/9-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Bukchon/9-3.png");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("감고당길과 위아영 벽화")
        .runningTime("01:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/kor/9.+%EA%B0%90%EA%B3%A0%EB%8B%B9%EA%B8%B8%EA%B3%BC+%EC%9C%84%EC%95%84%EC%98%81+%EB%B2%BD%ED%99%94.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Gamgodang-gil and \"We are Young\" Graffiti")
        .runningTime("01:22")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Bukchon/eng/09+Gamgodang-gil+and+We+are+young+Graffiti.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }


  public void insertJamsilGoodNeighborhoodGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("6.9km")
            .estimatedTravelTime("2hours 40minutes")
            .location("Songpa-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Jamsil/a_good_neighborhood_for_a_walk/1.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(12L);
              add(9L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.EXCURSION.getDatabaseKoreanName())
        .overviewDescription("잠실에 오신 것을 환영합니다. 잠실에는 휴식과 즐거움을 위한 장소 , 그리고식사를 하고 오락을 즐길 수 있는 번화가가 있어 사람들이 많이 찾는 곳입니다. 잠실은 원래 한강으로 둘러싸여 있던 섬이었습니다. 석촌호수가 강의 일부였죠. 땅을 매립하면서 호수로 남게 되었고 그 위에는 롯데월드가 세워졌어요. 옛날에는 ‘잠실섬’에서 뽕나무를 심고 누에를 길러 실을 만들었어요. 그래서 한자로 ‘누에 잠’에 ‘집 실’을 써서 잠실이라는 이름이 붙여진 것이라고 합니다.")
        .playingCount("0")
        .title("산책하기 좋은 동네")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.EXCURSION.getDatabaseEnglishName())
        .overviewDescription("Welcome to Jamsil. Jamsil is a popular place for a lot of people because it has lots of places for relaxation and pleasure and downtowns where you can eat and enjoy entertainment. Jamsil was originally an island surrounded by Han-river. Seokchon Lake was actually the part of the river. However, when land was reclaimed, it remained as a lake and Lotte World was built on top of it. In the past, people in ‘Jamsil Island’ planted mulberry trees and raised silkworms to make yarn. The name ‘Jamsil’ was made after the meaning of Chinese character ‘house of silkworms.‘ The word ‘Jam’ means silkworms and ‘sil’ means house in here.")
        .playingCount("0")
        .title("A Good Neighborhood for a Walk")
        .viewCount("0")
        .build());
  }

  public void insertJamsilGoodNeighborhoodTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(8L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.097590)
        .locationLatitude(37.506711)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/1-1.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("잠실소개")
        .runningTime("01:35")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/1.+%EC%9E%A0%EC%8B%A4%EC%86%8C%EA%B0%9C.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Introduction of Jamsil")
        .runningTime("02:04")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/1_+Introduction+of+Jamsil.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.100769)
        .locationLatitude(37.510220)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/2-1.png");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("석촌호수(서호)")
        .runningTime("02:16")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/2.+%EC%84%9D%EC%B4%8C%ED%98%B8%EC%88%98+(%EC%84%9C%ED%98%B8).mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Seokchon Lake(Seoho)")
        .runningTime("02:34")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/2_+Seokchon+Lake+(Seoho).mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.105809)
        .locationLatitude(37.510542)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/3-1.png");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("석촌호수 카페거리(동호)")
        .runningTime("00:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/3.+%EC%84%9D%EC%B4%8C%ED%98%B8%EC%88%98+%EC%B9%B4%ED%8E%98%EA%B1%B0%EB%A6%AC+(%EB%8F%99%ED%98%B8).MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Seokchon Lake Cafe Street(Dongho)")
        .runningTime("00:54")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/3_+Seokchon+Lake+Cafe+Street.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.107670)
        .locationLatitude(37.512078)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/4-1.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("새내쉼터")
        .runningTime("00:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/4.+%EC%83%88%EB%82%B4%EC%89%BC%ED%84%B0.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Saenae Rest Area")
        .runningTime("00:56")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/4_+Saenae+Rest+Area.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.103232)
        .locationLatitude(37.512788)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/5-1.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("롯데월드타워")
        .runningTime("01:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/5.+%EB%A1%AF%EB%8D%B0%EC%9B%94%EB%93%9C%ED%83%80%EC%9B%8C.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Lotte World Tower")
        .runningTime("02:04")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/5_+Lotte+World+Tower.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.100313)
        .locationLatitude(37.513334)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/6-1.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("잠실 지하상가")
        .runningTime("00:46")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/6.+%EC%9E%A0%EC%8B%A4+%EC%A7%80%ED%95%98%EC%83%81%EA%B0%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Jamsil Underground Shopping Center")
        .runningTime("00:54")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/6_+Jamsil+Underground+Shopping+C.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.107522)
        .locationLatitude(37.514199)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/7-1.jpg");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("방이동 먹자골목")
        .runningTime("00:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/7.+%EB%B0%A9%EC%9D%B4%EB%8F%99+%EB%A8%B9%EC%9E%90%EA%B3%A8%EB%AA%A9.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Bangi-dong Food Alley")
        .runningTime("00:30")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/7_+Bangi-dong+Food+Alley.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.104089)
        .locationLatitude(37.522221)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/8-1.png");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("서울책보고")
        .runningTime("00:58")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/8.+%EC%84%9C%EC%9A%B8%EC%B1%85%EB%B3%B4%EA%B3%A0.mmP3.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Seoul Book Report")
        .runningTime("01:04")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/8_+Seoul+Book+Report.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.102834)
        .locationLatitude(37.524800)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/a_good_neighborhood_for_a_walk/9-1.png");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("잠실한강공원")
        .runningTime("02:16")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/kor/9.+%EC%9E%A0%EC%8B%A4+%ED%95%9C%EA%B0%95%EA%B3%B5%EC%9B%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Jamsil Han-River Park")
        .runningTime("02:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/a_good_neighborhood_for_a_walk/eng/9_+Jamsil+Han-River+Park.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertJamsilSpecialTouristGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("4.1km")
            .estimatedTravelTime("2hours 40minutes")
            .location("Songpa-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Jamsil/seoul_special_tourist_zone/1.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(12L);
              add(8L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.SHOPPING.getDatabaseKoreanName())
        .overviewDescription("잠실에 오신 것을 환영합니다. 잠실에는 휴식과 즐거움을 위한 장소 , 그리고식사를 하고 오락을 즐길 수 있는 번화가가 있어 사람들이 많이 찾는 곳입니다. 잠실은 원래 한강으로 둘러싸여 있던 섬이었습니다. 석촌호수가 강의 일부였죠. 땅을 매립하면서 호수로 남게 되었고 그 위에는 롯데월드가 세워졌어요. 옛날에는 ‘잠실섬’에서 뽕나무를 심고 누에를 길러 실을 만들었어요. 그래서 한자로 ‘누에 잠’에 ‘집 실’을 써서 잠실이라는 이름이 붙여진 것이라고 합니다.")
        .playingCount("0")
        .title("서울 대표 관광특구")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.SHOPPING.getDatabaseEnglishName())
        .overviewDescription("Welcome to Jamsil. Jamsil is a popular place for a lot of people because it has lots of places for relaxation and pleasure and downtowns where you can eat and enjoy entertainment. Jamsil was originally an island surrounded by Han-river. Seokchon Lake was actually the part of the river. However, when land was reclaimed, it remained as a lake and Lotte World was built on top of it. In the past, people in ‘Jamsil Island’ planted mulberry trees and raised silkworms to make yarn. The name ‘Jamsil’ was made after the meaning of Chinese character ‘house of silkworms.‘ The word ‘Jam’ means silkworms and ‘sil’ means house in here.")
        .playingCount("0")
        .title("Seoul Special Tourist Zone")
        .viewCount("0")
        .build());
  }

  public void insertJamsilSpecialTouristTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(9L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackLanguageContentRepository.findByTitle("석촌호수(서호)").orElseThrow(NoSuchElementException::new)
        .getAudioTrack();
    list.add(track1);

    AudioTrack track2 = audioTrackLanguageContentRepository.findByTitle("석촌호수 카페거리(동호)").orElseThrow(NoSuchElementException::new)
        .getAudioTrack();
    list.add(track2);

    AudioTrack track3 = audioTrackLanguageContentRepository.findByTitle("새내쉼터").orElseThrow(NoSuchElementException::new)
        .getAudioTrack();
    list.add(track3);

    AudioTrack track4 = audioTrackLanguageContentRepository.findByTitle("롯데월드타워").orElseThrow(NoSuchElementException::new)
        .getAudioTrack();
    list.add(track4);

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.099741)
        .locationLatitude(37.509211)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/5-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/5-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/5-4.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("롯데월드어드벤처, 민속박물관, 아이스링크")
        .runningTime("04:37")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/seoul_special_tourist_zone/kor/5.+%EB%A1%AF%EB%8D%B0%EC%9B%94%EB%93%9C+%EC%96%B4%EB%93%9C%EB%B2%A4%EC%B2%98+.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("About Lotte World Adventure")
        .runningTime("04:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/seoul_special_tourist_zone/eng/5_+About+Lotte+World+Adventure.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.099648)
        .locationLatitude(37.510583)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/6-2.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("샤롯데시어터")
        .runningTime("00:58")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/seoul_special_tourist_zone/kor/6.+%EC%83%A4%EB%A1%AF%EB%8D%B0+%EC%8B%9C%EC%96%B4%ED%84%B0.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Charlotte Theater")
        .runningTime("00:56")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/seoul_special_tourist_zone/eng/6+Charlotte+Theater.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.113761)
        .locationLatitude(37.517800)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Jamsil/seoul_special_tourist_zone/7-1.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("올림픽공원")
        .runningTime("00:55")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/seoul_special_tourist_zone/kor/7.+%EC%98%AC%EB%A6%BC%ED%94%BD%EA%B3%B5%EC%9B%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Olympic Park")
        .runningTime("00:56")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Jamsil/seoul_special_tourist_zone/eng/7+Olympic+Park.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertYongsanCenterOfSeoulGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("8.3km")
            .estimatedTravelTime("3 hours 30minutes")
            .location("Yongsan-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yongsan/the_center_of_seoul/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yongsan/the_center_of_seoul/2.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yongsan/the_center_of_seoul/3.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(11L);
              add(3L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.EXCURSION.getDatabaseKoreanName())
        .overviewDescription("용산구는 서울의 중심부에 위치해 경제와 교통 문화의 중심지입니다. 고속철도와 중앙역사의 기능을 하고 있습니다. 뿐만 아니라 효창공원, 국립중앙박물관, 전쟁기념관, 한강시민공원 등 도심공원이 많아 주민들이 참 살기 좋은 곳입니다.")
        .playingCount("0")
        .title("서울의 중심")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.EXCURSION.getDatabaseEnglishName())
        .overviewDescription("Welcome to Yongsan, the center of Seoul. Yongsan-gu is the major economic and transportation hub located in the heart of Seoul. It is also a central station, which has a high-speed railroad. In addition, there are lots of nice places such as the National Museum of Korea, The War Memorial of Korea, and many urban parks such as Hyochang Park and Hangang Park, so it is really an amazing place to live in.")
        .playingCount("0")
        .title("The Center of Seoul")
        .viewCount("0")
        .build());
  }

  public void insertYongsanCenterOfSeoulTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(10L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.973883)
        .locationLatitude(37.535407)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/1-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/1-2.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("삼각지역")
        .runningTime("00:43")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/1.+%EC%82%BC%EA%B0%81%EC%A7%80%EC%97%AD.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Samgakji Station")
        .runningTime("01:00")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/1_+Samgakji+Station.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977111)
        .locationLatitude(37.536478)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/2-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/2-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/2-4.png");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("전쟁기념관")
        .runningTime("02:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/2.+%EC%A0%84%EC%9F%81%EA%B8%B0%EB%85%90%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("The War Memorial of Korea")
        .runningTime("03:08")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/2_+The+War+Memorial+of+Korea.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.978816)
        .locationLatitude(37.536810)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/3-1.png");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("전쟁기념관 옥외전시장")
        .runningTime("01:37")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/3.+%EC%A0%84%EC%9F%81%EA%B8%B0%EB%85%90%EA%B4%80+%EC%98%A5%EC%99%B8%EC%A0%84%EC%8B%9C%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("War Memorial of Korea Outdoor Exhibition Hall")
        .runningTime("01:42")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/3_+War+Memorial+of+Korea+Outdoor+Exhibition+Hall.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977822)
        .locationLatitude(37.536083)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/4-1.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("전사자 명비")
        .runningTime("00:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/4.+%EC%A0%84%EC%82%AC%EC%9E%90%EB%AA%85%EB%B9%84.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Warriors’ Death Monument")
        .runningTime("00:58")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/4_+Warriors%E2%80%99+Death+Monument.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.968373)
        .locationLatitude(37.538623)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/5-1.jpg");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("열정도")
        .runningTime("01:43")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/5.+%EC%97%B4%EC%A0%95%EB%8F%84.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Yeoljeongdo Street")
        .runningTime("01:52")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/5_+Yeoljeongdo+Street.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.963418)
        .locationLatitude(37.544887)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/6-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/6-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/6-4.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("효창공원")
        .runningTime("02:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/6.+%ED%9A%A8%EC%B0%BD%EA%B3%B5%EC%9B%90.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Hyochang Park")
        .runningTime("02:08")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/6_+Hyochang+Park.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.961507)
        .locationLatitude(37.545253)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/7-1.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("삼의사묘역")
        .runningTime("03:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/7.+%EC%82%BC%EC%9D%98%EC%82%AC%EB%AC%98%EC%97%AD.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("The 3 Martyr Cemetery")
        .runningTime("04:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/7_+The+3+Martyr+Cemetery.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.959517)
        .locationLatitude(37.544577)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/8-1.png");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("백범기념관")
        .runningTime("02:10")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/8.+%EB%B0%B1%EB%B2%94%EA%B8%B0%EB%85%90%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Kim Koo Museum & Library")
        .runningTime("02:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/8_+Kim+Koo+Museum+_+Library.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.959943)
        .locationLatitude(37.536699)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/9-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/9-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/9-3.jpg");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("용문시장")
        .runningTime("02:24")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/9.+%EC%9A%A9%EB%AC%B8%EC%A0%84%ED%86%B5%EC%8B%9C%EC%9E%A5.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Yongmun Traditional Market")
        .runningTime("02:58")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/9_+Yongmun+Traditional+Market.mp3")
        .build());

    AudioTrack track10 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.964324)
        .locationLatitude(37.529407)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/10-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/10-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/10-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/10-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/10-5.png");
        }})
        .build());
    list.add(track10);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track10)
        .title("아이파크몰")
        .runningTime("02:41")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/10.+%EC%95%84%EC%9D%B4%ED%8C%8C%ED%81%AC%EB%AA%B0.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track10)
        .title("I'Park Mall")
        .runningTime("02:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/10_+I_Park+Mall.mp3")
        .build());

    AudioTrack track11 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.968347)
        .locationLatitude(37.528980)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/11-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/11-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/the_center_of_seoul/11-2.jpg");
        }})
        .build());
    list.add(track11);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track11)
        .title("아모레퍼시픽 미술관")
        .runningTime("02:42")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/kor/11.+%EC%95%84%EB%AA%A8%EB%A0%88%ED%8D%BC%EC%8B%9C%ED%94%BD+%EB%AF%B8%EC%88%A0%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track11)
        .title("Amorepacific Museum of Art")
        .runningTime("02:32")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/the_center_of_seoul/eng/11_+AMOREPACIFIC+Museum+of+Art.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertYongsanHistoryGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("5.8km")
            .estimatedTravelTime("4hours")
            .location("Yongsan-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yongsan/a_village_full_of_history_and_culture/1.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yongsan/a_village_full_of_history_and_culture/2.jpg");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(10L);
              add(3L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.EXCURSION.getDatabaseKoreanName())
        .overviewDescription("용산구는 서울의 중심부에 위치해 경제와 교통 문화의 중심지입니다. 고속철도와 중앙역사의 기능을 하고 있습니다. 뿐만 아니라 효창공원, 국립중앙박물관, 전쟁기념관, 한강시민공원 등 도심공원이 많아 주민들이 참 살기 좋은 곳입니다.")
        .playingCount("0")
        .title("역사와 문화가 가득한 동네")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.EXCURSION.getDatabaseEnglishName())
        .overviewDescription("Welcome to Yongsan, the center of Seoul. Yongsan-gu is the major economic and transportation hub located in the heart of Seoul. It is also a central station, which has a high-speed railroad. In addition, there are lots of nice places such as the National Museum of Korea, The War Memorial of Korea, and many urban parks such as Hyochang Park and Hangang Park, so it is really an amazing place to live in.")
        .playingCount("0")
        .title("A Village Full of History and Culture")
        .viewCount("0")
        .build());
  }

  public void insertYongsanHistoryTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(11L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.980549)
        .locationLatitude(37.523699)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/1-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/1-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/1-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/1-4.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("국립중앙박물관")
        .runningTime("00:53")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/kor/1.+%EA%B5%AD%EB%A6%BD%EC%A4%91%EC%95%99%EB%B0%95%EB%AC%BC%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("National  Museum of Korea")
        .runningTime("00:56")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/eng/1_+National+Museum+of+Korea.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.980719)
        .locationLatitude(37.521036)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/2-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/2-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/2-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/2-4.jpg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("국립한글박물관")
        .runningTime("01:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/kor/2.+%EA%B5%AD%EB%A6%BD%ED%95%9C%EA%B8%80%EB%B0%95%EB%AC%BC%EA%B4%80.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("National Hangul Museum")
        .runningTime("01:37")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/eng/2_+National+Hangul+Museum.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.983633)
        .locationLatitude(37.521721)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/3-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/3-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/3-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/3-4.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/3-5.jpg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("용산가족공원")
        .runningTime("00:59")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/kor/3.+%EC%9A%A9%EC%82%B0%EA%B0%80%EC%A1%B1%EA%B3%B5%EC%9B%90.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Yongsan Family Park")
        .runningTime("01:03")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/eng/3_+Yongsan+Family+Park.mp3")
        .build());

    AudioTrack track4 = audioTrackLanguageContentRepository.findByTitle("아이파크몰").orElseThrow(NoSuchElementException::new).getAudioTrack();
    list.add(track4);

    AudioTrack track5 = audioTrackLanguageContentRepository.findByTitle("아모레퍼시픽 미술관").orElseThrow(NoSuchElementException::new).getAudioTrack();
    list.add(track5);

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.971304)
        .locationLatitude(37.531168)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/6-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yongsan/a_village_full_of_history_and_culture/6-2.jpg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("용리단길")
        .runningTime("01:11")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/kor/6.+%EC%9A%A9%EB%A6%AC%EB%8B%A8%EA%B8%B8.m4a")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Yongridan-gil")
        .runningTime("01:23")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yongsan/a_village_full_of_history_and_culture/eng/6_+Yongridan-gil.mp3")
        .build());

    AudioTrack track7 = audioTrackLanguageContentRepository.findByTitle("전쟁기념관").orElseThrow(NoSuchElementException::new).getAudioTrack();
    list.add(track7);

    AudioTrack track8 = audioTrackLanguageContentRepository.findByTitle("전쟁기념관 옥외전시장").orElseThrow(NoSuchElementException::new).getAudioTrack();
    list.add(track8);

    AudioTrack track9 = audioTrackLanguageContentRepository.findByTitle("전사자 명비").orElseThrow(NoSuchElementException::new).getAudioTrack();
    list.add(track9);

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertOlympicParkGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("5.3km")
            .estimatedTravelTime("2hours 20minutes")
            .location("Songpa-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/OlympicPark/1.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/OlympicPark/2.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/OlympicPark/3.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/OlympicPark/4.jpg");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(8L);
              add(9L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.NATURE.getDatabaseKoreanName())
        .overviewDescription("올림픽공원은 서울에서 가장 큰 공원입니다. 그래서 공원 내부에는 다양한 동식물부터 미술관, 박물관, 전시관 등의 문화시설까지 즐길거리가 참 많은 곳입니다. 또한 공원 곳곳에 조각 작품들이 많아 세계 5대 조각공원으로 꼽히기도 했습니다. 올림픽공원의 핫플부터 곳곳에 숨겨진 명소까지 자세히 알려드릴게요. 가볍게 공원을 둘러보면서 오디오를 들어보세요. ")
        .playingCount("0")
        .title("세계 5대 조각공원")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.NATURE.getDatabaseEnglishName())
        .overviewDescription("Olympic Park is the largest park in Seoul. Therefore, there are many things to enjoy and look around inside the park, from various animals and plants living in the park, to cultural facilities such as art museums, museums and exhibition halls. Also, it was chosen to be one of the top five sculpture parks in the world because of various sculptures throughout the park. I'll tell you more about tourist spots and the hidden attractions in Olympic Park. Take a look around the park while listening to our audio guide!")
        .playingCount("0")
        .title("one of the world's 5 best sculpture parks")
        .viewCount("0")
        .build());
  }

  public void insertOlympicParkTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(12L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.113718)
        .locationLatitude(37.517830)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/1-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/1-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/1-3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/1-4.jpeg");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("올림픽공원 소개와 평화의 광장")
        .runningTime("05:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/1.+%EC%98%AC%EB%A6%BC%ED%94%BD%EA%B3%B5%EC%9B%90+%EC%86%8C%EA%B0%9C%EC%99%80+%ED%8F%89%ED%99%94%EC%9D%98+%EA%B4%91%EC%9E%A5.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Olympic Park and Peace Plaza")
        .runningTime("04:53")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/1.+Olympic+Park+and+Peace+Plaza.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.116777)
        .locationLatitude(37.519650)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/2-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/2-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/2-3.jpeg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("몽촌해자와 곰말다리")
        .runningTime("01:34")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/2.+%EB%AA%BD%EC%B4%8C%ED%95%B4%EC%9E%90%EC%99%80+%EA%B3%B0%EB%A7%90%EB%8B%A4%EB%A6%AC.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Mongchon-haeja and Gommal Bridge")
        .runningTime("01:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/2_+Mongchon+Hae+Ja+and+Gommal+Br.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.120404)
        .locationLatitude(37.522598)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/3-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/3-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/3-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/3-3.jpeg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("나홀로나무")
        .runningTime("00:48")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/3.+%EB%82%98%ED%99%80%EB%A1%9C%EB%82%98%EB%AC%B4.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("One Tree Hill")
        .runningTime("00:45")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/3_+One+Tree+Hill.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.125160)
        .locationLatitude(37.521878)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/4-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/4-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/4-3.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("88호수")
        .runningTime("00:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/4.+88%ED%98%B8%EC%88%98.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("88 Lake")
        .runningTime("00:42")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/4_+88+Lake.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.121527)
        .locationLatitude(37.518849)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/5-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/5-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/5-4.jpg");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("야생화 학습장")
        .runningTime("00:40")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/5.+%EC%95%BC%EC%83%9D%ED%99%94+%ED%95%99%EC%8A%B5%EC%9E%A5.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Wildflower Garden")
        .runningTime("00:37")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/5_+Wild+Flower+Garden.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.120054)
        .locationLatitude(37.516522)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/6-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/6-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/6-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/6-5.jpg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("조각공원과 소마미술관")
        .runningTime("02:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/6.+%EC%A1%B0%EA%B0%81%EA%B3%B5%EC%9B%90%EA%B3%BC+%EC%86%8C%EB%A7%88%EB%AF%B8%EC%88%A0%EA%B4%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Sculpture Park and SOMA")
        .runningTime("02:30")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/6_+Sculpture+Park+and+SOMA.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.120611)
        .locationLatitude(37.515411)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/7-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/7-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/7-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/7-4.jpg");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("한성백제박물관")
        .runningTime("01:30")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/7.+%ED%95%9C%EC%84%B1%EB%B0%B1%EC%A0%9C%EB%B0%95%EB%AC%BC%EA%B4%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Seoul Baekje Museum")
        .runningTime("01:36")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/7_+Seoul+Hanseong+Baekje+Museum.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.125416)
        .locationLatitude(37.513475)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/8-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/8-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/8-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/8-4.jpg");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("들꽃마루와 장미광장")
        .runningTime("01:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/8.+%EB%93%A4%EA%BD%83%EB%A7%88%EB%A3%A8%EC%99%80+%EC%9E%A5%EB%AF%B8%EA%B4%91%EC%9E%A5.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Flower Garden and Rose Plaza")
        .runningTime("02:01")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/8_+Flower+Garden+and+Rose+Plaza.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(127.130275)
        .locationLatitude(37.516760)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/9-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/9-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/OlympicPark/9-3.jpg");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("만남의 광장")
        .runningTime("01:20")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/kor/9.+%EB%A7%8C%EB%82%A8%EC%9D%98+%EA%B4%91%EC%9E%A5.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Meeting Plaza")
        .runningTime("01:22")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/OlympicPark/eng/9_+Meeting+Plaza.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertInsadongGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("2.6km")
            .estimatedTravelTime("2 hours")
            .location("Jongno-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Insadong/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Insadong/2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Insadong/3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Insadong/4.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(7L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.SHOPPING.getDatabaseKoreanName())
        .overviewDescription("인사동은 한국적인 분위기를 느낄 수 있어 외국인 관광객들이 자주 방문하는 장소 중 하나입니다. 전통음식은 물론 전통차, 공예품, 미술품까지 다양하게 구경하실 수 있습니다. 이번코스는 종각역에서 시작해서 인사동, 운현궁, 익선동까지 둘러볼 예정입니다. 익선동은 아기자기한 카페들과 음식점들이 모여 있어 구경하면서 돌아다니기 좋을 거예요. 곳곳에 숨은 서울 이야기부터 다양한 정보까지 다 알려드릴게요!")
        .playingCount("0")
        .title("한국의 문화를 느낄 수 있는 동네")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.SHOPPING.getDatabaseEnglishName())
        .overviewDescription("Insa-dong is one of the most popular places for foreign tourists. You can feel the traditional vibe in Korea. You can also enjoy various kinds of traditional foods, tea, crafts, and art. In this course, you will start with Jonggak Station, and look around Insadong, Unhyeongung, and Ikseon-dong. Ikseon-dong is a great place to look around having small cafes and restaurants. I'll tell you everything from the hidden stories of Seoul to the exciting information!")
        .playingCount("0")
        .title("A Neighbourhood Full of Authentic and Traditional Korean Culture ")
        .viewCount("0")
        .build());
  }

  public void insertInsadongTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(13L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.983812)
        .locationLatitude(37.570048)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/1-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/1-2.jpg");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("보신각")
        .runningTime("01:28")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/1.+%EB%B3%B4%EC%8B%A0%EA%B0%81.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Bosingak")
        .runningTime("01:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/01+Bosingak.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.983262)
        .locationLatitude(37.570579)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/2-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/2-3.png");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("종로타워")
        .runningTime("01:00")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/2.+%EC%A2%85%EB%A1%9C%ED%83%80%EC%9B%8C.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Jongno Tower")
        .runningTime("00:59")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/02+Jongno+Tower.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.983318)
        .locationLatitude(37.571863)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/3-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/3-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/3-3.png");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("공평유적전시관")
        .runningTime("01:15")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/3.+%EA%B3%B5%ED%8F%89%EB%8F%84%EC%8B%9C%EC%9C%A0%EC%A0%81%EC%A0%84%EC%8B%9C%EA%B4%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Gongpyeong Historic Site Museum")
        .runningTime("01:22")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/03+Gongpyeong+Historic+Site+Museum.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.986174)
        .locationLatitude(37.573015)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/4-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/4-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/4-3.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("인사동 문화의 거리")
        .runningTime("02:08")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/4.+%EC%9D%B8%EC%82%AC%EB%8F%99+%EB%AC%B8%ED%99%94%EC%9D%98%EA%B1%B0%EB%A6%AC.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Insadong Culture Street")
        .runningTime("02:19")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/04+Insadong+Culture+Street.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.985376)
        .locationLatitude(37.573626)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/5-2.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("인사동 마루")
        .runningTime("00:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/5.+%EC%9D%B8%EC%82%AC%EB%8F%99+%EB%A7%88%EB%A3%A8.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Insadong Maru")
        .runningTime("00:48")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/05+Insadong+maru.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.984789)
        .locationLatitude(37.574088)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/6-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/6-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/6-4.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("쌈지길")
        .runningTime("00:59")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/6.+%EC%8C%88%EC%A7%80%EA%B8%B8.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Ssamzigil")
        .runningTime("01:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/06+Ssamziegil.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.984014)
        .locationLatitude(37.574647)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/7-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/7-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/7-3.png");

        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("안녕인사동")
        .runningTime("00:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/7.+%EC%95%88%EB%85%95%EC%9D%B8%EC%82%AC%EB%8F%99.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Anyoung Insadong")
        .runningTime("00:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/07+Anyoung+Insadong.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.986543)
        .locationLatitude(37.576251)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/8-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/8-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/8-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/8-4.png");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("운현궁")
        .runningTime("03:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/8.+%EC%9A%B4%ED%98%84%EA%B6%81.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Unhyeongung Royal Residence")
        .runningTime("03:58")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/08+Unhyeongung.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.987686)
        .locationLatitude(37.573783)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/9-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/9-2.png");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("낙원상가")
        .runningTime("00:30")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/9.+%EB%82%99%EC%9B%90%EC%83%81%EA%B0%80.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Nakwon Arcade")
        .runningTime("00:32")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/09+Nakwon+Arcade.mp3")
        .build());

    AudioTrack track10 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.989473)
        .locationLatitude(37.572597)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/10-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/10-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/10-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Insadong/10-4.png");
        }})
        .build());
    list.add(track10);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track10)
        .title("익선동")
        .runningTime("01:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/kor/10.+%EC%9D%B5%EC%84%A0%EB%8F%99.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track10)
        .title("Ikseon-dong")
        .runningTime("01:18")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Insadong/eng/10+Ikseon-dong.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertDeoksugungPalaceGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("2.8km")
            .estimatedTravelTime("2 hours")
            .location("Jung-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/DeoksugungPalace/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/DeoksugungPalace/2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/DeoksugungPalace/3.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/DeoksugungPalace/4.jpg");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(15L);
              add(16L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.HISTORY.getDatabaseKoreanName())
        .overviewDescription("한국의 역사에 대해 관심이 있으시다면 흥선대원군과 고종에 대해 들어보셨을 거예요. 이번 오디오에서는 흥선대원군과 고종이 주로 머물렀던 공간들에 대해 소개해드릴게요. 조선시대에서 마지막으로 지어진 덕수궁은 전통적인 건축물과 근대적인 건축물이 함께 공존하고 있습니다. 덕수궁 내부에 있는 다양한 건물들을 소개해드리고 덕수궁 밖의 산책하기 좋은 산책로까지 알찬 정보를 담아봤습니다.")
        .playingCount("0")
        .title("도심 속에 있는 조선의 마지막 궁궐")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.HISTORY.getDatabaseEnglishName())
        .overviewDescription("If you're interested in Korean history, you might've heard about Regent Heungseon Daewongun and Emperor Gojong. In this content, I will introduce you the places where Heungseon Daewongun and Gojong stayed. Deoksugung Palace, which was the last building to be built during the Joseon Dynasty, consists of both traditional and modern architecture. We are going to introduce various buildings inside Deoksugung Palace and give you some information  about walking trails with beautiful views outside Deoksugung Palace.")
        .playingCount("0")
        .title("The Last Palace of Joseon in the Heart of the City")
        .viewCount("0")
        .build());
  }

  public void insertDeoksugungPalaceTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(14L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976515)
        .locationLatitude(37.564975)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/1-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/1-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/1-3.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("대한문")
        .runningTime("02:42")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/1.+%EB%8C%80%ED%95%9C%EB%AC%B8.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Daehanmun")
        .runningTime("02:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/01+Daehanmun.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.974680)
        .locationLatitude(37.565196)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/2-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/2-3.jpg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("중화문")
        .runningTime("00:41")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/2.+%EC%A4%91%ED%99%94%EB%AC%B8.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Junghwamun")
        .runningTime("02:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/02+Junghwamun.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.974845)
        .locationLatitude(37.565774)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/3-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/3-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/3-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/3-4.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/3-5.jpg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("중화전")
        .runningTime("04:20")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/3.+%EC%A4%91%ED%99%94%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Junghwajeon")
        .runningTime("02:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/03+Junghwajeon.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.974442)
        .locationLatitude(37.565693)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/4-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/4-2.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("석조전 서관과 분수대")
        .runningTime("01:03")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/4.+%EC%84%9D%EC%A1%B0%EC%A0%84+%EC%84%9C%EA%B4%80%EA%B3%BC+%EB%B6%84%EC%88%98%EB%8C%80.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Seokjojeon West Hall and the Fountain")
        .runningTime("01:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/04+Seokjojeon+West+Hall+and+the+Fountain.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.974356)
        .locationLatitude(37.566269)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/5-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/5-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/5-3.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("석조전")
        .runningTime("01:23")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/5.+%EC%84%9D%EC%A1%B0%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Seokjojeon")
        .runningTime("01:05")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/05+Seokjojeon.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.974764)
        .locationLatitude(37.566138)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/6-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/6-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/6-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/6-4.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/6-5.jpg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("준명당, 즉조당, 석어당")
        .runningTime("03:19")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/6.+%EC%A4%80%EB%AA%85%EB%8B%B9%2C+%EC%A6%89%EC%A1%B0%EB%8B%B9%2C+%EC%84%9D%EC%96%B4%EB%8B%B9.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Junmyeondang, Jeukjodang, Seogeodang")
        .runningTime("03:14")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/06+Junmyeondang%2C+Jeukjodang%2C+Seogeodang.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.975515)
        .locationLatitude(37.565928)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/7-1.jpg");

        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("덕홍전")
        .runningTime("01:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/7.+%EB%8D%95%ED%99%8D%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Deokhongjeon")
        .runningTime("03:14")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/07+Deokhongjeon.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.975620)
        .locationLatitude(37.566434)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/8-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/8-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/8-3.jpg");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("정관헌")
        .runningTime("01:22")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/8.+%EC%A0%95%EA%B4%80%ED%97%8C.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Jeonggwanheon")
        .runningTime("01:18")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/08+Jeonggwanheon.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.975835)
        .locationLatitude(37.565913)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/9-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/9-2.jpg");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("함녕전")
        .runningTime("00:42")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/9.+%ED%95%A8%EB%85%95%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Hamnyeongjeon")
        .runningTime("00:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/09+Hamnyeongjeon.mp3")
        .build());

    AudioTrack track10 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976632)
        .locationLatitude(37.566570)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/10-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/10-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/10-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/10-4.png");
        }})
        .build());
    list.add(track10);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track10)
        .title("덕수궁 돌담길")
        .runningTime("02:00")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/10.+%EB%8D%95%EC%88%98%EA%B6%81+%EB%8F%8C%EB%8B%B4%EA%B8%B8.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track10)
        .title("Deoksugung Dol-dam Road")
        .runningTime("01:59")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/10+Deoksugung+Doldam-gil+(Deoksugung+Stone-wall+Road).mp3")
        .build());

    AudioTrack track11 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.973250)
        .locationLatitude(37.567518)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/11-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/11-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/11-3.jpg");
        }})
        .build());
    list.add(track11);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track11)
        .title("고종의 길")
        .runningTime("00:43")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/11.+%EA%B3%A0%EC%A2%85%EC%9D%98+%EA%B8%B8.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track11)
        .title("King Gojong’s Road")
        .runningTime("00:41")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/11+King+Gojong%E2%80%99s+Road.mp3")
        .build());

    AudioTrack track12 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.971685)
        .locationLatitude(37.567541)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/12-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/12-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/12-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/12-4.jpg");
        }})
        .build());
    list.add(track12);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track12)
        .title("정동공원")
        .runningTime("00:37")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/12.+%EC%A0%95%EB%8F%99%EA%B3%B5%EC%9B%90.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track12)
        .title("Jeongdong Park")
        .runningTime("00:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/12+Jeongdong+Park+(The+Former+Russian+Legation).mp3")
        .build());

    AudioTrack track13 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.972567)
        .locationLatitude(37.566631)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/13-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/DeoksugungPalace/13-2.png");
        }})
        .build());
    list.add(track13);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track13)
        .title("중명전")
        .runningTime("02:19")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/kor/13.+%EC%A4%91%EB%AA%85%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track13)
        .title("Jungmyeongjeon")
        .runningTime("02:29")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/DeoksugungPalace/eng/13+Jungmyeongjeon.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertGyeongbokgungPalaceGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("2.3km")
            .estimatedTravelTime("2 hours")
            .location("Jongno-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/GyeongbokgungPalace/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/GyeongbokgungPalace/2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/GyeongbokgungPalace/3.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(14L);
              add(16L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.HISTORY.getDatabaseKoreanName())
        .overviewDescription("경복궁은 조선의 법궁입니다. 그래서 건물의 건축물과 배치, 심지어 이름까지 조선의 종교인 유교가 많이 관여를 했습니다. 조선의 과학기술을 엿볼 수 있는 곳도 곳곳에 숨겨져 있습니다. 경복궁을 세세하게 소개해드릴게요.")
        .playingCount("0")
        .title("서울의 법궁")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.HISTORY.getDatabaseEnglishName())
        .overviewDescription("Gyeongbokgung is a Joseon's palace where king stays. Therefore, confucianism, a religion of Joseon, has deeply involved in architecture, layout, and even name of the building. There are also some hidden places where you can get a glimpse of Joseon's technology. Let me introduce Gyeongbokgung in detail.")
        .playingCount("0")
        .title("A Principal Royal Palace")
        .viewCount("0")
        .build());
  }

  public void insertGyeongbokgungPalaceTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(15L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976204)
        .locationLatitude(37.575943)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/1-1.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("경복궁 소개")
        .runningTime("01:54")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/1.+%EA%B2%BD%EB%B3%B5%EA%B6%81+%EC%86%8C%EA%B0%9C.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Gyeongbokgung Palace")
        .runningTime("02:05")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/01+Gyeongbokgung+Palace.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976998)
        .locationLatitude(37.576008)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/2-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/2-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/2-4.jpg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("광화문")
        .runningTime("03:14")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/2.+%EA%B4%91%ED%99%94%EB%AC%B8.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Gwanghwamun Gate")
        .runningTime("03:01")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/02+Gwanghwamun+Gate.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976894)
        .locationLatitude(37.577350)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/3-1.jpg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("영제교")
        .runningTime("01:25")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/3.+%EC%98%81%EC%A0%9C%EA%B5%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Yeongjegyo Bridge")
        .runningTime("01:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/03+Yeongjegyo+Bridge.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976970)
        .locationLatitude(37.578532)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/4-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/4-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/4-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/4-4.jpg");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("근정전")
        .runningTime("05:13")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/4.+%EA%B7%BC%EC%A0%95%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Geunjeongjeon Hall")
        .runningTime("04:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/04+Geunjeongjeon+Hall.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.975865)
        .locationLatitude(37.579714)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/5-2.jpg");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("경회루")
        .runningTime("02:04")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/5.+%EA%B2%BD%ED%9A%8C%EB%A3%A8.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Gyeonghoeru Pavilion")
        .runningTime("01:55")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/05+Gyeonghoeru+Pavilion.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.975904)
        .locationLatitude(37.578945)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/6-1.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("수정전")
        .runningTime("00:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/6.+%EC%88%98%EC%A0%95%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Sujeongjeon Hall")
        .runningTime("00:47")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/06+Sujeongjeon+Hall.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976983)
        .locationLatitude(37.579073)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/7-1.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("사정전")
        .runningTime("00:52")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/7.+%EC%82%AC%EC%A0%95%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Sajeongjeon Hall")
        .runningTime("00:55")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/07+Sajeongjeon+Hall.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977044)
        .locationLatitude(37.579523)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/8-1.png");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("강녕전")
        .runningTime("01:22")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/8.+%EA%B0%95%EB%85%95%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Gangnyeongjeon Hall")
        .runningTime("01:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/08+Gangnyeongjeon+Hall.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977049)
        .locationLatitude(37.579922)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/9-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/9-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/9-3.jpg");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("교태전")
        .runningTime("01:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/9.+%EA%B5%90%ED%83%9C%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Gyotaejeon Hall")
        .runningTime("01:19")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/09+Gyotaejeon+Hall.mp3")
        .build());

    AudioTrack track10 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977972)
        .locationLatitude(37.580288)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/10-1.jpg");
        }})
        .build());
    list.add(track10);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track10)
        .title("자경전")
        .runningTime("01:25")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/10.+%EC%9E%90%EA%B2%BD%EC%A0%84.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track10)
        .title("Jagyeongjeon Hall")
        .runningTime("01:13")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/10+Jagyeongjeon+Hall.mp3")
        .build());

    AudioTrack track11 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.978051)
        .locationLatitude(37.579030)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/11-1.jpg");
        }})
        .build());
    list.add(track11);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track11)
        .title("소주방")
        .runningTime("00:39")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/11.+%EC%86%8C%EC%A3%BC%EB%B0%A9.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track11)
        .title("Sojubang House")
        .runningTime("00:39")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/11+Sojubang+House.mp3")
        .build());

    AudioTrack track12 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977016)
        .locationLatitude(37.582335)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/12-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/12-2.png");
        }})
        .build());
    list.add(track12);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track12)
        .title("향원정")
        .runningTime("01:14")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/12.+%ED%96%A5%EC%9B%90%EC%A0%95.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track12)
        .title("Hyangwonjeong Pavilion")
        .runningTime("01:09")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/12+Hyangwonjeong+Pavilion.mp3")
        .build());

    AudioTrack track13 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.977175)
        .locationLatitude(37.583179)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/13-1.jpg");
        }})
        .build());
    list.add(track13);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track13)
        .title("건청궁")
        .runningTime("01:07")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/13.+%EA%B1%B4%EC%B2%AD%EA%B6%81.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track13)
        .title("Geoncheonggung Residence")
        .runningTime("00:55")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/13+Geoncheonggung+Residence.mp3")
        .build());

    AudioTrack track14 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.976067)
        .locationLatitude(37.583433)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/14-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/GyeongbokgungPalace/14-2.jpg");
        }})
        .build());
    list.add(track14);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track14)
        .title("집옥재")
        .runningTime("01:12")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/kor/14.+%EC%A7%91%EC%98%A5%EC%9E%AC.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track14)
        .title("Jibokjae House")
        .runningTime("00:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/GyeongbokgungPalace/eng/14+Jibokjae+House.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertChangdeokgungPalaceGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("1.2km")
            .estimatedTravelTime("1 hours")
            .location("Jongno-gu, Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/ChangdeokgungPalace/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/ChangdeokgungPalace/2.jpg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/ChangdeokgungPalace/3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/ChangdeokgungPalace/4.jpg");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(14L);
              add(15L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.HISTORY.getDatabaseKoreanName())
        .overviewDescription("창덕궁은 경복궁의 뒤를 이어 두번째로 세워진 궁궐인데요, 그럼에도 불구하고 많은 왕들의 사랑을 받아 왕들이 창덕궁에 머물며 나라를 다스리면서 자연스럽게 조선 왕조의 중심지가 되었습니다.")
        .playingCount("0")
        .title("조선의 두번째 궁궐")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.HISTORY.getDatabaseEnglishName())
        .overviewDescription("Changdeokgung Palace was the second palace to be built after Gyeongbokgung Palace. Nevertheless, it was favored by many kings that they stayed at Changdeokgung Palace and ruled the country, naturally making it the center of the Joseon Dynasty.")
        .playingCount("0")
        .title("The Second Royal Palace of Joseon")
        .viewCount("0")
        .build());
  }

  public void insertChangdeokgungPalaceTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(16L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.990012)
        .locationLatitude(37.577542)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/1-1.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("창덕궁 소개")
        .runningTime("04:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/01+%EC%B0%BD%EB%8D%95%EA%B6%81+%EC%86%8C%EA%B0%9C.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Changdeokgung Palace")
        .runningTime("01:35")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/01+Changdeokgung+Palace.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.990029)
        .locationLatitude(37.577818)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/2-1.jpg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("돈화문")
        .runningTime("01:07")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/02+%EB%8F%88%ED%99%94%EB%AC%B8.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Donhwamun Gate")
        .runningTime("01:03")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/02+Donhwamun+Gate.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.989959)
        .locationLatitude(37.578448)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/3-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/3-2.jpg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("금천교")
        .runningTime("01:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/03+%EA%B8%88%EC%B2%9C%EA%B5%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Geumcheongyo Bridge")
        .runningTime("01:14")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/03+Geumcheongyo+Bridge.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.991175)
        .locationLatitude(37.578781)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/4-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/4-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/4-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/4-4.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/4-5.jpg");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("인정문과 인정전")
        .runningTime("02:56")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/04+%EC%9D%B8%EC%A0%95%EB%AC%B8%EA%B3%BC+%EC%9D%B8%EC%A0%95%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Injeongjeon Hall and Injeongmun Gate")
        .runningTime("02:41")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/04+Injeongjeon+Hall+and+Injeongmun+Gate.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.991684)
        .locationLatitude(37.579635)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/5-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/5-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/5-3.jpg");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("선정전")
        .runningTime("00:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/05+%EC%84%A0%EC%A0%95%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Seonjeongjeon Hall")
        .runningTime("00:39")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/05+Seonjeongjeon+Hall.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.992254)
        .locationLatitude(37.579728)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/6-2.jpg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("희정당")
        .runningTime("01:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/06+%ED%9D%AC%EC%A0%95%EB%8B%B9.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Huijeongdang Hall")
        .runningTime("01:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/06+Huijeongdang+Hall.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.992391)
        .locationLatitude(37.580126)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/7-1.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("대조전")
        .runningTime("00:35")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/07+%EB%8C%80%EC%A1%B0%EC%A0%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Daejojeon Hall")
        .runningTime("01:33")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/07+Daejojeon+Hall.mp3")
        .build());

    AudioTrack track8 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.992642)
        .locationLatitude(37.579335)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/8-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/8-2.jpg");
        }})
        .build());
    list.add(track8);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track8)
        .title("성정각")
        .runningTime("01:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/08+%EC%84%B1%EC%A0%95%EA%B0%81.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track8)
        .title("Seongjeonggak Hall")
        .runningTime("00:28")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/08+Seongjeonggak+Hall.mp3")
        .build());

    AudioTrack track9 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.993431)
        .locationLatitude(37.578725)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/9-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/9-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/9-3.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/ChangdeokgungPalace/9-4.jpg");
        }})
        .build());
    list.add(track9);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track9)
        .title("낙선재 일원")
        .runningTime("02:38")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/kor/09+%EB%82%99%EC%84%A0%EC%9E%AC+%EC%9D%BC%EC%9B%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track9)
        .title("Nakseonjae Complex (Nakseonjae Irwon)")
        .runningTime("02:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/ChangdeokgungPalace/eng/09+Nakseonjae+Complex+.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertYeouidoeGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("7km")
            .estimatedTravelTime("2hours 30minutes")
            .location("Yeongdeungpo-gu,Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeouido/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeouido/2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeouido/3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeouido/4.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeouido/5.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(3L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.EXCURSION.getDatabaseKoreanName())
        .overviewDescription("한국의 맨해튼이라고 불리는 여의도에 오신 걸 환영합니다! 뉴욕의 맨해튼 하면 고층건물들이 빼곡히 있는 모습이 떠오르시죠? 여의도에도 많은 고층건물들이 있습니다. 많은 금융계 회사들뿐만 아니라 국회의사당, 금융감독원 등의 국가의 핵심 기관이 모여있어 여의도를 정치와 금융의 중심이라고 볼 수 있는데요. 요즘에는 쇼핑몰까지 들어오게 되면서 상업지구로도 발달하고 있습니다.")
        .playingCount("0")
        .title("한국의 맨해튼")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.EXCURSION.getDatabaseEnglishName())
        .overviewDescription("Welcome to Yeouido, the Manhattan of Korea! You remember the Manhattan city of New York, where there are skyscrapers? Yeouido has a lot of skyscrapers too just like Manhattan. In addition to many financial companies, key institutions such as the National Assembly and the Financial Supervisory Service are gathered, so Yeouido became another name for the center of politics and finance. Nowadays, as the appearance of shopping malls, it is developing into commercial districts.")
        .playingCount("0")
        .title("Korea’s Manhattan")
        .viewCount("0")
        .build());
  }

  public void insertYeouidoeTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(17L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.924099)
        .locationLatitude(37.521728)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/1-1.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("여의도 소개")
        .runningTime("01:03")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/1.+%EC%97%AC%EC%9D%98%EB%8F%84+%EC%86%8C%EA%B0%9C.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Introduction of Yeouido")
        .runningTime("01:08")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/01+Introduction+of+Yeouido.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.923328)
        .locationLatitude(37.523794)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/2-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/2-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/2-4.png");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("IFC몰")
        .runningTime("00:48")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/2.+IFC%EB%AA%B0.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("IFC Mall")
        .runningTime("00:45")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/02+IFC+Mall.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.921028)
        .locationLatitude(37.523837)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/3-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/3-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/3-3.png");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("SeMA벙커")
        .runningTime("01:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/3+SeMA%EB%B2%99%EC%BB%A4.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("SeMA Bunker")
        .runningTime("01:26")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/03+SeMA+Bunker.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.920539)
        .locationLatitude(37.524894)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/4-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/4-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/4-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/4-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/4-5.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("여의도 공원")
        .runningTime("01:01")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/4.+%EC%97%AC%EC%9D%98%EB%8F%84%EA%B3%B5%EC%9B%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Yeouido Park")
        .runningTime("00:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/04+Yeouido+Park.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.914172)
        .locationLatitude(37.531535)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/5-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/5-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/5-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/5-5.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("국회의사당")
        .runningTime("02:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/5.+%EA%B5%AD%ED%9A%8C%EC%9D%98%EC%82%AC%EB%8B%B9.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("The National Assembly building")
        .runningTime("02:48")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/05+The+National+Assembly+building.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.918861)
        .locationLatitude(37.533661)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/6-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/6-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/6-4.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("여의도 한강공원")
        .runningTime("02:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/6.+%EC%97%AC%EC%9D%98%EB%8F%84%ED%95%9C%EA%B0%95%EA%B3%B5%EC%9B%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Yeouido Han River Park")
        .runningTime("02:52")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/06+Yeouido+Hangang+Park.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.940121)
        .locationLatitude(37.519808)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/7-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/7-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/7-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeouido/7-4.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("63스퀘어")
        .runningTime("01:31")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/kor/7.+63%EC%8A%A4%ED%80%98%EC%96%B4.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("63 Square")
        .runningTime("01:40")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeouido/eng/07+63+Square.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertYeongdeungpoGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("5.2km")
            .estimatedTravelTime("2hours 20minutes")
            .location("Yeongdeungpo-gu,Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeongdeungpo/1.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeongdeungpo/2.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Yeongdeungpo/3.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(17L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.SHOPPING.getDatabaseKoreanName())
        .overviewDescription("한국의 타임스 스퀘어, 영등포에 오신 것을 환영합니다. 영등포는 서울 철도의 분기점인 지역이라 우리나라 경제발전을 주도하고 교통의 중심지로서 문화를 선도했습니다. 현재는 국제금융과 문화 관광 중심지로 자리 잡은 곳입니다.")
        .playingCount("0")
        .title("한국의 타임스퀘어")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.SHOPPING.getDatabaseEnglishName())
        .overviewDescription("Yeongdeungpo was a branch of Seoul Railway between Gyeongbu Line and Gyeongin Line, leading Korea's economic development and culture as a transportation hub. It is now a center of international finance, culture and tourism.")
        .playingCount("0")
        .title("The Times Square of Korea")
        .viewCount("0")
        .build());
  }

  public void insertYeongdeungpoTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(18L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.907958)
        .locationLatitude(37.514975)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/1-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/1-2.jpeg");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("영등포 소개")
        .runningTime("01:02")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/kor/1.+%EC%98%81%EB%93%B1%ED%8F%AC+%EC%86%8C%EA%B0%9C.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Introduction to Yeongdeungpo")
        .runningTime("01:00")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/eng/01+Introduction+to+Yeongdeungpo.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.910734)
        .locationLatitude(37.515371)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/2-1.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/2-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/2-3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/2-4.png");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("영등포공원")
        .runningTime("00:58")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/kor/2.+%EC%98%81%EB%93%B1%ED%8F%AC%EA%B3%B5%EC%9B%90.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Yeongdeungpo Park")
        .runningTime("00:55")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/eng/02+Yeongdeungpo+Park.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.905197)
        .locationLatitude(37.517509)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/3-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/3-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/3-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/3-4.png");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("타임스퀘어")
        .runningTime("01:37")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/kor/3.+%ED%83%80%EC%9E%84%EC%8A%A4%ED%80%98%EC%96%B4.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Time Square")
        .runningTime("01:28")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/eng/03+Time+Square.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.892669)
        .locationLatitude(37.513709)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/4-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/4-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/4-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/4-4.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("문래예술촌")
        .runningTime("02:19")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/kor/4.+%EB%AC%B8%EB%9E%98%EC%98%88%EC%88%A0%EC%B4%8C.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Mullae Art Village")
        .runningTime("02:06")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/eng/04+Mullae+Art+Village.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.905891)
        .locationLatitude(37.517924)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/5-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/5-3.jpeg");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("영등포 지하쇼핑센터")
        .runningTime("00:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/kor/5.+%EC%98%81%EB%93%B1%ED%8F%AC+%EC%A7%80%ED%95%98%EC%87%BC%ED%95%91%EC%84%BC%ED%84%B0.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Yeongdeungpo Underground Shopping Center")
        .runningTime("00:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/eng/05+Yeongdeungpo+Underground+Shopping+Center.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.908519)
        .locationLatitude(37.518659)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/6-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Yeongdeungpo/6-2.jpeg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("영등포 먹자골목")
        .runningTime("00:34")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/kor/6.+%EC%98%81%EB%93%B1%ED%8F%AC+%EB%A8%B9%EC%9E%90%EA%B3%A8%EB%AA%A9.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Yeongdeungpo Food Alley")
        .runningTime("00:38")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Yeongdeungpo/eng/06+Yeongdeungpo+Food+Alley.mp3")
        .build());

    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertHongdaeGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("3km")
            .estimatedTravelTime("2hours")
            .location("Mapo-gu,Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Hongdae/1.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Hongdae/2.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Hongdae/3.jpeg");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(6L);
              add(20L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.ART.getDatabaseKoreanName())
        .overviewDescription("홍대하면 떠오르는 이미지가 어떤가요? 아마도 젊음, 예술 이런 것들이 떠오를텐데요. 이번 코스에서는 홍대의 다른 매력을 보여드리고자 합니다. 홍대 메인거리에서 벗어나 골목 골목 숨겨진 서점에 대해 설명해드릴게요.")
        .playingCount("0")
        .title("홍대 서점 탐방")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.ART.getDatabaseEnglishName())
        .overviewDescription("What's the image that comes into your mind when you think of Hongdae? Probably things like youth and art, but today, I'd like to show you a different kind of charm of Hongdae in this course. Away from the main street, let me explain some hidden bookstores in the alley away from the main street of Hongdae.")
        .playingCount("0")
        .title("Hongdae Bookstore Tour")
        .viewCount("0")
        .build());
  }

  public void insertHongdaeTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(19L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.923897)
        .locationLatitude(37.556955)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/1-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/1-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/1-3.png");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("홍대입구")
        .runningTime("00:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/1.+%ED%99%8D%EB%8C%80%EC%9E%85%EA%B5%AC.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Hongik University Entrance")
        .runningTime("00:18")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/01+Hongik+University+Entrance.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.922975)
        .locationLatitude(37.557424)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/2-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/2-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/2-3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/2-4.jpeg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("1984")
        .runningTime("02:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/2.+1984.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("1984")
        .runningTime("02:41")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/02+1984.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.921052)
        .locationLatitude(37.556213)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/3-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/3-2.jpeg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("독립서점에 대하여")
        .runningTime("01:20")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/3.+%EB%8F%85%EB%A6%BD%EC%84%9C%EC%A0%90%EC%97%90+%EB%8C%80%ED%95%98%EC%97%AC.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("About Independent Bookstore")
        .runningTime("01:04")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/03+About+Independent+Bookstore.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.919773)
        .locationLatitude(37.557621)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/4-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/4-2.jpg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/4-3.png");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("공상온도")
        .runningTime("01:21")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/4.+%EA%B3%B5%EC%83%81%EC%98%A8%EB%8F%84.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Gongsang-Ondo (The temperature of Daydreaming)")
        .runningTime("01:06")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/04+Gongsang-Ondo.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.918136)
        .locationLatitude(37.557862)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/5-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/5-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/5-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/5-4.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("온고당서점")
        .runningTime("00:50")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/5.+%EC%98%A8%EA%B3%A0%EB%8B%B9%EC%84%9C%EC%A0%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Ongodang Bookstore")
        .runningTime("00:44")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/05+Ongodang+Bookstore.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.922581)
        .locationLatitude(37.560232)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/6-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/6-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/6-3.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/6-4.jpeg");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("안도북스")
        .runningTime("01:34")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/6.+%EC%95%88%EB%8F%84%EB%B6%81%EC%8A%A4.MP3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Ando Books")
        .runningTime("01:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/06+Ando+Books.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.929485)
        .locationLatitude(37.556102)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/7-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/7-2.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Hongdae/7-3.jpeg");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("경의선숲길 (경의선 책거리)")
        .runningTime("01:20")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/kor/7.+%EA%B2%BD%EC%9D%98%EC%84%A0+%EC%88%B2%EA%B8%B8.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Gyeongui Line Forest Road (Gyeongui Line Book Street)")
        .runningTime("00:57")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Hongdae/eng/07+Gyeongui+Line+Forest+Road.mp3")
        .build());


    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }

  public void insertBuamdongGuides() {
    AudioGuide audioGuide = audioGuideRepository.save(
        AudioGuide.builder()
            .distance("3.6km")
            .estimatedTravelTime("2 hours 10 minutes")
            .location("Jongno-gu,Seoul")
            .images(new ArrayList<String>() {{
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Buam-dong/1.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Buam-dong/2.jpeg");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Buam-dong/3.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Buam-dong/4.png");
              add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/images/Buam-dong/5.png");
            }})
            .recommendedAudioGuideIds(new ArrayList<Long>(){{
              add(4L);
              add(19L);
            }})
            .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.KOREAN)
        .category(AudioGuideCategory.ART.getDatabaseKoreanName())
        .overviewDescription("부암동은 옛모습을 그대로 가지고 있어 정겨운 느낌을 가지고 있는 동네입니다. 청와대와 가까운 탓에 군사보호구역, 개발제한구역으로 지정되어 있어 높은 건물과 신축건물이 많이 때문인데요. 덕분에 옛날 분위기와 자연을 느낄 수 있어 각광 받는 관광지입니다. 소규모 카페와 미술관 등이 많아 한적한 나들이를 원하는 분들에게 이번 코스를 추천합니다.")
        .playingCount("0")
        .title("사색하기 좋은 동네")
        .viewCount("0")
        .build());

    audioGuideLanguageContentRepository.save(AudioGuideLanguageContent.builder()
        .audioGuide(audioGuide)
        .language(Language.ENGLISH)
        .category(AudioGuideCategory.ART.getDatabaseEnglishName())
        .overviewDescription("Buam-dong is a neighborhood that has a friendly atmosphere because it has a remained the feature of the old days. As it’s close to Cheong Wa Dae, Buam-dong is designated as a military protection zone and a restricted development zone, having many tall buildings and new buildings. Due to following reasons, it has been a popular tourist attraction with old atmosphere and nature. I recommend this course to those who want a quiet outing because there are many small cafes and art galleries.")
        .playingCount("0")
        .title("A Suitable Place to Be in Contemplation")
        .viewCount("0")
        .build());
  }

  public void insertBuamdongTracks() {
    AudioGuide audioGuide = audioGuideRepository.findById(20L).orElseThrow(() -> new NoSuchElementException());

    List<AudioTrack> list = new ArrayList<>();

    AudioTrack track1 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.966046)
        .locationLatitude(37.590579)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/1-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/1-2.jpeg");
        }})
        .build());
    list.add(track1);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track1)
        .title("청운공원")
        .runningTime("01:00")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/1.+%EC%B2%AD%EC%9A%B4%EA%B3%B5%EC%9B%90.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track1)
        .title("Cheongun Park")
        .runningTime("01:05")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/01+Cheongun+Park.mp3")
        .build());

    AudioTrack track2 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.965963)
        .locationLatitude(37.590439)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/2-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/2-2.jpeg");
        }})
        .build());
    list.add(track2);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track2)
        .title("윤동주 시인의 언덕")
        .runningTime("01:45")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/2.+%EC%9C%A4%EB%8F%99%EC%A3%BC+%EC%8B%9C%EC%9D%B8%EC%9D%98+%EC%96%B8%EB%8D%95.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track2)
        .title("Yoon Dong-ju Hill")
        .runningTime("01:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/02+Yoon+Dong-ju+Hill.mp3")
        .build());

    AudioTrack track3 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.965873)
        .locationLatitude(37.590170)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/3-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/3-2.jpeg");
        }})
        .build());
    list.add(track3);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track3)
        .title("청운문학도서관")
        .runningTime("00:36")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/3.+%EC%B2%AD%EC%9A%B4%EB%AC%B8%ED%95%99%EB%8F%84%EC%84%9C%EA%B4%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track3)
        .title("Chungwoon Literary Library")
        .runningTime("00:28")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/03+Chungwoon+Literary+Library.mp3")
        .build());

    AudioTrack track4 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.967117)
        .locationLatitude(37.592100)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/4-1.jpeg");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/4-2.jpeg");
        }})
        .build());
    list.add(track4);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track4)
        .title("윤동주 문학관")
        .runningTime("02:13")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/4.+%EC%9C%A4%EB%8F%99%EC%A3%BC+%EB%AC%B8%ED%95%99%EA%B4%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track4)
        .title("Yoon Dong-ju Literary Museum")
        .runningTime("02:11")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/04+Yoon+Dong-ju+Literary+Museum.mp3")
        .build());

    AudioTrack track5 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.966252)
        .locationLatitude(37.594342)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/5-1.png");
        }})
        .build());
    list.add(track5);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track5)
        .title("환기 미술관")
        .runningTime("01:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/5.+%ED%99%98%EA%B8%B0+%EB%AF%B8%EC%88%A0%EA%B4%80.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track5)
        .title("Whanki Art Museum")
        .runningTime("01:12")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/05+Whanki+Art+Museum.mp3")
        .build());

    AudioTrack track6 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.967652)
        .locationLatitude(37.602814)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/6-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/6-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/6-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/6-4.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/6-5.png");
        }})
        .build());
    list.add(track6);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track6)
        .title("백사실 계곡")
        .runningTime("01:27")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/6.+%EB%B0%B1%EC%82%AC%EC%8B%A4+%EA%B3%84%EA%B3%A1.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track6)
        .title("Baeksasil Valley")
        .runningTime("01:17")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/06+Baeksasil+Valley.mp3")
        .build());

    AudioTrack track7 = audioTrackRepository.save(AudioTrack.builder()
        .locationLongitude(126.967933)
        .locationLatitude(37.595373)
        .images(new ArrayList<String>() {{
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/7-1.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/7-2.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/7-3.png");
          add(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/images/Buam-dong/7-4.png");
        }})
        .build());
    list.add(track7);
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.KOREAN)
        .audioTrack(track7)
        .title("산모퉁이 카페")
        .runningTime("01:18")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/kor/7.+%EC%82%B0%EB%AA%A8%ED%89%81%EC%9D%B4+%EC%B9%B4%ED%8E%98.mp3")
        .build());
    audioTrackLanguageContentRepository.save(AudioTrackLanguageContent.builder()
        .language(Language.ENGLISH)
        .audioTrack(track7)
        .title("Cafe ‘San-mo-tung-i’")
        .runningTime("01:05")
        .audioFileUrl(AWS_CLOUD_FRONT_URL_PREFIX + "/audio-guides/audio_tracks/audio-files/Buam-dong/eng/07+Cafe+%E2%80%98San-mo-tung-i%E2%80%99.mp3")
        .build());


    for (int order = 0; order < list.size(); order++) {
      audioGuideTrackContainerRepository.save(
          AudioGuideTrackContainer.builder()
              .audioTrack(list.get(order))
              .orderNumber(order + 1)
              .audioGuide(audioGuide)
              .build());
    }
  }
}
