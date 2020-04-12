package ch.nolix.commonTutorial.validatorTutorial;

import ch.nolix.common.validator.Validator;

/**
 * This class provides a tutorial how the zeta validator validates an integer.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 30
 */
public final class IntegerValidationTutorial2 {
	
	/**
	 * Lets the zeta validator suppose once that 5 is positive and once suppose that 5 is negative.
	 * This is done that the error message of a probable thrown exception contains the variable name.
	 */
	public static void main(String[] args) {

		//Supposes that 5 is positive, what makes that the zeta validator does not complain.
		Validator.assertThat(5).thatIsNamed("size").isPositive();
		
		//Supposes that 5 is negative, what makes that the zeta validator throws a NegativeArgumentException.
		Validator.assertThat(5).thatIsNamed("size").isNegative();
	}
	
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IntegerValidationTutorial2() {}
}
