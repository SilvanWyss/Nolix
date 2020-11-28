//package declaration
package ch.nolix.common.commontypehelper;

//Java import
import java.util.Arrays;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class ArrayHelper {
	
	//static method
	public static double[] createCopyOf(final double[] array) {
		
		Validator.assertThat(array).thatIsNamed(VariableNameCatalogue.ARRAY).isNotNull();
		
		return Arrays.copyOf(array, array.length);
	}
	
	//static method
	public static <E> E[] createCopyOf(final E[] array) {

		Validator.assertThat(array).thatIsNamed(VariableNameCatalogue.ARRAY).isNotNull();
		
		return Arrays.copyOf(array, array.length);
	}
	
	//visibility-reduced constructor
	private ArrayHelper() {}
}
