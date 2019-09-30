/**
 * 
 */
package com.shankarsan.calculator.service.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.constants.CommonConstants;
import com.shankarsan.exception.ServiceException;

/**
 * @author SHANKARSAN
 *
 */
@Component
public class CalculatorServiceHelper {

	/**
	 * @param result
	 * @return
	 */
	public String postProcessResult(String result) {
		boolean isNegative;
		isNegative = result.contains(CommonConstants.HYPHEN);
		if(StringUtils.containsOnly(result, CommonConstants._0)) {
			result = CommonConstants._0;
		}else if(StringUtils.containsOnly(result, CommonConstants._0.toCharArray()[0], CommonConstants.DOT.toCharArray()[0])) {
			result = new StringBuilder(CommonConstants._0).append(CommonConstants.DOT).append(CommonConstants._0).toString();
		}else if(StringUtils.contains(result, CommonConstants.DOT)) {
			result = result.replaceAll("(^\\-?0*)|(0*$)", CommonConstants.EMPTY);
		}else {
			result = result.replaceAll("(^\\-?0*)", CommonConstants.EMPTY);
		}
		if(result.startsWith(CommonConstants.DOT)) {
			result = CommonConstants._0 + result;
		}
		if(result.endsWith(CommonConstants.DOT)) {
			result  = result + CommonConstants._0;
		}
		if(isNegative && !result.contains(CommonConstants.HYPHEN)) {
			result = CommonConstants.HYPHEN + result;
		}
		return result;
	}
	
	/**
	 * @param calculatorInputDTO
	 * @return
	 * @throws ServiceException
	 */
	public int processOperands(CalculatorInputDTO calculatorInputDTO) throws ServiceException {
		int operand1DecimalPartLength = 0, operand2DecimalPartLength = 0, maxOperandDecimalPartLength = 0, operand1WholeNumberLength = 0, operand2WholeNumberLength = 0;
		StringBuilder operandBuilder;
		String[] splitArray = null;
		
		calculatorInputDTO.setOperand_1(StringUtils.replace(calculatorInputDTO.getOperand_1(), CommonConstants.MINUS, CommonConstants.EMPTY));
		calculatorInputDTO.setOperand_2(StringUtils.replace(calculatorInputDTO.getOperand_2(), CommonConstants.MINUS, CommonConstants.EMPTY));
		splitArray = calculatorInputDTO.getOperand_1().split(CommonConstants.DOT_REGEX);
		operand1WholeNumberLength = splitArray[0].length();
		if(splitArray.length > 1) {
			operand1DecimalPartLength = splitArray[1].length();
		}
		splitArray = calculatorInputDTO.getOperand_2().split(CommonConstants.DOT_REGEX);
		operand2WholeNumberLength = splitArray[0].length();
		if(splitArray.length > 1) {
			operand2DecimalPartLength = splitArray[1].length();
		}
		maxOperandDecimalPartLength = Math.max(operand1DecimalPartLength,operand2DecimalPartLength);
		
		if(operand1DecimalPartLength - operand2DecimalPartLength > 0) {
			operandBuilder = new StringBuilder(calculatorInputDTO.getOperand_2());
			for(int index = 0; index < (operand1DecimalPartLength - operand2DecimalPartLength); index++) {
				operandBuilder.append(CommonConstants._0);
			}
			calculatorInputDTO.setOperand_2(operandBuilder.toString());
		}else if(operand2DecimalPartLength - operand1DecimalPartLength > 0) {
			operandBuilder = new StringBuilder(calculatorInputDTO.getOperand_1());
			for(int index = 0; index < (operand2DecimalPartLength - operand1DecimalPartLength); index++) {
				operandBuilder.append(CommonConstants._0);
			}
			calculatorInputDTO.setOperand_1(operandBuilder.toString());
		}
		
		if(operand1WholeNumberLength - operand2WholeNumberLength > 0) {
			operandBuilder = new StringBuilder(calculatorInputDTO.getOperand_2());
			for(int index = 0; index < (operand1WholeNumberLength - operand2WholeNumberLength); index++) {
				operandBuilder.insert(0, CommonConstants._0);
			}
			calculatorInputDTO.setOperand_2(operandBuilder.toString());
		}else if(operand2WholeNumberLength - operand1WholeNumberLength > 0) {
			operandBuilder = new StringBuilder(calculatorInputDTO.getOperand_1());
			for(int index = 0; index < (operand2WholeNumberLength - operand1WholeNumberLength); index++) {
				operandBuilder.insert(0, CommonConstants._0);
			}
			calculatorInputDTO.setOperand_1(operandBuilder.toString());
		}
		
		calculatorInputDTO.setOperand_1(StringUtils.replace(calculatorInputDTO.getOperand_1(), CommonConstants.DOT, CommonConstants.EMPTY));
		calculatorInputDTO.setOperand_2(StringUtils.replace(calculatorInputDTO.getOperand_2(), CommonConstants.DOT, CommonConstants.EMPTY));
		
		return maxOperandDecimalPartLength;
	}
	
	/**
	 * @param operand1Array
	 * @param operand2Array
	 * @param calculatorInputDTO
	 */
	public void populateOperandArrays(int operand1Array[], int operand2Array[], CalculatorInputDTO calculatorInputDTO) {
		int index = 0;
		for(index = 0; index < calculatorInputDTO.getOperand_1().length(); index++) {
			operand1Array[index] = Integer.valueOf(String.valueOf(calculatorInputDTO.getOperand_1().charAt(index)));
		}
		for(index = 0; index < calculatorInputDTO.getOperand_2().length(); index++) {
			operand2Array[index] = Integer.valueOf(String.valueOf(calculatorInputDTO.getOperand_2().charAt(index)));
		}
	}
}
