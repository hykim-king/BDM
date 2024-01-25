package com.test.bdm.food.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.nutrient.domain.NutrientVO;

@Controller
@RequestMapping("food")
@SessionAttributes("selectedFoods")
public class FoodController {

//    @ModelAttribute("selectedFoods")
//    public List<NutrientVO> initializeSelectedFoods() {
//        return new ArrayList<>();
//    }
	
	@ModelAttribute("selectedFoods")
    public HashMap<String, String> FoodsCart() {
        return new HashMap<>();
    }

//    @GetMapping("/selectFood/{foodId}")
//    public String selectFood(@PathVariable Long foodId, 
//                             @ModelAttribute("selectedFoods") List<NutrientVO> selectedFoods) {
//        // DB에서 foodId에 해당하는 음식 정보를 가져와서 selectedFoods에 추가
//    	NutrientVO selectedFood = foodService.getFoodById(foodId);
//        selectedFoods.add(selectedFood);
//        return "redirect:/showSelectedFoods";
//    }
	
	@GetMapping("/selectFood.do")
    public String selectFood(@ModelAttribute("selectedFoods") HashMap<String, String> selectedFoods,
    		NutrientVO inVO) {
        // DB에서 foodId에 해당하는 음식 정보를 가져와서 selectedFoods에 추가
//    	NutrientVO selectedFood = foodService.getFoodById(foodId);
		String code = inVO.getCode();
		String name = inVO.getName();
		
        selectedFoods.put(code, name);
        return "redirect:/showSelectedFoods";
    }

//    @GetMapping("/removeFood/{index}")
//    public String removeFood(@PathVariable int index, 
//                             @ModelAttribute("selectedFoods") List<NutrientVO> selectedFoods) {
//        // 선택한 음식 목록에서 index에 해당하는 음식을 제거
//        selectedFoods.remove(index);
//        return "redirect:/showSelectedFoods";
//    }
	
	@GetMapping("/removeFood/{code}")
    public String removeFood(@PathVariable String code, 
                             @ModelAttribute("selectedFoods") HashMap<String, String> selectedFoods) {
        // 선택한 음식 목록에서 index에 해당하는 음식을 제거
        selectedFoods.remove(code);
        return "redirect:/showSelectedFoods";
    }

//    @GetMapping("/showSelectedFoods")
//    public String showSelectedFoods(@ModelAttribute("selectedFoods") List<NutrientVO> selectedFoods,
//                                    Model model) {
//        // 선택한 음식 목록과 해당 음식들의 영양분 합을 계산하여 모델에 추가
//        model.addAttribute("selectedFoods", selectedFoods);
////        model.addAttribute("totalNutrients", calculateTotalNutrients(selectedFoods));
//        return "selectedFoods";
//    }
    
    @GetMapping("/showSelectedFoods")
    public ModelAndView showSelectedFoods(@ModelAttribute("selectedFoods") HashMap<String, String> selectedFoods,
    		ModelAndView modelAndView) {
        // 선택한 음식 목록과 해당 음식들의 영양분 합을 계산하여 모델에 추가
    	modelAndView.addObject("selectedFoods", selectedFoods);
//        model.addAttribute("totalNutrients", calculateTotalNutrients(selectedFoods));
        return modelAndView;
    }

//    private NutrientInfo calculateTotalNutrients(List<NutrientVO> selectedFoods) {
//        // 선택한 음식들의 영양분 합을 계산하는 로직
//        // ...
//    }
}