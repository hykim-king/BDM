package com.test.bdm.nutrient.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EatVO {
	private String id;
	private String name;
	private String regDt;
	private String code;
	private String divs;
	private double amount;

	@Override
	public String toString() {
		return "EatVO [id=" + id + ", name=" + name + ", regDt=" + regDt + ", code=" + code + ", divs=" + divs
				+ ", amount=" + amount + ", toString()=" + super.toString() + "]";
	}

}
