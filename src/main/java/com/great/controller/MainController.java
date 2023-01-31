package com.great.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.great.controller.*;
import com.great.domain.FamilyVO;
import com.great.domain.FoodListVO;
import com.great.domain.FundingCorpVO;
import com.great.domain.FundingListVO;
import com.great.domain.FundingVO;
import com.great.domain.LimitFoodListVO;
import com.great.domain.MemoVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;
import com.great.domain.RaisingVO;
import com.great.domain.RecipeCategoriVO;
import com.great.domain.RecipeVO;
import com.great.scheduled.KakaoTokenRefresh;
import com.great.scheduled.SendKakaoMsg;
import com.great.service.FoodListService;
import com.great.service.KakaoAPI;
import com.great.service.MemberService;
import com.great.service.RecipeService;
import com.great.util.Util;

@Controller
public class MainController {
	// 프로필 선택 후 메인 화면 뷰
    @Autowired
    private MemberService service;
    
    @Autowired
    private KakaoAPI kakao;

	@Autowired
	private SendKakaoMsg KakaoSendMsgScheduled;
	
	@Autowired
	private FoodListService foodlistService;
	
	@Autowired
	private RecipeService recipeservice;


	// 프로필 선택 후 메인 화면 뷰 부분
	@GetMapping("/main")
	public String mainView(Model model, HttpSession session) throws ParseException {
		System.out.println("[뷰로그] 메인화면 접속");
		FamilyVO user = (FamilyVO)session.getAttribute("user");
    	// 모든 재료 리스트 가져오
    	List<FoodListVO> foodlist = foodlistService.getFoodList(user);
    	// 유통기한이 3일 내로 남은 것들만 담을 리스트 생성
    	List<LimitFoodListVO> limitfoodlist = new ArrayList<LimitFoodListVO>();
    	// 오늘 날짜 생성
    	Date today = new Date();
    	// 날짜 포멧 정하기
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
    	String date1 = format.format(today);
    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
    	Date FirstDate = format.parse(date1);
    	
        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
    	for(FoodListVO food: foodlist) {
    		
    		LimitFoodListVO limitfood = new LimitFoodListVO();
    		String date2 = food.getEndDate();
    		Date SecondDate = format.parse(date2);
    		
            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
            // 나온 결과 값에서 일 수로 전환 하기
    		long calDateDays = calDate / ( 24*60*60*1000); 
    		// 만약 3일 이내로 남았을 경우
    		if(calDateDays >= -3) {
    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
    			limitfood.setCls(food.getCls());
    			limitfood.setDate(Long.toString(calDateDays));
    			limitfood.setEndDate(food.getEndDate());
    			limitfood.setId(food.getId());
    			limitfood.setImg(food.getImg());
    			limitfood.setInDate(food.getInDate());
    			limitfoodlist.add(limitfood);
    		}
    	}
 
    	model.addAttribute("limitfoodlist",limitfoodlist);
    	
    	 

		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
							
		RecipeCategoriVO categori = recipeservice.getProfileRecipe(profile);
		
		List<String> nation_list = new ArrayList<String>();
		String[] temp_nation = categori.getNation_nm().split(",");

		List<String> ty_list = new ArrayList<String>();
		String[] temp_ty = categori.getTy_nm().split(",");
		
		List<String> level_list = new ArrayList<String>();
		String[] temp_level = categori.getLevel_nm().split(",");
		
		
		for (String str : temp_nation) {
			nation_list.add(str);
		}
		
		for (String str : temp_ty) {
			ty_list.add(str);
		}
		
		for (String str : temp_level) {
			level_list.add(str);
		}
		
		
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();
		List<RecipeVO> finalrecipeList = new ArrayList<RecipeVO>();
		
		recipeList = recipeservice.getFavoriteRecipeList(nation_list, ty_list, level_list);
		
		String allergy = service.getUserAllergy(profile);
		
    	ArrayList<String> allergyList = new ArrayList<String>();
    	
		String[] strList = allergy.split(":");
		
		System.out.println("b : "+allergy);
		
		if(allergy.equals("")) {
			model.addAttribute("recipeList",recipeList);
		} else {
			for (String str : strList) {
				allergyList.add(str);
			}
			

			for(RecipeVO recipe : recipeList) {
				int num = 0;
				List<String> ingrelist = new ArrayList<String>();
				ingrelist = service.getFoodIngre(recipe.getRecipe_id());
				for(String ingre : ingrelist) {
					
					for(String all :  allergyList) {
						System.out.println(ingre + " = " + all);
						if(ingre.contains(all)) {
							num = 1;
						} 
					}
				}
				if(num == 0) {
					finalrecipeList.add(recipe);
				}
			}
				
		
			model.addAttribute("recipeList",finalrecipeList);
		}
		List<MemoVO> MemoList = service.getMemoList(user.getId());
	
		model.addAttribute("memolist", MemoList);
		
		MileageVO serchVO = new MileageVO();
		
		serchVO.setDate(date1);
		serchVO.setId(user.getId());
		
		List<MileageVO> todayMileage = service.getTodayMileage(serchVO); 
		
		Calendar cal = Calendar.getInstance();
		
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int mileage = 0;
		
		if(hour <= 7) {
			try {
				for (int i = 0; i < 1; i++) {
					mileage += todayMileage.get(i).getMileage();
				}
				System.out.println("1 : " + mileage);
			}
			catch (Exception e){
			}
		}
		else if(hour <= 15) {
			try {
				for (int i = 0; i < 2; i++) {
					mileage += todayMileage.get(i).getMileage();
				}
				System.out.println("2 : " + mileage);
			}
			catch (Exception e){
			}
		}
		else if(hour <= 23) {
			try {
				for (int i = 0; i < 3; i++) {
					mileage += todayMileage.get(i).getMileage();
				}
				System.out.println("3 : " + mileage);
			}
			catch (Exception e){
			}
		}
		
		model.addAttribute("mileage", mileage);
		
		return "main";
	}
	
	// 프로필 선택 후 메인 화면 프로세스
	@GetMapping("/main/profile")
	public String mainprocess(ProfileVO vo,HttpServletRequest request) {
		System.out.println("[로그] 프로필 선택 후 메인 화면 과정 진행");
		HttpSession session = request.getSession();
		
		// 프로필을 선택했을 때 파라미터를 vo로 받아서 null 값이 아니면 profile 값을 세션에 저장하고 메인으로 넘김
		if (vo != null) {
			session.setAttribute("profile", vo);
			return "redirect:/main";
		// vo가 null 값이면 누굴 선택했는지 모르기 때문에 profile 화면으로 다시 리다이렉트 
		} else {
			return "redirect:/profile";
		}
	}	
	
	@GetMapping("/emartmall")
	public String emartmall() {
			return "emartmall";
	}	
	
	// 프로필 선택 화면으로 돌아가는 프로세스
	@GetMapping("/profile/logout")
	public String profilelogoutprocess(ProfileVO vo,HttpServletRequest request) {
		System.out.println("[로그] 프로필 세션 로그아웃");
		// 세션을 가져와서 profile 세션을 삭제시킴
		HttpSession session = request.getSession();
		session.removeAttribute("profile");
		return "redirect:/profile";
	}	
	
	// 계정 로그아웃 프로세스
	@GetMapping("/profile/userlogout")
	public String profileuserlogoutprocess(HttpServletRequest request) {
		// user 로그를 찍기 위한 코드
		HttpSession session = request.getSession();
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		System.out.println("[로그] " + user.getId()+ "님이 로그아웃 하셨습니다");
		if((String)session.getAttribute("access_Token")==null) {
			session.invalidate();
			// 로그인 페이지로 리다이렉트
			return "redirect:/login";
		}else {
			kakao.kakaoLogout((String)session.getAttribute("access_Token"));
			session.invalidate();
			// 로그인 페이지로 리다이렉트
			return "redirect:/login";
		}
	}	
	
	
	
	// 계정 정보 프로세스
	@GetMapping("/profile/userinfoproc")
	public String userinfoprocess(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
		
		FamilyVO userinfo = new FamilyVO();
		
		System.out.println("profile : " + profile.getId() + profile.getUsername());
		
		profile = service.getProfile(profile);
		userinfo = service.getUserinfo(profile.getId());
		
		model.addAttribute("profileinfo", profile);
		model.addAttribute("userinfo", userinfo);
		System.out.println("[뷰로그] 계정 정보 접속");
		return "userinfo";
	}	
	
	
	// 계정 정보 수정
	@GetMapping("/profile/useredit")
	public String userEdit(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
		FamilyVO userinfo = new FamilyVO();
		
		
		
		profile = service.getProfile(profile);
		userinfo = service.getUserinfo(profile.getId());
		
		model.addAttribute("profileinfo", profile);
		model.addAttribute("userinfo", userinfo);
		System.out.println("[뷰로그] 계정 정보 수정 접속");
		return "userinfoEdit";
	}	
	
	
	@PostMapping("/profile/usereditproc")
	public String userEditprocess(FamilyVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		// 파라미터로 받은 값에 로그인 세션에서 가져온 ID만 가져와서 추가
		user.setEmail(vo.getEmail());
		user.setAddress1(vo.getAddress1());
		user.setAddress2(vo.getAddress2());
		user.setAddress3(vo.getAddress3());
		user.setNickname(vo.getNickname());
		// 프로필 정보 수정하기
		service.updateUser(user);		
		return "redirect:/profile/userinfoproc";
	}	
	
	
	@PostMapping("/profile/usereditpwdproc")
	public String userEditpwdprocess(FamilyVO vo,HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		FamilyVO user = (FamilyVO)session.getAttribute("user");

		// Salt값 가져오기
		String SALT = Util.getSALT();
		// 비밀번호 암호화하기
		String PW = Util.Hashing(vo.getPw().getBytes(), SALT);
		user.setSalt(SALT);
		user.setPw(PW);

		service.updateUserpwd(user);		
		return "redirect:/profile/userinfoproc";
	}	
	
	

	
	
	// 프로필 정보 프로세스
	@GetMapping("/profile/infoproc")
	public String profileinfoprocess(Model model,HttpServletRequest request) {
		// 프로필 정보의 세션을 가져옴Model model
		HttpSession session = request.getSession();
		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
		// 가져온 세션의 프로필 이름으로 프로필 전체 정보를 조회

		profile = service.getProfile(profile);
		// model에 저장하여 profileinfo 페이지로 넘김
		model.addAttribute("profileinfo", profile);
		System.out.println("[뷰로그] 프로필 정보 접속");
		return "profileinfo";
	}	
	
	// 프로필 정보 수정
	@GetMapping("/profile/edit")
	public String profileEdit(Model model,HttpServletRequest request) {
		// 프로필 정보의 세션을 가져옴
		HttpSession session = request.getSession();
		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
		// 가져온 세션의 프로필 이름으로 프로필 전체 정보를 조회
		profile = service.getProfile(profile);
		// model에 저장하여 profileEdit 페이지로 넘김
		model.addAttribute("profileinfo", profile);
		System.out.println("[뷰로그] 프로필 수정 접속");
		return "profileEdit";
	}	
	
	
	
	// 프로필 정보 수정 프로세스
	@PostMapping("/profile/editprocess")
	public String profileEditprocess(ProfileVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		// 파라미터로 받은 값에 로그인 세션에서 가져온 ID만 가져와서 추가
		vo.setId(user.getId());
		// 프로필 정보 수정하기
		service.updateProfile(vo);		
		return "redirect:/profile/infoproc";
	}	
	
	@RequestMapping(value="/profile/edittime")
	public @ResponseBody HashMap<Object, Object> updateScheduler(@RequestParam  HashMap<Object, Object> params, HttpSession session) throws Exception{
		KakaoSendMsgScheduled.changeCronSet((String) params.get("cron"));
		KakaoSendMsgScheduled.startScheduler(session);
		
		HashMap<Object, Object> res = new HashMap<Object, Object>();
		res.put("res", "success");
		return res;
	}
	
	@RequestMapping(value="/profile/deletetime")
	public @ResponseBody HashMap<Object, Object> pauseScheduler(@RequestParam  HashMap<Object, Object> params) throws Exception{
		KakaoSendMsgScheduled.stopScheduler();
		HashMap<Object, Object> res = new HashMap<Object, Object>();
		res.put("res", "success");
		return res;
	}
	
	@GetMapping("/change")
	public String change() {
		return "change";
	}
	
	@GetMapping("/addmemo")
	@ResponseBody 
	public String addmemo(@RequestParam("memo") String memo, HttpSession session) throws Exception{
		
		Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
		// 년월일시분초 14자리 포멧
		SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String date = fourteen_format.format(date_now);
		
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
		
		String userid = user.getId();
		String username = profile.getUsername();
		
		MemoVO memo_text = new MemoVO();
		memo_text.setDate(date);
		memo_text.setId(userid);
		memo_text.setUsername(username);
		memo_text.setMemo(memo);
		service.insertMemo(memo_text);
		
		return "success";
	}
	
	@GetMapping("/sprout")
	public String sproutView(Model model, HttpSession session){
		
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		List<MileageVO> monthlyMileage = service.getMonthlyMileage(user.getId());
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			//윤년
			int[] MonthlyDay = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			model.addAttribute("MonthlyDay", MonthlyDay[month]);
		} else {
			//평년
			int[] MonthlyDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			model.addAttribute("MonthlyDay", MonthlyDay[month]);
		}
		
		boolean booleanMileage[] = new boolean[31];
		Arrays.fill(booleanMileage, false);
		
		for (MileageVO VO : monthlyMileage) {
			if(VO.getMileage() >= 100) {
				booleanMileage[Integer.parseInt(VO.getDate().split("-")[2])] = true;
			}
		}
		
		model.addAttribute("booleanMileage", booleanMileage);
		
		//기업 정보와 기업의 펀딩액 조회
		List<FundingCorpVO> corpInfo = service.getCorpInfo();
		List<RaisingVO> raising = new ArrayList<RaisingVO>();
		
		for (FundingCorpVO VO : corpInfo) {
			String corpName = VO.getName();
			String info = VO.getInfo();
			String imgsrc = VO.getImgsrc();
			int count = service.getFundRaising(corpName);
			
			RaisingVO temp = new RaisingVO();
			
			temp.setName(corpName);
			temp.setInfo(info);
			temp.setCount(String.valueOf(count));
			temp.setImgsrc(imgsrc);
			
			raising.add(temp);
		}
		
		model.addAttribute("raising", raising);
		
		//해당 사용자의 남은 새싹 갯수
		int sproutCount = service.getSproutCount(user.getId());
		int usedSproutCount = service.getUsedSproutCount(user.getId());
		
		String holdCount = String.valueOf(sproutCount - usedSproutCount);
		
		model.addAttribute("holdCount", holdCount);
		
		return "sprout";
	}
	
	@PostMapping("/sprout")
	public String sprout(FundingVO vo, HttpSession session){
		
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		//System.out.println(user);
		//System.out.println("post sprout");
		//System.out.println(vo);
		
		// 해당 기업에 count를 더하기
		List<FundingListVO> fundinglist = service.getFundingList(user.getId(), vo.getCorp());
		if(fundinglist == null) {
			//System.out.println("null : 새로 insert");
			service.insertFundingList(user.getId(), vo.getCorp(), vo.getCount());
		}
		else {
			//System.out.println("update");
			service.updateFundingList(user.getId(), vo.getCorp(), vo.getCount());
		}
		
		return "redirect:/sprout";
	}
	
	@GetMapping("/help")
	public String helpView(HttpSession session){
		
		
		return "help";
	}
}
