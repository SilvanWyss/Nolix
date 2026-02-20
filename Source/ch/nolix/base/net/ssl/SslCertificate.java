/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.ssl;

import ch.nolix.baseapi.net.ssl.ISslCertificate;

//record
public record SslCertificate(String publicKeyPemFilePath, String privateKeyPemFilePath) implements ISslCertificate {
  /**
   * {@inheritDoc}
   */
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
