/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.misc.dataobject;

/**
 * @author Silvan Wyss
 */
public interface IBlob {
  byte[] getStoredBytes();

  int getSize();
}
