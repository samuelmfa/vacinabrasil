package br.com.orange.vacinabrasil.entities.validators;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateValidatorInt, Date> {

	@Override
	public void initialize(DateValidatorInt endDate) {
	}

	@Override
	public boolean isValid(Date endDate, ConstraintValidatorContext context) {
		Date startDate = new Date();
		return endDate.compareTo(startDate) <= 0;
	}

}
