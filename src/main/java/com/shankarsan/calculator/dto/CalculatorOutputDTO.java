/**
 * 
 */
package com.shankarsan.calculator.dto;

/**
 * @author SHANKARSAN
 *
 */
public class CalculatorOutputDTO extends BaseOutputDTO {
	private String result;
	private String errorMessage;

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

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
