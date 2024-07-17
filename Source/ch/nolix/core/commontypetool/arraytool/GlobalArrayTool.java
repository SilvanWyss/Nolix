//package declaration
package ch.nolix.core.commontypetool.arraytool;

//Java imports
import java.util.Arrays;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class GlobalArrayTool {

  //constructor
  private GlobalArrayTool() {
  }

  //static method
  public static <E> E[] createArrayWithElement(final E element, final @SuppressWarnings("unchecked") E... elements) {

    final @SuppressWarnings("unchecked") var array = (E[]) new Object[elements.length + 1];
    array[0] = element;
    System.arraycopy(elements, 0, array, 1, elements.length);

    return array;
  }

  //static method
  public static double[] createArrayWithValue(final double value, final double... values) {

    final var array = new double[1 + values.length];
    array[0] = value;
    System.arraycopy(values, 0, array, 1, values.length);

    return array;
  }

  //static method
  public static double[] createCopyOfArray(final double[] array) {

    GlobalValidator.assertThat(array).thatIsNamed(LowerCaseVariableCatalogue.ARRAY).isNotNull();

    return Arrays.copyOf(array, array.length);
  }

  //static method
  public static <E> E[] createCopyOfArray(final E[] array) {

    GlobalValidator.assertThat(array).thatIsNamed(LowerCaseVariableCatalogue.ARRAY).isNotNull();

    return Arrays.copyOf(array, array.length);
  }

  //static method
  public static ByteArrayMediator onArray(final byte[] byteArray) {
    return ByteArrayMediator.forByteArray(byteArray);
  }
}
