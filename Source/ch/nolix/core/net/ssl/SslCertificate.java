/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.net.ssl.ISslCertificate;

//record
public record SslCertificate(String publicKeyPemFilePath, String privateKeyPemFilePath) implements ISslCertificate {
  @Override
  public String getPrivateKeyPemFilePath() {
    return privateKeyPemFilePath;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPublicKeyPemFilePath() {
    return publicKeyPemFilePath;
  }
}
