/**
 * 
 */
package com.shankarsan.calculator.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author SHANKARSAN
 *
 */
public class CalculatorInputDTO {
	
	@NotNull @Pattern (regexp = "^(\\-?\\d*\\.?\\d*)$")
	private String operand_1;
	
	@NotNull @Pattern (regexp = "^(\\-?\\d*\\.?\\d*)$")
	private String operand_2;
	
	@NotNull @Size (max = 1) @Pattern (regexp = "^(\\+|\\-|\\*|\\/)$")
	private String operator;
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * @return the operand_1
	 */
	public String getOperand_1() {
		return operand_1;
	}
	/**
	 * @param operand_1 the operand_1 to set
	 */
	public void setOperand_1(String operand_1) {
		this.operand_1 = operand_1;
	}
	/**
	 * @return the operand_2
	 */
	public String getOperand_2() {
		return operand_2;
	}
	/**
	 * @param operand_2 the operand_2 to set
	 */
	public void setOperand_2(String operand_2) {
		this.operand_2 = operand_2;
	}
}
