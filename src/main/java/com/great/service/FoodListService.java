package com.great.service;

import java.util.List;

import com.great.domain.FamilyVO;
import com.great.domain.FoodInfoVO;
import com.great.domain.FoodListVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;
import com.great.domain.SearchVO;

public interface FoodListService {
	public List<FoodListVO> getFoodList(FamilyVO familyVO);
	public List<ProfileVO> getAllergyList(String id);
	public void insert_foodlist(FoodListVO vo);
	public FoodInfoVO getfoodexp(String foodname);
	public void updatefood(FoodListVO foodListVO);
	public void deletefood(FoodListVO foodlistVO);
	public MileageVO getMileage(MileageVO mileageVO);
	public void insert_mileage(MileageVO mileageVO);
	public void updateMileage(MileageVO mileageVO);
	public List<FoodListVO> getSearchFoodList(SearchVO searchVO);
}
