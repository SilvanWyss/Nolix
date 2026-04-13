/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontypetool.arraytool;

import ch.nolix.baseapi.commontypetool.arraytool.IArrayTool;
import ch.nolix.baseapi.commontypetool.arraytool.IByteArrayMediator;

/**
 * @author Silvan Wyss
 */
public final class ArrayTool implements IArrayTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public double[] createArrayWithValue(final double value, final double... values) {
    final var array = new double[1 + values.length];
    array[0] = value;
    System.arraycopy(values, 0, array, 1, values.length);

    return array;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IByteArrayMediator onArray(final byte[] byteArray) {
    return ByteArrayMediator.forByteArray(byteArray);
  }
}
