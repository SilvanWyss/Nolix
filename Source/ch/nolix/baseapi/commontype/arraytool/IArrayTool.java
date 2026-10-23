/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.arraytool;

/**
 * @author Silvan Wyss
 */
public interface IArrayTool {
  /**
   * @param byteArray
   * @return a new {@link IByteArrayMediator} for the given byteArray
   */
  IByteArrayMediator onArray(byte[] byteArray);
}
