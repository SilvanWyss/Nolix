//package declaration
package ch.nolix.coreTutorial.validator2Tutorial;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * This class provides a tutorial for the validator.
 * 
 * @author Silvan Wys
 * @month 2017-05
 * @lines 50
 */
public final class ValidatorTutorial {

	//main method
	public static void main(String[] args) {
		printAmount("apple", 5);
		printAmount("banana", 10);
		printAmount("cake", 2);
	}
	
	//static method
	/**
	 * Prints out to the console the amount of the product with given product name.
	 * 
	 * @param productName
	 * @param amount
	 * @throws NullArgumentException if the given product name is null.
	 * @throws EmptyArgumentException if the given product name is empty.
	 * @throws NegativArgumentException if the given amount is negative.
	 */
	public static void printAmount(final String productName, final int amount) {
		
		//Checks if the given product name is not null or empty.
		Validator.suppose(productName).thatIsNamed("product name").isNotEmpty();
		
		//Checks if the given amount is not negative.
		Validator.suppose(amount).thatIsNamed("amount").isNotNegative();
		
		System.out.println(productName + ": " + amount + " pieces");
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ValidatorTutorial() {}
}
