package com.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.great.domain.FamilyVO;
import com.great.domain.FoodInfoVO;
import com.great.domain.FoodListVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;
import com.great.domain.SearchVO;

public interface FoodListMapper {
	
	@Select("select * from foodlist where id=#{id}")
	public List<FoodListVO> getFoodList(FamilyVO familyVO);
	
	@Select("select * from profile where id=#{id}")
	public List<ProfileVO> getAllergyList(String id);
	
	@Select("select * from foodlist where id=#{id} and cls=#{text}")
	public List<FoodListVO> getSearchFoodList(SearchVO searchVO);
	
	@Insert("insert into foodlist(LTX, LTY, RDX, RDY, X, Y, cls, img, id, inDate, endDate) values(#{LTX}, #{LTY}, #{RDX}, #{RDY}, #{X}, #{Y}, #{cls}, #{img}, #{id}, #{inDate}, #{endDate})")
	public void insert_foodlist(FoodListVO vo);
	
	@Select("select * from foodinfo where foodname=#{foodname}")
	public FoodInfoVO getfoodexp(String foodname);
	
	@Update("update foodlist set cls=#{cls}, inDate=#{inDate}, endDate=#{endDate} where num=#{num}")
	public void updatefood(FoodListVO foodListVO);
	
	@Delete("delete from foodlist where num=#{num}")
	public void deletefood(FoodListVO foodlistVO);
	
	@Select("select * from mileage where id=#{id} and date=#{date} and time=#{time}")
	public MileageVO getMileage(MileageVO mileageVO);
	
	@Insert("insert into mileage(id, mileage, date, time) values(#{id}, #{mileage}, #{date}, #{time})")
	public void insert_mileage(MileageVO mileageVO);
	
	@Update("update mileage set mileage=#{mileage} where id=#{id} and date=#{date} and time=#{time}")
	public void updateMileage(MileageVO mileageVO);
}
