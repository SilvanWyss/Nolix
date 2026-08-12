/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.blob;

/**
 * @author Silvan Wyss
 */
public interface IBlob {
  int getSizeInBytes();

  byte[] getStoredBytes();
}
