//package declaration
package ch.nolix.coreTutorial.validator2Tutorial;

import ch.nolix.primitive.validator2.Validator;

//class
/**
 * This class provides a tutorial how the zeta validator validates a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 30
 */
public final class BooleanValidationTutorial2 {

	//main method
	/**
	 * Lets the zeta validator suppose once that true booleans are all true and once suppose that mixed booleans are all true.
	 */
	public static void main(String[] args) {
		
		//Supposes that true booleans are all true, what makes that the zeta validator does not complain.
		Validator.suppose(true, true, true, true);
		
		//Supposes that mixed booleans are all true, what makes that the zeta validator throws a FalseArgumentException.
		Validator.suppose(true, true, true, false);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private BooleanValidationTutorial2() {}
}
