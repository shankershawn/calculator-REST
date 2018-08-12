/**
 * 
 */
package com.shankarsan.calculator.service;

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
			calculatorOutputDTO = new CalculatorOutputDTO(Double.parseDouble(calculatorInputDTO.getOperand_1()) + Double.parseDouble(calculatorInputDTO.getOperand_2()));
		}else {
			_logger.debug("invalid input!");
			calculatorOutputDTO = new CalculatorOutputDTO();
		}
		_logger.info("Exiting add method");
		return calculatorOutputDTO;
	}

}
