package ch.nolix.coreapi.netapi.sslapi;

public interface ISslCertificate {

  String getPrivateKeyPemFilePath();

  String getPublicKeyPemFilePath();
}
