/**
 * 
 */
package com.shankarsan.calculator.controller;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;
import com.shankarsan.calculator.service.CalculatorService;
import com.shankarsan.exception.ServiceException;

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

	@PostMapping(value = "/basic", consumes = MediaType.APPLICATION_JSON)
	public CalculatorOutputDTO calculate(@Valid @RequestBody CalculatorInputDTO calculatorInputDTO, BindingResult bindingResult) throws ServiceException {
		return calculatorService.calculate(calculatorInputDTO, bindingResult);
	}
}
