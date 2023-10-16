//package declaration
package ch.nolix.core.net.tls;

//own imports
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

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
