package com.great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.domain.FamilyVO;
import com.great.domain.FoodInfoVO;
import com.great.domain.FoodListVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;
import com.great.domain.SearchVO;
import com.great.mapper.FoodListMapper;

@Service("foodListService")
public class FoodListServiceImpl implements FoodListService{
	
	@Autowired
	private FoodListMapper mapper;

	@Override
	public List<FoodListVO> getFoodList(FamilyVO familyVO) {
		return mapper.getFoodList(familyVO);
	}
	
	@Override
	public List<ProfileVO> getAllergyList(String id) {
		return mapper.getAllergyList(id);
	}
	
	@Override
	public void insert_foodlist(FoodListVO vo) {
		mapper.insert_foodlist(vo);
	}
	
	@Override
	public FoodInfoVO getfoodexp(String foodname){
		return mapper.getfoodexp(foodname);
	}
	
	@Override
	public void updatefood(FoodListVO foodListVO) {
		mapper.updatefood(foodListVO);
	}
	
	@Override
	public void deletefood(FoodListVO foodlistVO) {
		mapper.deletefood(foodlistVO);
	}
	
	@Override
	public MileageVO getMileage(MileageVO mileageVO) {
		return mapper.getMileage(mileageVO);
	}
	
	@Override
	public void insert_mileage(MileageVO mileageVO) {
		mapper.insert_mileage(mileageVO);
	}
	
	@Override
	public void updateMileage(MileageVO mileageVO) {
		mapper.updateMileage(mileageVO);
	}
	
	@Override
	public List<FoodListVO> getSearchFoodList(SearchVO searchVO){
		return mapper.getSearchFoodList(searchVO);
	}
}