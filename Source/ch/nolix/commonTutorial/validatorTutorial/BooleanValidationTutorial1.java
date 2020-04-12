package ch.nolix.commonTutorial.validatorTutorial;

import ch.nolix.common.validator.Validator;

/**
 * This class provides a tutorial how the zeta validator validates a boolean.
 * 
 * @author Silvan Wys
 * @month 2016-12
 * @lines 30
 */
public final class BooleanValidationTutorial1 {
	
	/**
	 * Lets the zeta validator suppose once that a true boolean is true and once that a true boolean is false.
	 */
	public static void main(String[] args) {
		
		//Supposes that a true boolean is true, what makes that the zeta validator does not complain.
		Validator.assertThat(true);
		
		//Supposes that a true boolean is false, what makes that the zeta validator throws a TrueArgumentException.
		Validator.assertThatNot(true);
	}
	
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private BooleanValidationTutorial1() {}
}
