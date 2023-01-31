package com.great.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.great.controller.*;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.great.domain.FamilyVO;
import com.great.domain.ProfileVO;
import com.great.scheduled.KakaoTokenRefresh;
import com.great.scheduled.SendKakaoMsg;
import com.great.service.KakaoAPI;
import com.great.service.MemberService;
import com.great.service.NaverAPI;
import com.great.util.Util;
import com.great.domain.KakaoVO;

@Controller
public class MemberController {
	
    @Autowired
    private MemberService service;
    @Autowired
    private KakaoAPI kakao;
    @Autowired
    private NaverAPI naver;
    @Autowired
    ServletContext application;
    
	@Autowired
	private KakaoTokenRefresh KakaoRefreshScheduled;
    


    // 로그인 뷰 
	@GetMapping("/login")
	public String loginView(HttpServletRequest request) {
		System.out.println("[뷰로그] 로그인 뷰 ");
		return "index"; 	
	}
	
	// 로그인 체크 과정
	@RequestMapping(value="/login", produces="application/text; charset=utf8")
	@ResponseBody
	public String login(@RequestParam("userId") String userId,
			@RequestParam("userPwd") String userPwd,FamilyVO vo, HttpServletRequest request) throws Exception {
		System.out.println("[로그] 로그인 과정 실행중");
		
		String id="", pw="";
		
		// id와 pw를 받아서 null값이 아닌 경우 로그인 시도했던 id와 pw에 데이터 집어넣기
		if(request.getParameter("userId")!=null) {
			id = request.getParameter("userId");
			pw = request.getParameter("userPwd");	
		}
		
		// 로그인 시도하려는 ID의 Salt값을 디비에서 가져와서 저장
		String salt = service.getFamilySalt(id);
		
		// salt 값이 DB에 있다면
		if (salt != null) {
			// 가져온 salt 값으로 암호화
			String PW = Util.Hashing(pw.getBytes(), salt);
			// 암호화된 값을 vo에 저장
			vo.setId(id);
			vo.setPw(PW);
		} else {
			System.out.println("[로그] 로그인 에러");
			return "계정의 ID 또는 비밀번호가 잘못되었습니다";
		}
		
		// 암호화된 계정 정보로 조회
		FamilyVO user = service.getFamily(vo);
		
		// DB에 계정 정보와 동일하다면
		if (user != null) {
			HttpSession session = request.getSession();
			// 로그인된 세션을 부여해준다
			session.setAttribute("user", user);
			System.out.println("[로그]" + user.getId() + "님이 로그인하셨습니다");
			// 프로필 페이지로 넘김
			return "로그인";
		} else {
			System.out.println("[로그] 패스워드 에러");
			return "계정의 ID 또는 비밀번호가 잘못되었습니다"; 
		}
	}
	
	
    // 아이디/패스워드 뷰 
	@GetMapping("/login/idpwdcheck")
	public String loginidpwdcheckView(FamilyVO vo) {
		System.out.println("[뷰로그] ID/PWD 찾기 뷰 ");
		return "idpwdcheck";
	}
	
	// 임시 비밀번호 발송 프로세스
	@RequestMapping(value="/login/pwdcheck", produces="application/text; charset=utf8")
	@ResponseBody
	public String pwdcheck(@RequestParam("userId") String userId,
			@RequestParam("userEmail") String userEmail,FamilyVO vo, HttpServletRequest request) throws Exception {
		vo.setId(userId);
		vo.setEmail(userEmail);
		
		// 파라미터로 받은 아이디와 이메일을 가져와서 그 계정정보로 계정 정보 가져오기
		FamilyVO family = service.getFamilyIdEmailChk(vo);
		
		// 그 아이디와 이메일이 없는 계정이라면 다시 idpwdcheck 창으로 보내기
		if(family==null) {
			return "잘못된 아이디 또는 이메일이 입력되었습니다<br> 다시 한번 확인해주세요";
		}

		// 이메일 제목
		String subject = "[Great] 임시 비밀번호 발급 이메일입니다";
		// 이메일 메시지
		String msg ="";
		// 새로운 비밀번호 발급
		String newpwd = Util.newPwd();
		// 새로운 Salt값 가져오기
		String salt = Util.getSALT();
		// 새로운 비밀번호에 새로운 Salt값으로 암호화
		String hashPW = Util.Hashing(newpwd.getBytes(), salt);
		// 보낼 메시지 내용 저장
		msg = "<h3> 임시비밀번호는 " + newpwd + "입니다";
		
		// salt값과 암호화된 비밀번호 다시 저장
		vo.setPw(hashPW);
		vo.setSalt(salt);
		
		// 해당 계정 정보 업데이트
		service.updatePwdFamily(vo);
		
		// 이메일 발송하기
		Util.sendMail(family.getEmail(), subject, msg);
		
		return family.getEmail() + "로<br> 임시메일이 발송처리되었습니다";
	}
	
	// 아이디 체크 프로세스
	@RequestMapping(value="/login/idcheck", produces="application/text; charset=utf8")
	@ResponseBody
	public String idcheck(@RequestParam("userEmail") String userEmail, FamilyVO vo) throws Exception {
		// 이메일을 가지고 아이디 체크해서 계정 정보 가져오기
		vo.setEmail(userEmail);
		FamilyVO family = service.getID(vo.getEmail());
		// 해당 이메일을 조회했을 때 없는 계정이면 다시 페이지로 가기
		if(family==null) {
			return "해당 이메일로는 아이디가 조회되지 않습니다";
		}
		// 받은 아이디 정보를 가지고 그 다음페이지에 넘기기
		String userid = family.getId();
		return "유저의 ID는 " + userid + "입니다";
	}
	
	// 회원가입 뷰
	@GetMapping("/register")
	public String registerView(FamilyVO vo) {
		System.out.println("[뷰로그] 회원가입 뷰 접속");
		return "register";
	}
	
	// 회원가입 프로세스
	@PostMapping("/register")
	public String register(FamilyVO vo) throws Exception {
		// Salt값 가져오기
		String SALT = Util.getSALT();
		// 비밀번호 암호화하기
		String PW = Util.Hashing(vo.getPw().getBytes(), SALT);
		
		// salt값하고 암호화된 비밀번호 저장하기
		vo.setSalt(SALT);
		vo.setPw(PW);

		// 입력정보로 회원가입 시키기
		service.insertFamily(vo);
		
		return "index";
	}
	
	// 회원가입 ID 중복체크
	@RequestMapping(value="/login/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public int memberIdChkPOST(String id) throws Exception{
		// Id를 체크해서 반환 값 리턴해주기(ajax방식)
		int Chk = service.getFamilyIdChk(id);
		return Chk;
	}
	
	
	// 프로필 뷰 및 프로세스
	@GetMapping("/profile")
	public String profileView(Model model, HttpServletRequest request) {
		System.out.println("[뷰로그] 프로필 뷰 접속");
		// 세션에 저장된 유저 정보 가져오기
		HttpSession session = request.getSession();
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		
		// 가져온 유저 정보의 아이디로 고객의 프로필 정보를 조회해서 리스트로 담기
		List<ProfileVO> profileList = service.getProfileList(user.getId());
		
		// 받아온 리스트를 다음 페이지로 넘기기
		model.addAttribute("profileList", profileList);
		return "profile";
	}
	
		
	// 프로필 등록 뷰
	@GetMapping("/register/profile")
	public String registerProfileView(ProfileVO vo) {
		System.out.println("[뷰로그] 프로필 등록 뷰 접속 ");
		return "registerprofile";
	}
	
	// 프로필 등록 프로세스
	@PostMapping("/registerprofile")
	public String registerProfile(ProfileVO vo, HttpServletRequest request) throws Exception {
		// 유저 세션 정보를 가져와서
		HttpSession session = request.getSession();
		FamilyVO user = (FamilyVO)session.getAttribute("user");
		// 그 전페이지에서 받았던 vo에 userid만 추가시킨다
		vo.setId(user.getId());
		// 그 정보로 프로필 등록 완료
		service.insertProfile(vo);
		System.out.println("[로그]" + user.getId() + "님이 프로필을 생성했습니다");
		return "redirect:/profile";
	}
	
	
	// KaKao 로그인 API
	@RequestMapping(value="/login/kakao")
	public String Kakaologin(@RequestParam("code") String code, HttpSession session, Model model) throws IOException {
		KakaoVO kakaoVO = new KakaoVO();
		
	    kakaoVO = kakao.getAccessToken(code);
	   
	    HashMap<String, String> userInfo = kakao.getUserInfo(kakaoVO.getAccess_token());
	    //kakao.getfriend(access_Token);
	    
	    String id = userInfo.get("id");
	    System.out.println("id");
	    System.out.println("nickname");
	    String nickname = userInfo.get("nickname");
	    FamilyVO user = service.getAPIUser(id);
	    
	    if(user == null) {
	    	model.addAttribute("id",id);
	    	model.addAttribute("nickname",nickname);
	    	return "registerapi";
	    }
	    else {
	    	session.setAttribute("user", user);
	    	session.setAttribute("access_Token", kakaoVO.getAccess_token());
	    	session.setAttribute("refresh_Token", kakaoVO.getRefresh_token());
	    	KakaoRefreshScheduled.startScheduler(session);
	    	//Jsoup.connect("http://127.0.0.1:8282/getToken?token=" + kakaoVO.getAccess_token()).ignoreContentType(true).get();
	    	System.out.println("로그인 토큰" + (String)session.getAttribute(("access_Token")));
	    	return "redirect:/profile";
	    }
	}
	
	// 카카오 토큰 리프래시
	@RequestMapping(value="/refresh", method = RequestMethod.GET,produces = "application/text; charset=utf8")
	@ResponseBody
	public String refresh(HttpSession session) {
		System.out.println("시도됨");
		if(session.getAttribute("refresh_Token")!=null) {
		String refresh_token = (String)session.getAttribute("refresh_Token");
		System.out.print(refresh_token);
		KakaoVO token = kakao.refreshToken(refresh_token);
		session.setAttribute("access_Token", token.getAccess_token());
		System.out.println("access: " + token.getAccess_token());
		}
		return "sucess";
	}

	// Naver 로그인 API
	@RequestMapping(value="/login/naver")
	public String Naverlogin(@RequestParam("code") String code,@RequestParam("state") String state, HttpSession session, Model model) throws UnsupportedEncodingException {
		String access_Token =  naver.getAccessToken(code,state);
		HashMap<String, String> userInfo = naver.naverProfile(access_Token);
	    String id = userInfo.get("id");
	    String nickname = userInfo.get("nickname");
	    FamilyVO user = service.getAPIUser(id);
	    
	    if(user == null) {
	    	model.addAttribute("id",id);
	    	model.addAttribute("nickname",nickname);
	    	return "registerapi";
	    }
	    else {
	    	session.setAttribute("user", user);
	    	session.setAttribute("access_Token", access_Token);
	    	return "redirect:/profile";
	    }
	}
	
	// 네이버, 카카오 회원가입 프로세스
	@PostMapping("/login/registerapi")
	public String registerapi(FamilyVO vo) throws Exception {
		String pw = Util.getPWD();
		// Salt값 가져오기
		String SALT = Util.getSALT();
		// 비밀번호 암호화하기
		String PW = Util.Hashing(pw.getBytes(), SALT); 	
		
		// salt값하고 암호화된 비밀번호 저장하기
		vo.setSalt(SALT);
		vo.setPw(PW);

		// 입력정보로 회원가입 시키기
		service.insertFamily(vo);
		
		return "index";
	}
	
	// 회원탈퇴
	@GetMapping("/outoffamily")
	public String outoffamily() {
		return "outoffamily";
	}
	
	// 프로필탈퇴
	@GetMapping("/outofprofile")
	public String outofprofile() {
		return "outofprofile";
	}
	
	// 회원탈퇴
	@GetMapping("/outoffamily/delete")
	public String outoffamilydelete(HttpSession session) {
		FamilyVO user = (FamilyVO) session.getAttribute("user");
		service.deleteAllMemo(user);
		service.deleteAllSprout(user);
		service.deleteAllfunding_list(user);
		service.deleteAllFoodlist(user);
		service.deleteAllMileage(user);
	    service.deleteAllProfile(user);
	    service.deleteFamily(user);
		
		session.invalidate();
		return "redirect:/";
	}
	
	// 프로필탈퇴
	@GetMapping("/outofprofile/delete")
	public String outofprofiledelete(HttpSession session) {
		ProfileVO profile = (ProfileVO) session.getAttribute("profile");
	    service.deleteProfileMemo(profile);
		service.deleteProfile(profile);
		
		session.removeAttribute("profile");
		return "redirect:/profile";
	}
	
	@GetMapping("/profilemain/logout")
	public String profilelogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}