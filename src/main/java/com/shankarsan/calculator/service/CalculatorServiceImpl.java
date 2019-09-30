/**
 * 
 */
package com.shankarsan.calculator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;
import com.shankarsan.calculator.service.helper.CalculatorServiceHelper;
import com.shankarsan.constants.CommonConstants;
import com.shankarsan.enums.OperatorEnum;
import com.shankarsan.exception.ServiceException;

/**
 * @author SHANKARSAN
 *
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {
	
	private static final Logger _logger = LoggerFactory.getLogger(CalculatorServiceImpl.class);
	
	@Autowired
	private CalculatorServiceHelper calculatorServiceHelper;

	/**
	 * @param calculatorInputDTO
	 * @return
	 * @throws ServiceException 
	 */
	private CalculatorOutputDTO add(CalculatorInputDTO calculatorInputDTO) throws ServiceException {
		_logger.info("Entering add method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		CalculatorInputDTO calculatorInputDTOForTransformedAdd = null;
		try {
			if(StringUtils.containsAny(calculatorInputDTO.getOperand_1(), CommonConstants.HYPHEN) && !StringUtils.containsAny(calculatorInputDTO.getOperand_2(), CommonConstants.HYPHEN)) {
				calculatorInputDTOForTransformedAdd = new CalculatorInputDTO();
				calculatorInputDTOForTransformedAdd.setOperand_1(calculatorInputDTO.getOperand_2());
				calculatorInputDTOForTransformedAdd.setOperand_2(calculatorInputDTO.getOperand_1().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorInputDTOForTransformedAdd.setOperator(OperatorEnum.MINUS_SIGN.getValue());
				calculatorOutputDTO = subtract(calculatorInputDTOForTransformedAdd);
			}else if(!StringUtils.containsAny(calculatorInputDTO.getOperand_1(), CommonConstants.HYPHEN) && StringUtils.containsAny(calculatorInputDTO.getOperand_2(), CommonConstants.HYPHEN)) {
				calculatorInputDTOForTransformedAdd = new CalculatorInputDTO();
				calculatorInputDTOForTransformedAdd.setOperand_1(calculatorInputDTO.getOperand_1());
				calculatorInputDTOForTransformedAdd.setOperand_2(calculatorInputDTO.getOperand_2().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorInputDTOForTransformedAdd.setOperator(OperatorEnum.MINUS_SIGN.getValue());
				calculatorOutputDTO = subtract(calculatorInputDTOForTransformedAdd);
			}else if(StringUtils.containsAny(calculatorInputDTO.getOperand_1(), CommonConstants.HYPHEN) && StringUtils.containsAny(calculatorInputDTO.getOperand_2(), CommonConstants.HYPHEN)) {
				calculatorOutputDTO = processAdd(calculatorInputDTO);
				calculatorOutputDTO.setResult(CommonConstants.HYPHEN + calculatorOutputDTO.getResult());
			}else {
				calculatorOutputDTO = processAdd(calculatorInputDTO);
			}
		} catch (ServiceException  e) {
			_logger.error(e.getMessage(), e);
			throw e;
		} catch (Throwable t) {
			throw new ServiceException(t);
		}
		_logger.info("Exiting add method");
		return calculatorOutputDTO;
	}

	/**
	 * @param calculatorInputDTO
	 * @param carryOver
	 * @return
	 * @throws ServiceException
	 */
	private CalculatorOutputDTO processAdd(CalculatorInputDTO calculatorInputDTO)
			throws ServiceException {
		CalculatorOutputDTO calculatorOutputDTO;
		int maxOperandDecimalLength;
		int currentSum;
		int index;
		int carryOver = 0;
		String result;
		int[] operand1Array;
		int[] operand2Array;
		List<Integer> resultList;
		maxOperandDecimalLength = calculatorServiceHelper.processOperands(calculatorInputDTO);
		operand1Array = new int [calculatorInputDTO.getOperand_1().length()];
		operand2Array = new int [calculatorInputDTO.getOperand_2().length()];
		calculatorServiceHelper.populateOperandArrays(operand1Array, operand2Array, calculatorInputDTO);
		resultList = new ArrayList<>();
		for(index = operand1Array.length - 1; index >= 0; index--) {
			currentSum = operand1Array[index] + operand2Array[index] + carryOver;
			carryOver = currentSum / 10;
			resultList.add(currentSum % 10);
			if(resultList.size() == maxOperandDecimalLength) {
				resultList.add(-1);
			}
		}
		if(carryOver != 0) {
			resultList.add(carryOver);
		}
		result = StringUtils.reverse(resultList.stream().map(e -> e == -1 ? CommonConstants.DOT : String.valueOf(e)).collect(Collectors.joining()));
		result = calculatorServiceHelper.postProcessResult(result);
		_logger.debug("result: " + result);
		calculatorOutputDTO = new CalculatorOutputDTO(result);
		return calculatorOutputDTO;
	}
	
	/**
	 * @param calculatorInputDTO
	 * @return
	 * @throws ServiceException
	 */
	private CalculatorOutputDTO subtract(CalculatorInputDTO calculatorInputDTO) throws ServiceException {
		_logger.info("Entering subtract method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		CalculatorInputDTO calculatorInputDTOForTransformedSubtract = null;
		try {
			
			if(StringUtils.containsAny(calculatorInputDTO.getOperand_1(), CommonConstants.HYPHEN) && StringUtils.containsAny(calculatorInputDTO.getOperand_2(), CommonConstants.HYPHEN)) {
				calculatorInputDTOForTransformedSubtract = new CalculatorInputDTO();
				calculatorInputDTOForTransformedSubtract.setOperand_1(calculatorInputDTO.getOperand_2().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorInputDTOForTransformedSubtract.setOperand_2(calculatorInputDTO.getOperand_1().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorOutputDTO = processSubtract(calculatorInputDTOForTransformedSubtract);
			}else if(StringUtils.containsAny(calculatorInputDTO.getOperand_1(), CommonConstants.HYPHEN) && !StringUtils.containsAny(calculatorInputDTO.getOperand_2(), CommonConstants.HYPHEN)) {
				calculatorInputDTOForTransformedSubtract = new CalculatorInputDTO();
				calculatorInputDTOForTransformedSubtract.setOperand_1(calculatorInputDTO.getOperand_1().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorInputDTOForTransformedSubtract.setOperand_2(calculatorInputDTO.getOperand_2().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorOutputDTO = processAdd(calculatorInputDTOForTransformedSubtract);
				calculatorOutputDTO.setResult(CommonConstants.HYPHEN + calculatorOutputDTO.getResult());
			}else if(!StringUtils.containsAny(calculatorInputDTO.getOperand_1(), CommonConstants.HYPHEN) && StringUtils.containsAny(calculatorInputDTO.getOperand_2(), CommonConstants.HYPHEN)) {
				calculatorInputDTOForTransformedSubtract = new CalculatorInputDTO();
				calculatorInputDTOForTransformedSubtract.setOperand_1(calculatorInputDTO.getOperand_1());
				calculatorInputDTOForTransformedSubtract.setOperand_2(calculatorInputDTO.getOperand_2().replaceAll(CommonConstants.HYPHEN, CommonConstants.EMPTY));
				calculatorOutputDTO = processAdd(calculatorInputDTOForTransformedSubtract);
			}else {
				calculatorOutputDTO = processSubtract(calculatorInputDTO);
			}
		} catch (ServiceException  e) {
			_logger.error(e.getMessage(), e);
			throw e;
		} catch (Throwable t) {
			throw new ServiceException(t);
		}
		_logger.info("Exiting subtract method");
		return calculatorOutputDTO;
	}

	/**
	 * @param calculatorInputDTO
	 * @return
	 * @throws ServiceException
	 */
	private CalculatorOutputDTO processSubtract(CalculatorInputDTO calculatorInputDTO) throws ServiceException {
		CalculatorOutputDTO calculatorOutputDTO;
		int maxOperandDecimalLength;
		int currentDiff;
		int index;
		String result;
		int[] operand1Array;
		int[] operand2Array;
		int[] swapArray;
		List<Integer> resultList;
		boolean isSwap = false;
		int carryOver = 0;
		maxOperandDecimalLength = calculatorServiceHelper.processOperands(calculatorInputDTO);
		operand1Array = new int [calculatorInputDTO.getOperand_1().length()];
		operand2Array = new int [calculatorInputDTO.getOperand_2().length()];
		calculatorServiceHelper.populateOperandArrays(operand1Array, operand2Array, calculatorInputDTO);
		for(index = 0; index < operand1Array.length; index++) {
			if(operand1Array[index] < operand2Array[index]) {
				isSwap = true;
				break;
			}else if(operand1Array[index] > operand2Array[index]) {
				isSwap = false;
				break;
			}
		}
		if(isSwap) {
			swapArray = new int [calculatorInputDTO.getOperand_1().length()];
			swapArray = operand1Array;
			operand1Array = operand2Array;
			operand2Array = swapArray;
		}
		resultList = new ArrayList<>();
		for(index = operand1Array.length - 1; index >= 0; index--) {
			currentDiff = operand1Array[index] - operand2Array[index] - carryOver;
			if(currentDiff < 0) {
				carryOver = 1;
				currentDiff += 10;
			}else {
				carryOver = 0;
			}
			resultList.add(currentDiff);
			if(resultList.size() == maxOperandDecimalLength) {
				resultList.add(-1);
			}
		}
		if(isSwap) {
			resultList.add(-2);
		}
		result = StringUtils.reverse(resultList.stream().map(e -> e == -1 ? CommonConstants.DOT : (e == -2 ? CommonConstants.HYPHEN : String.valueOf(e))).collect(Collectors.joining()));
		result = calculatorServiceHelper.postProcessResult(result);
		_logger.debug("result: " + result);
		calculatorOutputDTO = new CalculatorOutputDTO(result);
		return calculatorOutputDTO;
	}

	/**
	 * @param calculatorInputDTO
	 * @return
	 */
	private CalculatorOutputDTO multiply(CalculatorInputDTO calculatorInputDTO) {
		_logger.info("Entering multiply method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		if(null != calculatorInputDTO && NumberUtils.isParsable(calculatorInputDTO.getOperand_1()) && NumberUtils.isParsable(calculatorInputDTO.getOperand_2())) {
			int multiplierPower = Math.max(
					StringUtils.substring(calculatorInputDTO.getOperand_1(), calculatorInputDTO.getOperand_1().indexOf(CommonConstants.DOT_REGEX) + 1).length(),
					StringUtils.substring(calculatorInputDTO.getOperand_2(), calculatorInputDTO.getOperand_2().indexOf(CommonConstants.DOT_REGEX) + 1).length());
			long operand_1 = (long)(Double.parseDouble(calculatorInputDTO.getOperand_1()) * Math.pow(10.0, multiplierPower));
			long operand_2 = (long)(Double.parseDouble(calculatorInputDTO.getOperand_2()) * Math.pow(10.0, multiplierPower));
			double result = (double) ((operand_1 * operand_2) / Math.pow(10.0, multiplierPower * 2));
			_logger.debug("operand_1: " + calculatorInputDTO.getOperand_1());
			_logger.debug("operand_2: " + calculatorInputDTO.getOperand_2());
			_logger.debug("result: " + result);
			calculatorOutputDTO = new CalculatorOutputDTO(result);
		}else {
			_logger.debug("invalid input!");
			calculatorOutputDTO = new CalculatorOutputDTO();
		}
		_logger.info("Exiting multiply method");
		return calculatorOutputDTO;
	}

	/**
	 * @param calculatorInputDTO
	 * @return
	 */
	private CalculatorOutputDTO divide(CalculatorInputDTO calculatorInputDTO) {
		_logger.info("Entering divide method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		if(null != calculatorInputDTO && NumberUtils.isParsable(calculatorInputDTO.getOperand_1()) && NumberUtils.isParsable(calculatorInputDTO.getOperand_2())) {
			double result = Double.parseDouble(calculatorInputDTO.getOperand_1()) / Double.parseDouble(calculatorInputDTO.getOperand_2());
			_logger.debug("operand_1: " + calculatorInputDTO.getOperand_1());
			_logger.debug("operand_2: " + calculatorInputDTO.getOperand_2());
			_logger.debug("result: " + result);
			calculatorOutputDTO = new CalculatorOutputDTO(result);
		}else {
			_logger.debug("invalid input!");
			calculatorOutputDTO = new CalculatorOutputDTO();
		}
		_logger.info("Exiting divide method");
		return calculatorOutputDTO;
	}

	/**
	 * @param calculatorInputDTO
	 * @param bindingResult
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public CalculatorOutputDTO calculate(CalculatorInputDTO calculatorInputDTO, BindingResult bindingResult) throws ServiceException {
		CalculatorOutputDTO calculatorOutputDTO = null;
		try {
			if(OperatorEnum.PLUS_SIGN.getValue().equalsIgnoreCase(calculatorInputDTO.getOperator())) {
				calculatorOutputDTO = add(calculatorInputDTO);
			}else if(OperatorEnum.MINUS_SIGN.getValue().equalsIgnoreCase(calculatorInputDTO.getOperator())) {
				calculatorOutputDTO = subtract(calculatorInputDTO);
			}else if(OperatorEnum.ASTERISK_SIGN.getValue().equalsIgnoreCase(calculatorInputDTO.getOperator())) {
				calculatorOutputDTO = multiply(calculatorInputDTO);
			}else if(OperatorEnum.FORWARD_SLASH_SIGN.getValue().equalsIgnoreCase(calculatorInputDTO.getOperator())) {
				calculatorOutputDTO = divide(calculatorInputDTO);
			}
		}catch(Throwable t) {
			throw new ServiceException(t);
		}
		return calculatorOutputDTO;
	}

}
