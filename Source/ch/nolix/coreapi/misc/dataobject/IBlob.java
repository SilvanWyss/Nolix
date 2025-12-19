package ch.nolix.coreapi.misc.dataobject;

/**
 * @author Silvan Wyss
 */
public interface IBlob {
  byte[] getStoredBytes();

  int getSize();
}
