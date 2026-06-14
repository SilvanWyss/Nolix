/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.ssl;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public final class SslCertificateKeyReaderHelper {
  private SslCertificateKeyReaderHelper() {
  }

  public static IWellOrderContainer<String> getKeyLinesFromPemFileLines(final IWellOrderContainer<String> pemFileLines) {
    return pemFileLines.getStoredSelected(SslCertificateKeyReaderHelper::isKeyLine);
  }

  public static boolean isKeyLine(final String line) {
    return //
    line != null
    && !line.isBlank()
    && !line.trim().equals("-----BEGIN PRIVATE KEY-----")
    && !line.trim().equals("-----END PRIVATE KEY-----");
  }
}
