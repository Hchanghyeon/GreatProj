package com.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.great.domain.FamilyVO;
import com.great.domain.FundingCorpVO;
import com.great.domain.FundingListVO;
import com.great.domain.MemoVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;


public interface MemberMapper {
	
	@Select("select allergyinfo from profile where username=#{username}")
	public String getUserAllergy(ProfileVO profilevo);
	
	// 계정 정보의 salt값 가져오기
	@Select("select salt from family where id=#{id}")
	public String getFamilySalt(String familyID);
	
	// 계정 정보의 id를 조회해서 있는 ID인지 중복 체크하기
	@Select("select count(*) from family where id=#{id}")
	public int getFamilyIdChk(String familyID);
	
	// 계정의 ID와 Email로 조회해서 계정 정보 알아내기(임시 비밀번호 처리에 쓰임)
	@Select("select * from family where id=#{id} and email=#{email}")
	public FamilyVO getFamilyIdEmailChk(FamilyVO familyVO);
	
	// 계정의 ID와 PWD로 조회 (로그인 처리에 쓰임)
	@Select("select * from family where id=#{id} and pw=#{pw}")
	public FamilyVO getFamily(FamilyVO familyVO);
	
	// 카카오
	@Select("select * from family where id=#{id}")
	public FamilyVO getAPIUser(String id);
	
	// 계정의 이메일 정보로 조회 (아이디 찾기에 쓰임)
	@Select("select * from family where email=#{email}")
	public FamilyVO getID(String email);
	
	// 계정의 ID로 조회해서 프로필 정보들 가져오기(프로필 리스트 가져올 때 사용)
	@Select("select * from profile where id=#{id}")
	public List<ProfileVO> getProfileList(String id);
	
	// 프로필의 이름으로 조회해서 해당 프로필 정보 가져오기(프로필 정보 또는 프로필 수정에 사용)
	@Select("select * from profile where username=#{username} and id=#{id}")
	public ProfileVO getProfile(ProfileVO vo);
	
	// 프로필의 ID를 조회해서 해당 프로필의 계정 정보를 가져오기(계정 정보 또는 계정 정보 수정에 사용)
	@Select("select * from family where id=#{id}")
	public FamilyVO getUserinfo(String id);
	
	
	// 계정의 ID로 조회해서 프로필 정보들 가져오기(프로필 리스트 가져올 때 사용)
	@Select("select * from memo where id=#{id} order by num desc")
	public List<MemoVO> getMemoList(String id);
	
	// 오늘의 마일리지 가져오기
	@Select("select * from mileage where id=#{id} and date=#{date}")
	public List<MileageVO> getTodayMileage(MileageVO vo);
	
	// 월간 마일리지 조회
	@Select("select sum(mileage) as mileage, date from mileage where id=#{id} GROUP BY date asc")
	public List<MileageVO> getMonthlyMileage(String id);
	
	// 새싹 갯수 조회
	@Select("select count(t.date) as count from (select id, date from mileage where id=#{id} group by date asc having sum(mileage) >= 100) t")
	public int getSproutCount(String id);
	
	// 사용한 새싹 갯수 조회
	@Select("select IFNULL(sum(count), 0) as count from funding_list where id=#{id};")
	public int getUsedSproutCount(String id);
	
	// 모든 기업정보 조회
	@Select("SELECT * FROM funding_corp")
	public List<FundingCorpVO> getCorpInfo();
	
	// 해당 기업의 펀딩금액 조회
	@Select("select sum(count) as count from funding_list where corp=#{corp}")
	public int getFundRaising(String corp);
	
	// 해당 기업의 펀딩금액을 더하기 전 값이 있는지 조회
	@Select("select * from funding_list where id=#{0} and corp=#{1}")
	public List<FundingListVO> getFundingList(String id, String corp);
	
	@Select("select irdnt_nm from recipe_ingredient where recipe_id=#{recipe_id}")
	public List<String> getFoodIngre(String recipe_id);
	
	
	// 해당 기업의 펀딩금액 더하기
	@Update("update funding_list set count = count + #{2} where id=#{0} and corp=#{1}")
	public void updateFundingList(String id, String corp, int count);
	
	// 해당 기업의 펀딩금액 더하기(새로 삽입)
	@Insert("insert into funding_list values(#{0},#{1},#{2}")
	public void insertFundingList(String id, String corp, int count);
	
	
	/*
	@Select("select * from profile")
	public List<ProfileVO> getProfileList();
	*/
	
	// 계정 정보 수정할 때 사용
	@Update("update family set nickname=#{nickname}, email=#{email}, address1=#{address1}, address2=#{address2}, address3=#{address3} where id=#{id}")
	public void updateUser(FamilyVO familyVO);
	
	@Update("update family set pw=#{pw}, salt=#{salt} where id=#{id}")
	public void updateUserpwd(FamilyVO familyVO);
	
	// 프로필 정보 수정할 때 사용
	@Update("update profile set age=#{age}, height=#{height}, gender=#{gender}, phone=#{phone}, allergyinfo=#{allergyinfo}, path=#{path},nation_nm=#{nation_nm}, ty_nm=#{ty_nm}, level_nm=#{level_nm} where username=#{username} and id=#{id}")
	public void updateProfile(ProfileVO profileVO);
	
	// 임시 비밀번호 이메일 발송시 변경된 비밀번호 업데이트에 사용
	@Update("update family set salt=#{salt}, pw=#{pw} where id=#{id} and email=#{email}")
	public void updatePwdFamily(FamilyVO familysVO);
	
	// 계정 회원가입에 쓰임
	@Insert("insert into profile values(#{username},#{age},#{height},#{gender},#{phone},#{allergyinfo},#{id},#{path},#{nation_nm},#{ty_nm},#{level_nm})")
	public void insertProfile(ProfileVO profileVO);
	
	// 프로필 등록에 쓰임
	@Insert("insert into family values(#{id},#{pw},#{salt},#{nickname},#{email},#{address1}, #{address2}, #{address3})")
	public void insertFamily(FamilyVO familyVO);
	
	// 메모 등록에 쓰기
	@Insert("insert into memo(date, memo, id, username) values(#{date}, #{memo}, #{id}, #{username})")
	public void insertMemo(MemoVO MemoVO);
		
	
	// ID삭제시 
	@Delete("delete from memo where id=#{id}")
	public void deleteAllMemo(FamilyVO familyVO);
	
	@Delete("delete from sprout where id=#{id}")
	public void deleteAllSprout(FamilyVO familyVO);
	
	@Delete("delete from funding_list where id=#{id}")
	public void deleteAllfunding_list(FamilyVO familyVO);
	
	@Delete("delete from foodlist where id=#{id}")
	public void deleteAllFoodlist(FamilyVO familyVO);
	
	@Delete("delete from mileage where id=#{id}")
	public void deleteAllMileage(FamilyVO familyVO);
	
	@Delete("delete from profile where id=#{id}")
	public void deleteAllProfile(FamilyVO familyVO);
	
	@Delete("delete from family where id=#{id}")
	public void deleteFamily(FamilyVO familyVO);
	
	
	// 프로필삭제시
	@Delete("delete from memo where username=#{username} and id=#{id}")
    public void deleteProfileMemo(ProfileVO profileVO);
	
	@Delete("delete from profile where username=#{username} and id=#{id}")
	public void deleteProfile(ProfileVO profileVO);
	
	
}