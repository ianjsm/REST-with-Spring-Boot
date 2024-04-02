package com.ian.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ian.springboot.exceptions.UnsupportedMathOperationException;

@RestController
public class MathController {

	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	@GetMapping("sub/{numberOne}/{numberTwo}")
	public Double sub(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}

	@GetMapping("multi/{numberOne}/{numberTwo}")
	public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}

	@GetMapping("div/{num1}/{num2}")
	public Double division(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2)
			throws Exception {
		if (!isNumeric(num1) || !isNumeric(num2)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return convertToDouble(num1) / convertToDouble(num2);
	}

	@GetMapping("average/{num1}/{num2}")
	public Double average(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2)
			throws Exception {
		if (!isNumeric(num1) || !isNumeric(num2)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		return (convertToDouble(num1) + convertToDouble(num2)) / 2;
	}

	@GetMapping("sqrt/{num1}")
	public Double squareroot(@PathVariable(value = "num1") String num1) throws Exception {
		if (!isNumeric(num1)) {
			throw new UnsupportedMathOperationException("Please set a numeric value");
		}
		Double num = convertToDouble(num1);
		return Math.sqrt(num);
	}

	private Double convertToDouble(String strNumber) {
		Double num = 0.0;

		if (strNumber == null) {
			return 0.0;
		}
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number) == true) {
			num = Double.parseDouble(number);
		}
		return num;
	}

	private boolean isNumeric(String strNumber) {
		if (strNumber == null) {
			return false;
		}
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}