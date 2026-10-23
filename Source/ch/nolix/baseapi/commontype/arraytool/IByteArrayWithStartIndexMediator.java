/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.arraytool;

/**
 * @author Silvan Wyss
 */
public interface IByteArrayWithStartIndexMediator {
  /**
   * Writes the given bytes into the byte array of the current
   * {@link IByteArrayWithStartIndexMediator}, beginning at its start index.
   * 
   * @param bytes
   * @return a new {@link INextIndexMediator} for the index after the written
   *         bytes
   */
  INextIndexMediator write(byte[] bytes);
}
