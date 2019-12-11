/**
 * 
 */
package com.shankarsan.calculator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.service.helper.CalculatorServiceHelper;
import com.shankarsan.constants.CommonConstants;
import com.shankarsan.enums.OperatorEnum;
import com.shankarsan.exception.ServiceException;

/**
 * @author SHANKARSAN
 *
 */
@RunWith(value = MockitoJUnitRunner.class)
public class CalculatorServiceHelperTest {
	
	@InjectMocks
	private CalculatorServiceHelper calculatorServiceHelper;
	
	@Test
	public void testPostProcessResult() {
		assertEquals("-100.0", calculatorServiceHelper.postProcessResult("-100.0000"));
		assertEquals(CommonConstants._0, calculatorServiceHelper.postProcessResult("0000"));
		assertEquals("0.0", calculatorServiceHelper.postProcessResult("00.000"));
		assertEquals("0.04", calculatorServiceHelper.postProcessResult(".04"));
		assertEquals("40", calculatorServiceHelper.postProcessResult("0040"));
		assertEquals("0.0", calculatorServiceHelper.postProcessResult("000."));
		
	}
	
	@Test
	public void testProcessOperands1() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("-14.54");
		calculatorInputDTO.setOperand_2("32.443");
		calculatorInputDTO.setOperator(OperatorEnum.PLUS_SIGN.getValue());
		
		assertEquals(3, calculatorServiceHelper.processOperands(calculatorInputDTO, OperatorEnum.PLUS_SIGN));
		assertEquals("14540", calculatorInputDTO.getOperand_1());
		assertEquals("32443", calculatorInputDTO.getOperand_2());
		
	}
	
	@Test
	public void testProcessOperands2() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("-140.54");
		calculatorInputDTO.setOperand_2("32.443");
		calculatorInputDTO.setOperator(OperatorEnum.MINUS_SIGN.getValue());
		
		assertEquals(3, calculatorServiceHelper.processOperands(calculatorInputDTO, OperatorEnum.MINUS_SIGN));
		assertEquals("140540", calculatorInputDTO.getOperand_1());
		assertEquals("032443", calculatorInputDTO.getOperand_2());
		
	}
	
	@Test
	public void testProcessOperands3() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("-140.5443");
		calculatorInputDTO.setOperand_2("32.443");
		calculatorInputDTO.setOperator(OperatorEnum.ASTERISK_SIGN.getValue());
		
		assertEquals(7, calculatorServiceHelper.processOperands(calculatorInputDTO, OperatorEnum.ASTERISK_SIGN));
		assertEquals("1405443", calculatorInputDTO.getOperand_1());
		assertEquals("032443", calculatorInputDTO.getOperand_2());
	}
	
	@Test
	public void testProcessOperands4() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("-140.5443");
		calculatorInputDTO.setOperand_2("3223.443");
		calculatorInputDTO.setOperator(OperatorEnum.FORWARD_SLASH_SIGN.getValue());
		
		assertEquals(0, calculatorServiceHelper.processOperands(calculatorInputDTO, OperatorEnum.FORWARD_SLASH_SIGN));
		assertEquals("01405443", calculatorInputDTO.getOperand_1());
		assertEquals("32234430", calculatorInputDTO.getOperand_2());
	}
	
	@Test
	public void testPopulateOperandArrays() {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("04345");
		calculatorInputDTO.setOperand_2("34333");
		int operand_1[] = new int [calculatorInputDTO.getOperand_1().length()];
		int operand_2[] = new int [calculatorInputDTO.getOperand_2().length()];
		
		calculatorServiceHelper.populateOperandArrays(operand_1, operand_2, calculatorInputDTO);
		assertArrayEquals(operand_1, new int [] {0,4,3,4,5});
		assertArrayEquals(operand_2, new int [] {3,4,3,3,3});
	}

}
