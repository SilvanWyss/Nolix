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
	public static <E> E[] createArrayWithElement(final E element, final @SuppressWarnings("unchecked")E... elements) {
		
		final @SuppressWarnings("unchecked")var array = (E[])new Object[elements.length + 1];
		array[0] = element;
		System.arraycopy(elements, 0, array, 1, elements.length);
		
		return array;
	}
	
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
