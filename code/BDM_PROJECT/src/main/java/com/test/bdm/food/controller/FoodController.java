package com.test.bdm.food.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.bdm.cmn.MessageVO;
import com.test.bdm.cmn.PcwkLogger;
import com.test.bdm.food.domain.FoodVO;
import com.test.bdm.food.service.FoodService;
import com.test.bdm.nutrient.domain.NutrientVO;
import com.test.bdm.user.domain.UserVO;

@Controller
@RequestMapping("food")
@SessionAttributes({"selectedFoodList", "selectedCodeList", "amountList"})
public class FoodController implements PcwkLogger {
	
	@Autowired
	FoodService foodService;
	
	@ModelAttribute("amountList")
    public List<Double> initializeamountList() {
        return new ArrayList<>();
    }

    @ModelAttribute("selectedFoodList")
    public List<String> initializeselectedFoodList() {
        return new ArrayList<>();
    }
    
    @ModelAttribute("selectedCodeList")
    public List<String> initializeselectedCodeList() {
        return new ArrayList<>();
    }
    
    @PostMapping(value = "/doSaveFood.do", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String doSaveFood(HttpSession session, FoodVO foodVO) {
    	String jsonString = "";
    	
    	UserVO sessionData = (UserVO) session.getAttribute("user");
    	
    	List<String> sessionData2 = (List<String>) session.getAttribute("selectedCodeList");
    	List<Double> sessionData3 = (List<Double>) session.getAttribute("amountList");
    	
    	String userId = sessionData.getId();
    	String divs = foodVO.getDivs();
//    	foodVO.setId(sessionData.getId());
//    	foodVO.setCode(code);
    	
    	int flag = foodService.doSaveFood(userId, sessionData2, sessionData3, divs);
    	
    	String message = "";

		if (flag == 1)
			message = "정상적으로 반영되었습니다";
		else
			message = "반영 실패";

		MessageVO messageVO = new MessageVO(flag + "", message);
		jsonString = new Gson().toJson(messageVO);
		LOG.debug("jsonString: " + jsonString);
    	
    	return jsonString;
    }
    
    @GetMapping("/doSelectedDelete.do")
    public String doSelectedDelete(@ModelAttribute("selectedFoodList") List<String> selectedFoodList,
    		@ModelAttribute("selectedCodeList") List<String> selectedCodeList,
    		@ModelAttribute("amountList") List<Double> amountList,
    		@RequestParam(value = "index") List<Integer> index) {
    	
//    	List<Integer> indexes = new ArrayList<>();
    	List<String> foodNames = new ArrayList<>();
    	List<String> foodCodes = new ArrayList<>();
    	List<Double> amounts = new ArrayList<Double>();
    	
    	for(int i=0; i<index.size(); i++) {
    		String food = selectedFoodList.get(index.get(i));
    		foodNames.add(food);
    		String code = selectedCodeList.get(i);
    		foodCodes.add(code);
    		double amount = amountList.get(i);
    		amounts.add(amount);
    	}
    	
    	for(int i=0; i<index.size(); i++) {
    		selectedFoodList.remove(foodNames.get(i));
    		selectedCodeList.remove(foodCodes.get(i));
    		amountList.remove(amounts.get(i));
    	}
    	
    	return "nutrient/nutrient";
    }
	
	@GetMapping("/doSelectFood.do")
    public String doSelectFood(@ModelAttribute("selectedFoodList") List<String> selectedFoodList,
    		@ModelAttribute("selectedCodeList") List<String> selectedCodeList,
    		@ModelAttribute("amountList") List<Double> amountList,
    		NutrientVO inVO, FoodVO foodVO) {
		String code = inVO.getCode();
		String name = inVO.getName();
		double amount = foodVO.getAmount();
		
		selectedCodeList.add(code);
        selectedFoodList.add(name);
        amountList.add(amount);
        
        return "nutrient/nutrient";
    }
    
    @GetMapping("/doShowSelectedFoods.do")
    public ModelAndView doShowSelectedFoods(@ModelAttribute("selectedFoodList") List<String> selectedFoodList,
    		@ModelAttribute("selectedCodeList") List<String> selectedCodeList,
    		@ModelAttribute("amountList") List<Double> amountList,
    		ModelAndView modelAndView) {
        // 선택한 음식 목록과 해당 음식들의 영양분 합을 계산하여 모델에 추가
    	modelAndView.addObject("selectedCodeList", selectedCodeList);
    	modelAndView.addObject("selectedFoodList", selectedFoodList);
    	modelAndView.addObject("amountList", amountList);
        return modelAndView;
    }
    
    @GetMapping("/doCancle.do")
    public String doCancle(SessionStatus sessionStatus) {
        // 현재 세션 완료 상태로 변경
        sessionStatus.setComplete();
        
        return "user/mypage";
    }
}