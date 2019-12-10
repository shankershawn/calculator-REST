/**
 * 
 */
package com.shankarsan.calculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;
import com.shankarsan.calculator.service.CalculatorServiceImpl;
import com.shankarsan.calculator.service.helper.CalculatorServiceHelper;
import com.shankarsan.enums.OperatorEnum;
import com.shankarsan.exception.ServiceException;

/**
 * @author SHANKARSAN
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {
	
	@InjectMocks
	private CalculatorServiceImpl calculatorService;
	
	@Mock
	private CalculatorServiceHelper calculatorServiceHelper;
	
	@Test
	public void testCalculatorAdd1() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("21.1");
		calculatorInputDTO.setOperator("+");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(1);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("53.1");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("53.1", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorAdd2() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("-21");
		calculatorInputDTO.setOperator("+");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("11");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("11", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorAdd3() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("-32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("+");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("-11");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("-11", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorAdd4() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("-32");
		calculatorInputDTO.setOperand_2("-21");
		calculatorInputDTO.setOperator("+");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("53");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("-53", calculatorOutputDTO.getResult());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ServiceException.class)
	public void testCalculatorAdd5() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("+");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenThrow(ServiceException.class);
		try {
			calculatorService.calculate(calculatorInputDTO, bindingResult);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	@Test
	public void testCalculatorSubtract1() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("-");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("11");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("11", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorSubtract2() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("-32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("-");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("53");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("-53", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorSubtract3() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("-32");
		calculatorInputDTO.setOperand_2("-21");
		calculatorInputDTO.setOperator("-");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 2;
			((int [])args[0])[1] = 1;
			((int [])args[1])[0] = 3;
			((int [])args[1])[1] = 2;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("-11");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("-11", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorSubtract4() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("-21");
		calculatorInputDTO.setOperator("-");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("53");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("53", calculatorOutputDTO.getResult());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ServiceException.class)
	public void testCalculatorSubtract5() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("-");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenThrow(ServiceException.class);
		try {
			calculatorService.calculate(calculatorInputDTO, bindingResult);
		} catch (ServiceException e) {
			throw e;
		}
	}
	
	@Test
	public void testCalculatorMultiply1() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("*");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("672");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("672", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorMultiply2() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("-32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("*");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("672");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("-672", calculatorOutputDTO.getResult());
	}
	
	@Test
	public void testCalculatorMultiply3() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		CalculatorOutputDTO calculatorOutputDTO = null;
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("-21");
		calculatorInputDTO.setOperator("*");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenReturn(0);
		doAnswer(invocation -> {
			Object[] args = invocation.getArguments();
			((int [])args[0])[0] = 3;
			((int [])args[0])[1] = 2;
			((int [])args[1])[0] = 2;
			((int [])args[1])[1] = 1;
			return null;
		}).when(calculatorServiceHelper).populateOperandArrays(any(int [].class), any(int [].class), any(CalculatorInputDTO.class));
		when(calculatorServiceHelper.postProcessResult(anyString())).thenReturn("672");
		calculatorOutputDTO = calculatorService.calculate(calculatorInputDTO, bindingResult);
		assertEquals("-672", calculatorOutputDTO.getResult());
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = ServiceException.class)
	public void testCalculatorMultiply4() throws ServiceException {
		CalculatorInputDTO calculatorInputDTO = new CalculatorInputDTO();
		calculatorInputDTO.setOperand_1("32");
		calculatorInputDTO.setOperand_2("21");
		calculatorInputDTO.setOperator("*");
		BindingResult bindingResult = mock(BindingResult.class);
		when(calculatorServiceHelper.processOperands(any(CalculatorInputDTO.class), any(OperatorEnum.class))).thenThrow(ServiceException.class);
		try {
			calculatorService.calculate(calculatorInputDTO, bindingResult);
		} catch (ServiceException e) {
			throw e;
		}
	}

}
