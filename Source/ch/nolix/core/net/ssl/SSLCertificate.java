//package declaration
package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.netapi.sslapi.ISSLCertificate;

//record
public record SSLCertificate(String publicKeyPEMFilePath, String privateKeyPEMFilePath) implements ISSLCertificate {

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
