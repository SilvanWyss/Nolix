package ch.nolix.coreapi.datamodelapi.blobapi;

public interface IBlob {

  byte[] getStoredBytes();

  int getSize();
}
