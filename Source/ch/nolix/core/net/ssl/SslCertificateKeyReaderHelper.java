package ch.nolix.core.net.ssl;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public final class SslCertificateKeyReaderHelper {
  private SslCertificateKeyReaderHelper() {
  }

  public static IContainer<String> getKeyLinesFromPemFileLines(final IContainer<String> pemFileLines) {
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
