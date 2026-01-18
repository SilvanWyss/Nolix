/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.ssl;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificate {
  String getPrivateKeyPemFilePath();

  String getPublicKeyPemFilePath();
}
