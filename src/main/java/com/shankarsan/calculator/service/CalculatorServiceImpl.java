/**
 * 
 */
package com.shankarsan.calculator.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shankarsan.calculator.dto.CalculatorInputDTO;
import com.shankarsan.calculator.dto.CalculatorOutputDTO;

/**
 * @author SHANKARSAN
 *
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {
	
	private static final Logger _logger = LoggerFactory.getLogger(CalculatorServiceImpl.class);

	/**
	 * @param calculatorInputDTO
	 * @return
	 */
	@Override
	public CalculatorOutputDTO add(CalculatorInputDTO calculatorInputDTO) {
		_logger.info("Entering add method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		if(null != calculatorInputDTO && NumberUtils.isParsable(calculatorInputDTO.getOperand_1()) && NumberUtils.isParsable(calculatorInputDTO.getOperand_2())) {
			int multiplierPower = Math.max(
					StringUtils.substring(calculatorInputDTO.getOperand_1(), calculatorInputDTO.getOperand_1().indexOf(".") + 1).length(),
					StringUtils.substring(calculatorInputDTO.getOperand_2(), calculatorInputDTO.getOperand_2().indexOf(".") + 1).length());
			long operand_1 = (long)(Double.parseDouble(calculatorInputDTO.getOperand_1()) * Math.pow(10.0, multiplierPower));
			long operand_2 = (long)(Double.parseDouble(calculatorInputDTO.getOperand_2()) * Math.pow(10.0, multiplierPower));
			double result = (double) (Math.addExact(operand_1, operand_2) / Math.pow(10.0, multiplierPower));
			_logger.debug("operand_1: " + calculatorInputDTO.getOperand_1());
			_logger.debug("operand_2: " + calculatorInputDTO.getOperand_2());
			_logger.debug("result: " + result);
			calculatorOutputDTO = new CalculatorOutputDTO(result);
		}else {
			_logger.debug("invalid input!");
			calculatorOutputDTO = new CalculatorOutputDTO();
		}
		_logger.info("Exiting add method");
		return calculatorOutputDTO;
	}

	@Override
	public CalculatorOutputDTO subtract(CalculatorInputDTO calculatorInputDTO) {
		_logger.info("Entering subtract method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		if(null != calculatorInputDTO && NumberUtils.isParsable(calculatorInputDTO.getOperand_1()) && NumberUtils.isParsable(calculatorInputDTO.getOperand_2())) {
			int multiplierPower = Math.max(
					StringUtils.substring(calculatorInputDTO.getOperand_1(), calculatorInputDTO.getOperand_1().indexOf(".") + 1).length(),
					StringUtils.substring(calculatorInputDTO.getOperand_2(), calculatorInputDTO.getOperand_2().indexOf(".") + 1).length());
			long operand_1 = (long)(Double.parseDouble(calculatorInputDTO.getOperand_1()) * Math.pow(10.0, multiplierPower));
			long operand_2 = (long)(Double.parseDouble(calculatorInputDTO.getOperand_2()) * Math.pow(10.0, multiplierPower));
			double result = (double) (Math.subtractExact(operand_1, operand_2) / Math.pow(10.0, multiplierPower));
			_logger.debug("operand_1: " + calculatorInputDTO.getOperand_1());
			_logger.debug("operand_2: " + calculatorInputDTO.getOperand_2());
			_logger.debug("result: " + result);
			calculatorOutputDTO = new CalculatorOutputDTO(result);
		}else {
			_logger.debug("invalid input!");
			calculatorOutputDTO = new CalculatorOutputDTO();
		}
		_logger.info("Exiting subtract method");
		return calculatorOutputDTO;
	}

	@Override
	public CalculatorOutputDTO multiply(CalculatorInputDTO calculatorInputDTO) {
		_logger.info("Entering multiply method");
		CalculatorOutputDTO calculatorOutputDTO = null;
		if(null != calculatorInputDTO && NumberUtils.isParsable(calculatorInputDTO.getOperand_1()) && NumberUtils.isParsable(calculatorInputDTO.getOperand_2())) {
			int multiplierPower = Math.max(
					StringUtils.substring(calculatorInputDTO.getOperand_1(), calculatorInputDTO.getOperand_1().indexOf(".") + 1).length(),
					StringUtils.substring(calculatorInputDTO.getOperand_2(), calculatorInputDTO.getOperand_2().indexOf(".") + 1).length());
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

	@Override
	public CalculatorOutputDTO divide(CalculatorInputDTO calculatorInputDTO) {
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

}
