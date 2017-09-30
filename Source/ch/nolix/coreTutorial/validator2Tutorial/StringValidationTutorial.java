//package declaration
package ch.nolix.coreTutorial.validator2Tutorial;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class provides a tutorial how the zeta validator validates a string.
 * 
 * @author Silvan
 * @month 2017-03
 * @lines 30
 */
public final class StringValidationTutorial {

	//main method
	/**
	 * Lets the zeta validator suppose properties of the string 'Hello World!'.
	 */
	public static void main(String[] args) {
		
		//Supposes that the string 'Hello World!' is not null and not empty, what makes that the zeta validator does not complain.
		Validator.suppose("Hello World!").isNotEmpty();
		
		//Supposes that the string 'Hello World' has the max length 10, what makes that the zeta validator throws an InvalidArgumentException.
		Validator.suppose("Hello World!").hasMaxLength(10);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private StringValidationTutorial() {}
}
