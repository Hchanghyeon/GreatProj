package com.great.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.great.domain.FamilyVO;
import com.great.domain.FundingCorpVO;
import com.great.domain.FundingListVO;
import com.great.domain.MemoVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;

public interface MemberService {
	public FamilyVO getFamily(FamilyVO familyVO);
	public List<ProfileVO> getProfileList(String id);
	public String getFamilySalt(String familyID);
	public void insertFamily(FamilyVO familyVO);
	public int getFamilyIdChk(String familyID);
	void insertProfile(ProfileVO profileVO);
	ProfileVO getProfile(ProfileVO vo);
	void updateProfile(ProfileVO profileVO);
	FamilyVO getID(String email);
	FamilyVO getFamilyIdEmailChk(FamilyVO familyID);
	void updatePwdFamily(FamilyVO familyVO);
	public FamilyVO getUserinfo(String id);
	public void updateUser(FamilyVO familyVO);
	public void updateUserpwd(FamilyVO familyVO);
	public FamilyVO getAPIUser(String id);
	public void insertMemo(MemoVO MemoVO);
	public List<MemoVO> getMemoList(String id);
	public List<MileageVO> getTodayMileage(MileageVO vo);
	public List<MileageVO> getMonthlyMileage(String id);
	public int getSproutCount(String id);
	public int getUsedSproutCount(String id);
	public List<FundingCorpVO> getCorpInfo();
	public int getFundRaising(String corp);
	public List<FundingListVO> getFundingList(String id, String corp);
	public void updateFundingList(String id, String corp, int count);
	public void insertFundingList(String id, String corp, int count);
	public String getUserAllergy(ProfileVO profilevo);
	public List<String> getFoodIngre(String recipe_id);
	
	
	// ID삭제시 
	public void deleteAllMemo(FamilyVO familyVO);
	public void deleteAllSprout(FamilyVO familyVO);
	public void deleteAllfunding_list(FamilyVO familyVO);
	public void deleteAllFoodlist(FamilyVO familyVO);
	public void deleteAllMileage(FamilyVO familyVO);
	public void deleteAllProfile(FamilyVO familyVO);
	public void deleteFamily(FamilyVO familyVO);
	
	
	// 프로필삭제시
    public void deleteProfileMemo(ProfileVO profileVO);
	public void deleteProfile(ProfileVO profileVO);
}
