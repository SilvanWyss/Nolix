/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypetool.arraytool;

/**
 * @author Silvan Wyss
 */
public interface IArrayTool {
  double[] createArrayWithValue(double value, double... values);

  IByteArrayMediator onArray(byte[] byteArray);
}
