package com.ssafy.live;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorTest {
	private static final Logger log = LoggerFactory.getLogger(CalculatorTest.class);
	private Calculator calc;
	
	@BeforeEach
	public void setup() {
		log.debug("setup 진입 - 개별 테스트를 위한 준비 작업");
		calc = new Calculator();
	}
	
	@BeforeAll
	public static void setupAll() {
		log.debug("setupAll 진입 - 모든 테스트 공통 작업");
	}
	
	@Test
	@DisplayName("두 수를 더한 결과 테스트 : ex: 1 + 2 = 3")
	public void 두_수를_더해주자() {
		int result = calc.add(1, 2);
		Assertions.assertEquals(3, result);
	}
	
	@Test
	public void divideTest() {
		
		ArithmeticException e = Assertions.assertThrows(ArithmeticException.class, ()->{
			calc.divide(1, 0);
		});
		Assertions.assertEquals("/ by zero", e.getMessage());
	}
	
}
