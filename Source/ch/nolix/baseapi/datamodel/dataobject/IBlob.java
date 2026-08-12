/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datamodel.dataobject;

/**
 * @author Silvan Wyss
 */
public interface IBlob {
  int getSizeInBytes();

  byte[] getStoredBytes();
}
