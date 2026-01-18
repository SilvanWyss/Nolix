/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.commontypetool.arraytool;

/**
 * @author Silvan Wyss
 */
public interface IArrayTool {
  <E> E[] createArrayWithElement(E element, @SuppressWarnings("unchecked") E... elements);

  double[] createArrayWithValue(double value, double... values);

  IByteArrayMediator onArray(byte[] byteArray);
}
