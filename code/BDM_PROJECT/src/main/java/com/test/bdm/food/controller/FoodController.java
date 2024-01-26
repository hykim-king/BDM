package com.test.bdm.food.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.test.bdm.food.service.FoodService;
import com.test.bdm.nutrient.domain.NutrientVO;

@Controller
@RequestMapping("food")
@SessionAttributes({"selectedFoodList", "selectedCodeList"})
public class FoodController {
	
	@Autowired
	FoodService foodService;

    @ModelAttribute("selectedFoodList")
    public List<String> initializeselectedFoodList() {
        return new ArrayList<>();
    }
    
    @ModelAttribute("selectedCodeList")
    public List<String> initializeselectedCodeList() {
        return new ArrayList<>();
    }
    
    @PostMapping(value = "/doSaveFood.do", produces = "application/json;charset=UTF-8")
    public String doSaveFood(@ModelAttribute("selectedFoodCode") List<String> selectedFoodCode,
    		NutrientVO inVO) {
    	String jsonString = "";
    	
    	int flag = foodService.doSaveFood(selectedFoodCode);
    	
    	return jsonString;
    }
	
	@GetMapping("/doSelectFood.do")
    public String doSelectFood(@ModelAttribute("selectedFoodList") List<String> selectedFoodList,
    		@ModelAttribute("selectedCodeList") List<String> selectedCodeList,
    		NutrientVO inVO) {
		String code = inVO.getCode();
		String name = inVO.getName();
		
		selectedCodeList.add(code);
        selectedFoodList.add(name);
        
        return "nutrient/nutrient";
    }

//    @GetMapping("/removeFood/{index}")
//    public String removeFood(@PathVariable int index, 
//                             @ModelAttribute("selectedFoods") List<NutrientVO> selectedFoods) {
//        // 선택한 음식 목록에서 index에 해당하는 음식을 제거
//        selectedFoods.remove(index);
//        return "redirect:/showSelectedFoods";
//    }
	
//	@GetMapping("/removeFood/{code}")
//    public String removeFood(@PathVariable String code, 
//                             @ModelAttribute("selectedFoods") List<String> selectedFoods) {
//        // 선택한 음식 목록에서 index에 해당하는 음식을 제거
//        selectedFoods.remove(code);
//        return "redirect:/showSelectedFoods";
//    }
    
    @GetMapping("/showSelectedFoods")
    public ModelAndView showSelectedFoods(@ModelAttribute("selectedFoodList") List<String> selectedFoodList,
    		@ModelAttribute("selectedCodeList") List<String> selectedCodeList,
    		ModelAndView modelAndView) {
        // 선택한 음식 목록과 해당 음식들의 영양분 합을 계산하여 모델에 추가
    	modelAndView.addObject("selectedCodeList", selectedCodeList);
    	modelAndView.addObject("selectedFoodList", selectedFoodList);
//        model.addAttribute("totalNutrients", calculateTotalNutrients(selectedFoods));
        return modelAndView;
    }
    
    @GetMapping("/doCancle.do")
    public String doCancle(SessionStatus sessionStatus) {
        // 현재 세션 완료 상태로 변경
        sessionStatus.setComplete();
        
        return "user/mypage";
    }

//    private NutrientInfo calculateTotalNutrients(List<NutrientVO> selectedFoods) {
//        // 선택한 음식들의 영양분 합을 계산하는 로직
//        // ...
//    }
}