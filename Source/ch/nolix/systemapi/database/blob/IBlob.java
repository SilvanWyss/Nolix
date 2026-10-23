/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.blob;

/**
 * @author Silvan Wyss
 */
public interface IBlob {
  /**
   * @return the size of the current {@link IBlob} in bytes
   */
  int getSizeInBytes();

  /**
   * @return the bytes of the current {@link IBlob}
   */
  byte[] getStoredBytes();
}
