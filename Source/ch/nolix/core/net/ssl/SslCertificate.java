//package declaration
package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.netapi.sslapi.ISslCertificate;

//record
public record SslCertificate(String publicKeyPEMFilePath, String privateKeyPEMFilePath) implements ISslCertificate {

  //method
  @Override
  public String getPrivateKeyPEMFilePath() {
    return privateKeyPEMFilePath;
  }

  //method
  @Override
  public String getPublicKeyPEMFilePath() {
    return publicKeyPEMFilePath;
  }
}
