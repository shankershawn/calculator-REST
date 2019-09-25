/**
 * 
 */
package com.shankarsan.enums;

/**
 * @author SHANKARSAN
 *
 */
public enum OperatorEnum {
	
	PLUS_SIGN("+"),
	MINUS_SIGN("-"),
	ASTERISK_SIGN("*"),
	FORWARD_SLASH_SIGN("/");
	
	private String value;
	
	private OperatorEnum(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
