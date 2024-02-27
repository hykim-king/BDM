package com.test.bdm.food.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodVO {
	private String id;
	private String regDt;
	private String code;
	private String divs;
	private double amount;

	@Override
	public String toString() {
		return "FoodVO [id=" + id + ", regDt=" + regDt + ", code=" + code + ", divs=" + divs + ", amount=" + amount
				+ ", toString()=" + super.toString() + "]";
	}

}
