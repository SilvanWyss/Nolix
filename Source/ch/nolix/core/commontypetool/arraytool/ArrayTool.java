//package declaration
package ch.nolix.core.commontypetool.arraytool;

//own imports
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IArrayTool;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IByteArrayMediator;

//class
public final class ArrayTool implements IArrayTool {

  //method
  @Override
  public <E> E[] createArrayWithElement(final E element, final @SuppressWarnings("unchecked") E... elements) {

    final @SuppressWarnings("unchecked") var array = (E[]) new Object[elements.length + 1];
    array[0] = element;
    System.arraycopy(elements, 0, array, 1, elements.length);

    return array;
  }

  //method
  @Override
  public double[] createArrayWithValue(final double value, final double... values) {

    final var array = new double[1 + values.length];
    array[0] = value;
    System.arraycopy(values, 0, array, 1, values.length);

    return array;
  }

  //method
  @Override
  public IByteArrayMediator onArray(final byte[] byteArray) {
    return ByteArrayMediator.forByteArray(byteArray);
  }
}
