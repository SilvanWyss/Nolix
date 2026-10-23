/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.ssl;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificate {
  /**
   * @return the path of the PEM file of the private key of the current
   *         {@link ISslCertificate}
   */
  String getPrivateKeyPemFilePath();

  /**
   * @return the path of the PEM file of the public key of the current
   *         {@link ISslCertificate}
   */
  String getPublicKeyPemFilePath();
}
