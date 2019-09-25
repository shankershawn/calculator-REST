/**
 * 
 */
package com.shankarsan.calculator.service;

import org.springframework.validation.BindingResult;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;
import com.shankarsan.exception.ServiceException;

/**
 * @author SHANKARSAN
 *
 */
public interface CalculatorService {

	public CalculatorOutputDTO calculate(CalculatorInputDTO calculatorInputDTO, BindingResult bindingResult) throws ServiceException;

}
