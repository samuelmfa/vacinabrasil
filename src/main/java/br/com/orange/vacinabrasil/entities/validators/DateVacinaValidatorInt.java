package br.com.orange.vacinabrasil.entities.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DateVacinaValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateVacinaValidatorInt {
	String message() default "Data Inválida";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
