package br.com.orange.vacinabrasil.entities.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateVacinaValidator implements ConstraintValidator<DateVacinaValidatorInt, Date> {

	@Override
	public void initialize(DateVacinaValidatorInt endDate) {
	}

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext context) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US);

		try {
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			Date startDate = sdf.parse(year + "-00-00T00:00:00.000Z");
			Date data = new Date();
			Date endDate = sdf.parse(sdf.format(data));
			return date.after(startDate) && date.before(endDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

}
