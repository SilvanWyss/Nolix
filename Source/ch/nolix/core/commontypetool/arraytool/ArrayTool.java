package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.coreapi.commontypetool.arraytool.IArrayTool;
import ch.nolix.coreapi.commontypetool.arraytool.IByteArrayMediator;

/**
 * @author Silvan Wyss
 */
public final class ArrayTool implements IArrayTool {
  @Override
  public <E> E[] createArrayWithElement(final E element, final @SuppressWarnings("unchecked") E... elements) {
    final @SuppressWarnings("unchecked") var array = (E[]) new Object[elements.length + 1];
    array[0] = element;
    System.arraycopy(elements, 0, array, 1, elements.length);

    return array;
  }

  @Override
  public double[] createArrayWithValue(final double value, final double... values) {
    final var array = new double[1 + values.length];
    array[0] = value;
    System.arraycopy(values, 0, array, 1, values.length);

    return array;
  }

  @Override
  public IByteArrayMediator onArray(final byte[] byteArray) {
    return ByteArrayMediator.forByteArray(byteArray);
  }
}
