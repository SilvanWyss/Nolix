//package declaration
package ch.nolix.coreTutorial.validator2Tutorial;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class provides a tutorial how the zeta validator validates an integer.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 30
 */
public final class IntegerValidationTutorial2 {

	//main method
	/**
	 * Lets the zeta validator suppose once that 5 is positive and once suppose that 5 is negative.
	 * This is done that the error message of a probable thrown exception contains the variable name.
	 */
	public static void main(String[] args) {

		//Supposes that 5 is positive, what makes that the zeta validator does not complain.
		Validator.supposeThat(5).thatIsNamed("size").isPositive();
		
		//Supposes that 5 is negative, what makes that the zeta validator throws a NegativeArgumentException.
		Validator.supposeThat(5).thatIsNamed("size").isNegative();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private IntegerValidationTutorial2() {}
}
