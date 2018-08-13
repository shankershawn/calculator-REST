/**
 * 
 */
package com.shankarsan.calculator.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;
import com.shankarsan.calculator.service.CalculatorService;

/**
 * @author SHANKARSAN
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/v1/calculator/")
public class CalculatorController {
	
	@Autowired
	private CalculatorService calculatorService;

	@PostMapping(value = "addition", consumes = MediaType.APPLICATION_JSON)
	public CalculatorOutputDTO add(@RequestBody CalculatorInputDTO calculatorInputDTO) {
		return calculatorService.add(calculatorInputDTO);
	}
	
	@PostMapping(value = "subtraction", consumes = MediaType.APPLICATION_JSON)
	public CalculatorOutputDTO subtract(@RequestBody CalculatorInputDTO calculatorInputDTO) {
		return calculatorService.subtract(calculatorInputDTO);
	}
	
	@PostMapping(value = "multiplication", consumes = MediaType.APPLICATION_JSON)
	public CalculatorOutputDTO multiply(@RequestBody CalculatorInputDTO calculatorInputDTO) {
		return calculatorService.multiply(calculatorInputDTO);
	}
	
	@PostMapping(value = "division", consumes = MediaType.APPLICATION_JSON)
	public CalculatorOutputDTO divide(@RequestBody CalculatorInputDTO calculatorInputDTO) {
		return calculatorService.divide(calculatorInputDTO);
	}
}
