package ch.nolix.coreapi.datamodel.blob;

public interface IBlob {

  byte[] getStoredBytes();

  int getSize();
}
