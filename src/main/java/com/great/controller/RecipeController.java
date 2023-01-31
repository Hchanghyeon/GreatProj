package com.great.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.great.domain.AllergyVO;
import com.great.domain.ProfileVO;
import com.great.domain.RecipeCategoriVO;
import com.great.domain.RecipeIngredientVO;
import com.great.domain.RecipeProcessVO;
import com.great.domain.RecipeVO;
import com.great.service.FoodListService;
import com.great.service.MemberService;
import com.great.service.RecipeService;

@Controller
public class RecipeController {
    @Autowired
    private RecipeService recipeservice;
    @Autowired
    private MemberService memberservice;
	
	
	@GetMapping("/recipemain")
	public String getsmartrecipemain(Model model, HttpSession session) {
		 
		ProfileVO profile = (ProfileVO)session.getAttribute("profile");
							
		RecipeCategoriVO categori = recipeservice.getProfileRecipe(profile);
		
		List<String> nation_list = new ArrayList<String>();
		String[] temp_nation = categori.getNation_nm().split(",");

		List<String> ty_list = new ArrayList<String>();
		String[] temp_ty = categori.getTy_nm().split(",");
		
		List<String> level_list = new ArrayList<String>();
		String[] temp_level = categori.getLevel_nm().split(",");
		
		
		for (String str : temp_nation) {
			nation_list.add(str);
		}
		
		for (String str : temp_ty) {
			ty_list.add(str);
		}
		
		for (String str : temp_level) {
			level_list.add(str);
		}
		
		
		List<RecipeVO> recipeList = new ArrayList<RecipeVO>();
		List<RecipeVO> finalrecipeList = new ArrayList<RecipeVO>();
		
		recipeList = recipeservice.getFavoriteRecipeList(nation_list, ty_list, level_list);
		
		String allergy = memberservice.getUserAllergy(profile);
		
    	ArrayList<String> allergyList = new ArrayList<String>();
    	
		String[] strList = allergy.split(":");
		
		System.out.println("b : "+allergy);
		
		if(allergy.equals("")) {
			model.addAttribute("recipeList",recipeList);
		} else {
			for (String str : strList) {
				allergyList.add(str);
			}
			

			for(RecipeVO recipe : recipeList) {
				int num = 0;
				List<String> ingrelist = new ArrayList<String>();
				ingrelist = memberservice.getFoodIngre(recipe.getRecipe_id());
				for(String ingre : ingrelist) {
					
					for(String all :  allergyList) {
						System.out.println(ingre + " = " + all);
						if(ingre.contains(all)) {
							num = 1;
						} 
					}
				}
				if(num == 0) {
					finalrecipeList.add(recipe);
				}
			}
				
		
			model.addAttribute("recipeList",finalrecipeList);
		}
		
	  	return "recipe";
	}
	
	@PostMapping("/recipe/getRecipe")
	@ResponseBody
	public Object getRecipe(@RequestParam(value="ty_nm[]") List<String> ty_nm, 
            @RequestParam(value="nation_nm[]") List<String> nation_nm, 
            @RequestParam(value="level_nm[]") List<String> level_nm, Model model) throws ParseException {
		
		
		List<RecipeVO> recipeList = recipeservice.getwantRecipeList(nation_nm, ty_nm, level_nm);
		
		System.out.println(recipeList);
		
		Gson gson = new Gson();
		JSONParser jsonParser = new JSONParser();
		Object jarry = new JSONArray();

		jarry = jsonParser.parse(gson.toJson(recipeList));

	
	  	return jarry;
	}
	  
	  
	  @GetMapping("/recipedetail")
	  public String getrecipeinfo(@RequestParam(value="no") String num, Model model) {
		  RecipeVO recipevo = recipeservice.getRecipe(num);
		  model.addAttribute("recipe_basic", recipevo);
		  List<RecipeIngredientVO> recipeingre = recipeservice.getRecipeIngredient(num);
		  model.addAttribute("recipe_ingre", recipeingre);
		  List<RecipeProcessVO> recipepro = recipeservice.getRecipeProcess(num);
		  model.addAttribute("recipe_pro", recipepro);
		  
		  return "recipeinfo";
	  }
	
}
