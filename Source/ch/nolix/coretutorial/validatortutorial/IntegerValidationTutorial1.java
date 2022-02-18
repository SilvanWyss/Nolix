package ch.nolix.coretutorial.validatortutorial;

import ch.nolix.core.errorcontrol.validator.Validator;

/**
 * This class is a tutorial how the zeta validator validates an integer.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 */
public final class IntegerValidationTutorial1 {
	
	/**
	 * Lets the zeta validator suppose once that 5 is positive and once suppose that 5 is negative.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Supposes that 5 is positive, what makes that the zeta validator does not complain.
		Validator.assertThat(5).isPositive();
		
		//Supposes that 5 is negative, what makes that the zeta validator throws a NegativeArgumentException.
		Validator.assertThat(5).isNegative();
	}
	
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IntegerValidationTutorial1() {}
}
