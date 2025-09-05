package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.net.ssl.ISslCertificate;

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
