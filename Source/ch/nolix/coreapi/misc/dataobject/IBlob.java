package ch.nolix.coreapi.misc.dataobject;

public interface IBlob {

  byte[] getStoredBytes();

  int getSize();
}
