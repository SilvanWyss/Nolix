/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.ssl;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificate {
  String getPrivateKeyPemFilePath();

  String getPublicKeyPemFilePath();
}
