package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;

//record
public record SslCertificate(String publicKeyPemFilePath, String privateKeyPemFilePath) implements ISslCertificate {

  @Override
  public String getPrivateKeyPemFilePath() {
    return privateKeyPemFilePath;
  }

  @Override
  public String getPublicKeyPemFilePath() {
    return publicKeyPemFilePath;
  }
}
