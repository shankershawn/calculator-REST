/**
 * 
 */
package com.shankarsan.calculator.dto;

/**
 * @author SHANKARSAN
 *
 */
public class CalculatorOutputDTO {
	private String result;

	public CalculatorOutputDTO(double result) {
		if((result * 10) % 10 == 0) { // this means the decimal portion is 0, for example 7.0
			this.result = Integer.toString((int) result);
		}else {
			this.result = Double.toString(result);
		}
		
	}

	public CalculatorOutputDTO() {

	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
