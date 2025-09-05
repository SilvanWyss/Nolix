package ch.nolix.coreapi.net.ssl;

public interface ISslCertificate {
  String getPrivateKeyPemFilePath();

  String getPublicKeyPemFilePath();
}
