package nc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{

	public boolean isValid(String value, ConstraintValidatorContext context) {
		String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
		 String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)+";
		 String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";

		 String PATTERN =
		            "^" + ATOM + "+(\\." + ATOM + "+)*@"
		                    + DOMAIN
		                    + "|"
		                    + IP_DOMAIN
		                    + ")$";
		return (value!=null) && (value.matches(PATTERN));
	}

}
