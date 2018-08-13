/**
 * 
 */
package com.shankarsan.calculator.service;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;

/**
 * @author SHANKARSAN
 *
 */
public interface CalculatorService {
	public CalculatorOutputDTO add(CalculatorInputDTO calculatorInputDTO);

	public CalculatorOutputDTO subtract(CalculatorInputDTO calculatorInputDTO);

	public CalculatorOutputDTO multiply(CalculatorInputDTO calculatorInputDTO);

	public CalculatorOutputDTO divide(CalculatorInputDTO calculatorInputDTO);
}
