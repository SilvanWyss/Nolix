//package declaration
package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;

//record
public record SslCertificate(String publicKeyPemFilePath, String privateKeyPemFilePath) implements ISslCertificate {

  //method
  @Override
  public String getPrivateKeyPemFilePath() {
    return privateKeyPemFilePath;
  }

  //method
  @Override
  public String getPublicKeyPemFilePath() {
    return publicKeyPemFilePath;
  }
}
