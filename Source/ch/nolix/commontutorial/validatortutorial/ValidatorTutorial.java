package ch.nolix.commontutorial.validatortutorial;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

/**
 * The {@link ValidatorTutorial} is a tutorial for the {@link Validator}.
 * Of the {@link ValidatorTutorial} an instance cannot be created.
 * 
 * @author Silvan Wys
 * @month 2017-05
 * @lines 40
 */
public final class ValidatorTutorial {
	
	public static void main(String[] args) {
		printAmount("apple", 5);
		printAmount("banana", 10);
		printAmount("cake", 2);
	}
	
	/**
	 * Prints out to the console the amount of the product with given productName.
	 * 
	 * @param productName
	 * @param amount
	 * @throws ArgumentIsNullException if the given productName is null.
	 * @throws EmptyArgumentException if the given productName is empty.
	 * @throws NegativeArgumentException if the given amount is negative.
	 */
	private static void printAmount(final String productName, final int amount) {
		
		//Asserts that the given productName is not null or empty.
		Validator.assertThat(productName).thatIsNamed("product name").isNotEmpty();
		
		//Asserts that the given amount is not negative.
		Validator.assertThat(amount).thatIsNamed("amount").isNotNegative();
		
		System.out.println(productName + ": " + amount + " pieces");
	}
	
	/**
	 * Prevents that an instance of the {@link ValidatorTutorial} can be created.
	 */
	private ValidatorTutorial() {}
}
