package com.great.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.great.domain.ProfileVO;
import com.great.domain.RecipeCategoriVO;
import com.great.domain.RecipeIngredientVO;
import com.great.domain.RecipeProcessVO;
import com.great.domain.RecipeVO;
import com.great.domain.RecipeimgVO;
import com.great.mapper.RecipeMapper;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService{
	
	@Autowired
	private RecipeMapper mapper;
	
	@Override
	public List<RecipeVO> getRecipeList() {
		return mapper.getRecipeList();
	}
	
	@Override
	public RecipeVO getRecipe(String num) {
		return mapper.getRecipe(num);
	}
	
	@Override
	public RecipeimgVO getRecipeImg(RecipeVO recipeVO) {
		return mapper.getRecipeImg(recipeVO);
	}
	
	@Override
	public List<RecipeVO> getwantRecipeList(List<String> nation_nm, List<String> ty_nm, List<String> level_nm){
		return mapper.getwantRecipeList(nation_nm, ty_nm, level_nm);
	}
	
	@Override 
	public List<RecipeProcessVO> getRecipeProcess(String num) {
		return mapper.getRecipeProcess(num);
	}
	
	@Override
	public List<RecipeIngredientVO> getRecipeIngredient(String num) {
		return mapper.getRecipeIngredient(num);
	}
	
	@Override
	public List<RecipeVO> getFavoriteRecipeList(List<String> nation_nm, List<String> ty_nm, List<String> level_nm){
		return mapper.getFavoriteRecipeList(nation_nm, ty_nm, level_nm);
	}
	
	@Override
	public RecipeCategoriVO getProfileRecipe(ProfileVO ProfileVO) {
		return mapper.getProfileRecipe(ProfileVO);
	}
}
