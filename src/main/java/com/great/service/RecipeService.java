package com.great.service;

import java.util.List;

import com.great.domain.ProfileVO;
import com.great.domain.RecipeCategoriVO;
import com.great.domain.RecipeIngredientVO;
import com.great.domain.RecipeProcessVO;
import com.great.domain.RecipeVO;
import com.great.domain.RecipeimgVO;

public interface RecipeService {
	public List<RecipeVO> getRecipeList();
	public RecipeVO getRecipe(String num);
	public RecipeimgVO getRecipeImg(RecipeVO recipeVO);
	public List<RecipeVO> getwantRecipeList(List<String> nation_nm, List<String> ty_nm, List<String> level_nm);
	public List<RecipeProcessVO> getRecipeProcess(String num);
	public List<RecipeIngredientVO> getRecipeIngredient(String num);
	public List<RecipeVO> getFavoriteRecipeList(List<String> nation_nm, List<String> ty_nm, List<String> level_nm);
	public RecipeCategoriVO getProfileRecipe(ProfileVO ProfileVO);
}
