let txt = "";
let maker_main = document.getElementById("Maker_main");
let ref = [
  "쌀",
  "안심",
  "콩나물",
  "청포묵",
  "미나리",
  "소금",
  "국간장",
  "다진파",
  "다진마늘",
  "참기름",
  "고추장",
  "설탕",
  "숙주",
  "도라지",
  "고사리",
  "계란",
  "양지머리",
  "멥쌀",
  "찹쌀",
  "수수",
  "차조",
  "콩",
  "팥",
  "당면",
  "돼지고기",
  "표고버섯",
  "호박",
  "당근",
  "부추",
  "청고추",
  "홍고추",
  "진간장",
  "밥",
  "통후추",
  "쇠고기",
  "파",
  "마늘",
  "깨소금",
  "고춧가루",
  "흑설탕",
  "계핏가루",
  "깐밤",
  "대추",
  "잣",
  "물엿",
  "식물성기름",
  "흰설탕",
  "간장",
  "물",
  "청동호박",
  "흑임자",
  "감자",
  "양파",
  "완두콩",
  "카레",
  "우유",
  "쌈장",
  "후춧가루",
  "청피망",
  "홍피망",
  "오이",
  "전분",
  "멸치",
  "다시마",
  "밀가루",
  "애호박",
  "실파",
  "양념장",
  "냉면",
  "동치미국물",
  "배",
  "식초",
  "쇠고기육수",
  "동치미무",
  "국수",
  "열무",
  "찹쌀가루",
  "대파",
  "다진생강",
  "겨자",
  "상추",
  "깻잎",
  "녹말",
  "계란흰자",
  "오징어",
  "새우",
  "홍합",
  "죽순",
  "멸칫국물",
  "청주",
  "김치",
  "두부",
  "만두피",
  "육수",
  "모시조개",
  "북어",
  "무",
  "미역",
  "다진쇠고기",
  "식용유",
  "불린미역",
  "생태",
  "쌀뜨물",
  "연어",
  "레몬즙",
  "양송이버섯",
  "올리브오일",
  "방울토마토",
  "페스토소스",
  "모듬채소",
  "차이브",
  "삶은 문어살",
  "갑오징어",
  "꼴뚜기",
  "중새우살",
  "쭈꾸미",
  "참소라살",
  "가리비",
  "검은껍질홍합",
  "가재새우",
  "샐러리",
  "루콜라",
  "트레비소",
  "소스",
  "재첩",
  "도미",
  "바질",
  "블랙올리브",
  "케이퍼",
  "스위트마조람",
  "화이트와인",
  "계란노른자",
  "오레가노",
  "그린올리브",
  "햇살담은간장",
  "느타리버섯",
  "꼬치",
  "꿀",
  "배즙",
  "생강즙",
  "피망",
  "토마토케첩",
  "날콩가루",
  "생강",
  "상추잎",
  "연겨자",
  "적양배추잎",
  "파프리카",
  "실고추",
  "우엉",
  "토마토",
  "다진양파",
  "참빛고운포도씨유",
  "잔멸치",
  "갈치",
  "닭",
  "고추",
  "들깨가루",
  "초고추장",
  "양념간장",
  "칼국수면",
  "양배추잎",
  "밤",
  "무명실",
  "파슬리가루",
  "대구살",
  "목이버섯",
  "파인애플",
  "갈비",
  "양파즙",
  "통깨",
  "월계수잎",
  "통마늘",
  "버터",
  "돼기고기",
  "레드와인",
  "토마토케찹",
  "빵가루",
  "은행",
  "된장",
  "배추",
  "새우젓",
  "굴",
  "갓",
  "젓국",
  "파슬리",
  "청국장",
  "배추김치",
  "김칫국물",
  "순두부",
  "바지락",
  "동태",
  "가래떡",
  "흰콩",
  "배춧잎",
  "통북어",
  "유부",
  "단무지",
  "해파리",
  "게맛살",
  "멸치젓",
  "풋배추",
  "[절임용 소금물] 소금",
  "총각무",
  "고추가루",
  "[절임용 소금물] 물",
  "배주머니",
  "꽃게",
  "술",
  "청정원맛선생",
  "아구",
  "싸리버섯",
  "조기",
  "명란",
  "쑥갓",
  "[쇠고기양념] 간장",
  "우거지",
  "팽이버섯",
  "어묵",
  "곤약",
  "낙지",
  "선지",
  "장조림",
  "김",
  "양",
  "후추",
  "소 잡뼈",
  "사골",
  "도가니",
  "밀국수",
  "까나리액젓",
  "석이버섯",
  "[절임용소금물] 소금",
  "말린표고버섯",
  "통생강",
  "[국물용 소금물] 소금",
  "연근",
  "조갯살",
  "냉이",
  "더덕",
  "꽈리고추",
  "마른오징어",
  "마늘종",
  "녹말가루",
  "메주콩",
  "칵테일새우",
  "장식용어묵",
  "돼지갈비",
  "참치",
  "문어",
  "고추냉이",
  "꽁치",
  "통도라지",
  "양배추",
  "넛맥",
  "라면",
  "햄",
  "치커리",
  "다시물",
  "맛술",
  "와사비",
  "흰후춧가루",
  "가는파",
  "굵은파",
  "도토리묵",
  "고등어",
  "미림",
  "마른고추",
  "가지",
  "마른새우",
  "골뱅이통조림",
  "소면",
  "잣가루",
  "스파게티",
  "생크림",
  "베이컨",
  "닭고기",
  "수삼",
  "맛살",
  "후루츠칵테일",
  "은박컵",
  "식빵",
  "양상추",
  "피클",
  "슬라이스치즈",
  "들깨",
  "머스터드",
  "마요네즈",
  "요리술",
  "컬리플라워",
  "사과",
  "자몽",
  "딸기",
  "오렌지마요네즈",
  "훈제연어",
  "앤다이브",
  "트레비스",
  "시금치",
  "파마산치즈치즈",
  "레몬드레싱",
  "레몬주스",
  "홀토마토",
  "프랑크소시지",
  "곱창",
  "우동면",
  "아귀",
  "미더덕",
  "강력분",
  "드라이이스트",
  "오렌지마멀레이드",
  "건포도",
  "검은깨",
  "붉은 고추",
  "무순",
  "쑥",
  "솔잎",
  "풋콩",
  "깨",
  "녹두",
  "포도씨유",
  "쇠고기(힘줄없는부위)",
  "식용유/소금/참기름/잣가루",
  "토란",
  "[쇠고기양념] 다진파",
  "튀김가루",
  "다진파슬리",
  "누룽지",
  "적파프리카",
  "황파프리카",
  "브로콜리",
  "찬밥",
  "쌀가루",
  "찬물",
  "삶은팥",
  "아몬드가루",
  "슈가파우더",
  "고구마줄기",
  "들기름",
  "쇠고기 육수",
  "통계피",
  "황설탕",
  "곶감",
  "호두",
  "정종",
  "감자전분",
  "조개살",
  "굴소스",
  "떡국떡",
  "취나물",
  "다진풋고추",
  "케일",
  "청경채",
  "겨자잎",
  "건새우가루",
  "달래",
  "청양고추",
  "육수용멸치",
  "양송이",
  "명란젓",
  "봄동",
  "옥수수",
  "풋고추",
  "정향",
  "송이버섯",
  "말린새우",
  "김치잎",
  "근대잎",
  "팥삶은물",
  "말린 표고버섯",
  "레몬",
  "왜된장",
  "맛국물",
  "생새우",
  "콘프레이크",
  "불린쌀",
  "생선살",
  "새우살",
  "게",
  "대하",
  "고구마",
  "얼음물",
  "다진청고추",
  "다진홍고추",
  "녹차분말",
  "모짜렐라치즈",
  "꽃상추",
  "포도",
  "사우어크라우트",
  "오곡곡물",
  "코다리",
  "광어",
  "피조개",
  "청어알",
  "연어알",
  "전복",
  "초밥",
  "치즈",
  "홍차티백",
  "콘플레이크",
  "카레가루",
  "멸치다시물",
  "무즙",
  "비엔나소시지",
  "액체육젓",
  "생굴",
  "인절미",
  "바게트",
  "신김치",
  "사이다",
  "조미술",
  "피쉬소스",
  "겨자가루",
  "닭다리",
  "들깻가루",
  "땅콩가루",
  "조청",
  "맛소금",
  "매실장아찌",
  "랩",
  "시금치즙",
  "당근즙",
  "고들빼기",
  "멸치액젓",
  "두릅",
  "참나물",
  "완두콩통조림",
  "옥수수통조림",
  "우스터소스",
  "아스파라거스",
  "풋마늘",
  "오이피클",
  "체리",
  "땅콩버터",
  "케첩",
  "핫소스",
  "꽁치통조림",
  "다진대파",
  "채썬쇠고기",
  "춘권피",
  "조미료",
  "김칫잎",
  "[멸치장국] 국멸치",
  "[불고기양념] 간장",
  "크림수프",
  "순대",
  "쫄면",
  "흰떡",
  "열무김치",
  "이스트",
  "쌀국수",
  "다진고추",
  "아욱",
  "떡",
  "해삼",
  "두반장",
  "닭안심",
  "새송이버섯",
  "숙주나물",
  "파스타",
  "닭봉",
  "닭육수",
  "오징어채",
  "칼국수",
  "청장",
  "새우젓국",
  "껍질콩",
  "대구",
  "기름",
  "중국부추",
  "닭살",
  "보리",
  "들깻잎",
  "조림간장",
  "국멸치",
  "토마토페스트",
  "파래",
  "단호박",
  "파마산치즈",
  "라이스페이퍼",
  "키위",
  "다시마국물",
  "치즈가루",
  "일본된장",
  "가쓰오브시",
  "양겨자",
  "페투치네",
  "흰후추",
  "오보로",
  "게살",
  "아보카도",
  "박고지",
  "날치알",
  "초생강",
  "조",
  "냉동만두",
  "올리브",
  "가쓰오브시국물",
  "흰살생선",
  "미원",
  "쇠고기스톡",
  "토마토페이스트",
  "국물용멸치",
  "메밀국수",
  "맛살조개",
  "해초",
  "장어",
  "산초가루",
  "장어대리",
  "장어뼈",
  "동태살",
  "돈까스소스",
  "바나나",
  "타바스코",
  "스위트콘",
  "고추기름",
  "녹말물",
  "간수",
  "우묵",
  "잔새우",
  "적채",
  "골뱅이",
  "체리알",
  "사워크림",
  "청정원굴소스",
  "튀김기름",
  "치자",
  "맛조개",
  "폰즈소스",
  "와사비소스",
  "깨소스",
  "채썬마늘",
  "채썬생강",
  "고춧기름",
  "생대구",
  "머스타드",
  "채소",
  "생밤",
  "양념",
  "[절임간장] 진간장",
  "영양부추",
  "쌈다시마",
  "무말랭이",
  "육회",
  "계란후라이",
  "굵은고춧가루",
  "고운고춧가루",
  "크림치즈",
  "무,래디쉬",
  "간마늘",
  "간생강",
  "피시소스",
  "고수잎",
  "[쇠고기육수] 쇠뼈",
  "쇠꼬리",
  "팔각",
  "얼갈이배추",
  "굵은소금",
  "쪽파",
  "꽃소금",
  "숯",
  "메주",
  "엿기름",
  "시럽",
  "호박씨",
  "근대",
  "순창콩된장",
  "붉은갓",
  "삭힌고추",
  "고운소금",
  "저민마늘",
  "[초고추장] 고추장",
  "녹차국수",
  "우무",
  "생수",
  "서리태콩",
  "참깨",
  "닭발",
  "통파",
  "전어",
  "콩가루",
  "전어젓갈",
  "타임",
  "샐러드오일",
  "소꼬리",
  "시금치나물",
  "도라지나물",
  "고사리나물",
  "고기산적",
  "토란탕",
  "멥쌀가루",
  "미소된장",
  "가다랑이포",
  "액젓",
  "[양념장] 다진파",
  "돼지등갈비",
  "바베큐소스",
  "비트",
  "절임무",
  "네모난햄",
  "닭가슴살",
  "오렌지",
  "허니머스터드",
  "참치통조림",
  "꽃빵",
  "돼지고기안심",
  "청정원국선생",
  "가쯔오브시",
  "청정원순창쌈장",
  "청정원어장",
  "[육수] 시판용장국",
  "새싹채소",
  "플레인요구르트",
  "발효초",
  "초콜릿",
  "과일통조림",
  "미니파프리카",
  "구운김",
  "다진돼지고기",
  "미소",
  "샐러드채소",
  "[배합초] 소금",
  "[비빔양념] 간장",
  "메기",
  "잉어",
  "[양념장] 고춧가루",
  "송어",
  "돼지 볼살",
  "이태리파슬리",
  "그라나 빠다노",
  "닭 육수",
  "이태리고추",
  "사라다나",
  "로즈마리",
  "물(쇠고기육수)",
  "아기된장소스",
  "시래기",
  "다시마 우린 물",
  "사과즙",
  "쇠고기(안심 또는 등심)",
  "홍피망, 청피망 각각",
  "후추, 식용유",
  "칠리",
  "다진 마늘",
  "월계수 잎",
  "볶은 흑임자(검은깨)",
  "땅콩",
  "인삼",
  "참쌀",
  "절인 배추",
  "낙지다리",
  "감동젓",
  "다진생각",
  "소급",
  "무지개고추",
  "찐옥수수 알갱이",
  "슈레드 치즈(모짜렐라치즈)",
  "올리브유",
  "육수용 무",
  "국물용 다시마",
  "육수용 대파",
  "육수용 물",
  "스파게티면",
  "파마르산치즈",
  "소금, 후추",
  "소고기",
  "버섯",
  "돛나물",
  "매실액",
  "노란 파프리카",
  "붉은 파프리카",
  "파뿌리",
  "미니새송이버섯",
  "다진쪽파",
  "노각",
  "허니머스타드",
  "고구마잎",
  "피자치즈",
  "토마토소스",
  "파마산치즈가루",
  "호박잎",
  "둥근호박",
  "모닝빵",
  "슬라이스햄",
  "어린잎채소",
  "마요네스",
  "라임",
  "허브(민트)",
  "탄산수",
  "얼음",
  "에멘탈 치즈가루",
  "아몬드",
  "다진식파",
  "백오이",
  "참치캔",
  "삶은계란",
  "견과류",
  "허브솔트",
  "머드터드",
  "부침유",
  "부침가루",
  "당근잎",
  "당근채",
  "배추잎",
  "달걀",
  "다짐육(돼지고기)",
  "다짐육(소고기)",
  "무채",
  "연어살",
  "건블루베리",
  "달걀노른자",
  "셀러리",
  "중새우",
  "김밥용김",
  "콩비지",
  "다짐육",
];

MakeAuto = () => {
  ref.forEach(function (arg) {
    if (arg.indexOf(txt) > -1) {
      $("#autoMaker").append($("<div>").text(arg));
    }
  });
};

MakeAutoClick = () => {
  $("#autoMaker")
    .children()
    .each(function () {
      $(this).click(function () {
        $("#search_area").val($(this).text());
        let span = document.createElement("span");
        span.setAttribute("class", "list");
        span.innerHTML = $(this).text();
        maker_main.appendChild(span);
        $("#autoMaker").children().remove();
        isComplete = true;
        $("#autoMaker").css("display", "none");
        $("#search_area").val("");
      });
    });
};

$("#search_area").focus(function () {
  $("#autoMaker").css("display", "block");
  if ($("#search_area").val() == "") {
    MakeAuto();
    MakeAutoClick();
  } else {
    var txt = $(this).val();
    if (txt != "") {
      //빈줄이 들어오면
      $("#autoMaker").children().remove();
      MakeAuto();
      MakeAutoClick();
    } else {
      $("#autoMaker").children().remove();
    }
  }
});

let isComplete = false; //autoMaker 자식이 선택 되었는지 여부
$("#search_area").keyup(function () {
  $("#autoMaker").css("display", "block");
  txt = $(this).val();
  if (txt != "") {
    //빈줄이 들어오면
    $("#autoMaker").children().remove();
    MakeAuto();
    MakeAutoClick();
  } else {
    $("#autoMaker").children().remove();
  }
});

let text = "";

cleartext = () => {
  $("#Maker_main").empty();
};


