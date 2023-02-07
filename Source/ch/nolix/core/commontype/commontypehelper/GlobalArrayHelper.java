//package declaration
package ch.nolix.core.commontype.commontypehelper;

//Java imports
import java.util.Arrays;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class GlobalArrayHelper {
	
	//static method
	public static double[] createCopyOfArray(final double[] array) {
		
		GlobalValidator.assertThat(array).thatIsNamed(LowerCaseCatalogue.ARRAY).isNotNull();
		
		return Arrays.copyOf(array, array.length);
	}
	
	//static method
	public static <E> E[] createCopyOfArray(final E[] array) {

		GlobalValidator.assertThat(array).thatIsNamed(LowerCaseCatalogue.ARRAY).isNotNull();
		
		return Arrays.copyOf(array, array.length);
	}
	
	//static method
	public static ByteArrayMediator onArray(final byte[] byteArray) {
		return new ByteArrayMediator(byteArray);
	}
	
	//constructor
	private GlobalArrayHelper() {}
}
