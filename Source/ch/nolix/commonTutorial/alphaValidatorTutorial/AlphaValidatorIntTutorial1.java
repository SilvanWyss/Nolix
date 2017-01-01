/*
 * file:	AlphaValidatorIntTutorial1.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	30
 */

//package declaration
package ch.nolix.commonTutorial.alphaValidatorTutorial;

//own import
import ch.nolix.common.zetaValidator.ZetaValidator;

//package-visible class
/**
 * This class provides a tutorial for the alpha validator class.
 */
final class AlphaValidatorIntTutorial1 {

	//main method
	/**
	 * Lets the alpha validator once suppose that 5 is positive and once suppose that 5 is negative.
	 */
	public static void main(String[] args) {
		
		//Supposes that 5 is positive, what makes that the alpha validator does not complain.
		ZetaValidator.supposeThat(5).isPositive();
		
		//Supposes that 5 is negative, what makes that the alpha validator throws a NegativeArgumentException.
		ZetaValidator.supposeThat(5).isNegative();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private AlphaValidatorIntTutorial1() {}
}
