/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.ssl;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public final class SslCertificateKeyReaderHelper {
  private SslCertificateKeyReaderHelper() {
  }

  /**
   * @param pemFileLines
   * @return the key lines from the given pemFileLines
   */
  public static ExtendedIterable<String> getKeyLinesFromPemFileLines(final ExtendedIterable<String> pemFileLines) {
    return pemFileLines.getStoredSelected(SslCertificateKeyReaderHelper::isKeyLine);
  }

  /**
   * @param line
   * @return true if the given line is a key line of a PEM file, false otherwise
   */
  public static boolean isKeyLine(final String line) {
    return //
    line != null
    && !line.isBlank()
    && !line.trim().equals("-----BEGIN PRIVATE KEY-----")
    && !line.trim().equals("-----END PRIVATE KEY-----");
  }
}
