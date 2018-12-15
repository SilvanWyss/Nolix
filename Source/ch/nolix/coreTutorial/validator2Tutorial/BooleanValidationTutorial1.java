//package declaration
package ch.nolix.coreTutorial.validator2Tutorial;

//own imports
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class provides a tutorial how the zeta validator validates a boolean.
 * 
 * @author Silvan Wys
 * @month 2016-12
 * @lines 30
 */
public final class BooleanValidationTutorial1 {

	//main method
	/**
	 * Lets the zeta validator suppose once that a true boolean is true and once that a true boolean is false.
	 */
	public static void main(String[] args) {
		
		//Supposes that a true boolean is true, what makes that the zeta validator does not complain.
		Validator.suppose(true);
		
		//Supposes that a true boolean is false, what makes that the zeta validator throws a TrueArgumentException.
		Validator.supposeNot(true);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private BooleanValidationTutorial1() {}
}
