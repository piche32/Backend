package com.ssafy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@RequiredArgsConstructor
@Slf4j
public class Car {
	@NonNull
	String name;
	@NonNull
	int price;
	boolean start;
	private String brand;
}
