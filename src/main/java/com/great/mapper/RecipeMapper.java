package com.great.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.great.domain.ProfileVO;
import com.great.domain.RecipeCategoriVO;
import com.great.domain.RecipeIngredientVO;
import com.great.domain.RecipeProcessVO;
import com.great.domain.RecipeVO;
import com.great.domain.RecipeimgVO;


public interface RecipeMapper {
	
	@Select("select * from recipe_basic")
	public List<RecipeVO> getRecipeList();
	
	@Select("select * from recipe_basic where recipe_id=#{recipe_id}")
	public RecipeVO getRecipe(String num);
	
	@Select("select * from recipe_ingredient where recipe_id=#{recipe_id}")
	public List<RecipeIngredientVO> getRecipeIngredient(String num);
	
	@Select("select * from recipe_process where recipe_id=#{recipe_id}")
	public List<RecipeProcessVO> getRecipeProcess(String num);
	
	@Select("select * from recipeimg where foodname=#{foodname}")
	public RecipeimgVO getRecipeImg(RecipeVO recipeVO);
	
	@Select("select nation_nm, ty_nm, level_nm from profile where username=#{username}")
	public RecipeCategoriVO getProfileRecipe(ProfileVO ProfileVO);
	
	public List<RecipeVO> getwantRecipeList(@Param("nation_nm") List<String> nation_nm, @Param("ty_nm") List<String> ty_nm, @Param("level_nm") List<String> level_nm);
	
	public List<RecipeVO> getFavoriteRecipeList(@Param("nation") List<String> nation_nm, @Param("ty") List<String> ty_nm, @Param("level") List<String> level_nm);
}                        
