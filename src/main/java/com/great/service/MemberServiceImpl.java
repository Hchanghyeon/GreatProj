package com.great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.domain.FamilyVO;
import com.great.domain.FundingCorpVO;
import com.great.domain.FundingListVO;
import com.great.domain.MemoVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;
import com.great.mapper.MemberMapper;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public String getFamilySalt(String familyID){
		return mapper.getFamilySalt(familyID);
	}
	
	@Override
	public FamilyVO getFamily(FamilyVO familyVO){
		return mapper.getFamily(familyVO);
	}
	
	@Override
	public List<ProfileVO> getProfileList(String id){
		return mapper.getProfileList(id);
	}
	
	@Override
	public ProfileVO getProfile(ProfileVO vo){
		return mapper.getProfile(vo);
	}
	
	@Override
	public void insertFamily(FamilyVO familyVO) {
		mapper.insertFamily(familyVO);
	}
	
	@Override
	public int getFamilyIdChk(String familyID) {
		return mapper.getFamilyIdChk(familyID);
	}
	
	@Override
	public void insertProfile(ProfileVO profileVO) {
		mapper.insertProfile(profileVO);
	}
	
	@Override
	public void updateProfile(ProfileVO profileVO) {
		mapper.updateProfile(profileVO);
	}
	
	@Override
	public FamilyVO getID(String email) {
		return mapper.getID(email);
	}
	
	@Override
	public FamilyVO getFamilyIdEmailChk(FamilyVO familyVO) {
		return mapper.getFamilyIdEmailChk(familyVO);
	}
	
	@Override
	public void updatePwdFamily(FamilyVO familyVO) {
		mapper.updatePwdFamily(familyVO);
	}
	
	@Override
	public FamilyVO getUserinfo(String id) {
		return mapper.getUserinfo(id);
	}
	
	@Override
	public void updateUser(FamilyVO familyVO) {
		mapper.updateUser(familyVO);
	}
	
	@Override
	public void updateUserpwd(FamilyVO familyVO) {
		mapper.updateUserpwd(familyVO);
	}
	
	@Override
	public FamilyVO getAPIUser(String id) {
		return mapper.getAPIUser(id);
	}
	
	@Override
	public void insertMemo(MemoVO MemoVO) {
		mapper.insertMemo(MemoVO);
	}
	
	@Override
	public List<MemoVO> getMemoList(String id){
		return mapper.getMemoList(id);
	}
	
	@Override
	public List<MileageVO> getTodayMileage(MileageVO vo){
		return mapper.getTodayMileage(vo);
	}
	
	@Override
	public List<MileageVO> getMonthlyMileage(String id){
		return mapper.getMonthlyMileage(id);
	}
	
	@Override
	public int getSproutCount(String id)
	{
		return mapper.getSproutCount(id);
	}
	
	@Override
	public int getUsedSproutCount(String id)
	{
		return mapper.getUsedSproutCount(id);
	}
	
	@Override
	public List<FundingCorpVO> getCorpInfo()
	{
		return mapper.getCorpInfo();
	}
	
	@Override
	public int getFundRaising(String corp)
	{
		return mapper.getFundRaising(corp);
	}
	
	@Override
	public List<FundingListVO> getFundingList(String id, String corp)
	{
		return mapper.getFundingList(id, corp);
	}
	
	@Override
	public void updateFundingList(String id, String corp, int count)
	{
		mapper.updateFundingList(id, corp, count);
	}
	
	@Override
	public void insertFundingList(String id, String corp, int count)
	{
		mapper.insertFundingList(id, corp, count);
	}
	
	@Override
	public void deleteAllMemo(FamilyVO familyVO) {
		mapper.deleteAllMemo(familyVO);
	}
	
	@Override
	public void deleteAllSprout(FamilyVO familyVO) {
		mapper.deleteAllSprout(familyVO);
	}
	@Override
	public void deleteAllfunding_list(FamilyVO familyVO) {
		mapper.deleteAllfunding_list(familyVO);
	}
	@Override
	public void deleteAllFoodlist(FamilyVO familyVO) {
		mapper.deleteAllFoodlist(familyVO);
	}
	@Override
	public void deleteAllMileage(FamilyVO familyVO) {
		mapper.deleteAllMileage(familyVO);
	}
	@Override
	public void deleteAllProfile(FamilyVO familyVO) {
		mapper.deleteAllProfile(familyVO);
	}
	@Override
	public void deleteFamily(FamilyVO familyVO) {
		mapper.deleteFamily(familyVO);
	}
	
	@Override 
	public String getUserAllergy(ProfileVO profilevo) {
		return mapper.getUserAllergy(profilevo);
	}
	
	@Override
	public List<String> getFoodIngre(String recipe_id){
		return mapper.getFoodIngre(recipe_id);
	}
	
	// 프로필삭제시
	@Override
    public void deleteProfileMemo(ProfileVO profileVO) {
		mapper.deleteProfileMemo(profileVO);
	}
    @Override
    public void deleteProfile(ProfileVO profileVO) {
    	mapper.deleteProfile(profileVO);
    }
}
