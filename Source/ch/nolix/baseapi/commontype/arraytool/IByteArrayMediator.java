/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.arraytool;

/**
 * @author Silvan Wyss
 */
public interface IByteArrayMediator {
  /**
   * @param index
   * @return a new {@link IByteArrayWithStartIndexMediator} for the byte array of
   *         the current {@link IByteArrayMediator} with the given index as start
   *         index
   */
  IByteArrayWithStartIndexMediator fromIndex(int index);
}
