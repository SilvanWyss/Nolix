package ch.nolix.core.net.ssl;

import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.net.ssl.ISslCertificateKeyReader;

/**
 * @author Silvan Wyss
 */
public final class SslCertificateKeyReader implements ISslCertificateKeyReader {
  public String getKeyFromPemFileLines(final IContainer<String> pemFileLines) {
    final var keyLines = getKeyLinesFromPemFileLines(pemFileLines);

    return keyLines.toConcatenatedString();
  }

  public boolean isKeyLine(final String line) {
    return !line.isBlank()
    && !line.trim().equals("-----BEGIN PRIVATE KEY-----")
    && !line.trim().equals("-----END PRIVATE KEY-----");
  }

  @Override
  public String readKeyFromPemFile(final String pemFilePath) {
    final var pemFileLines = FileSystemAccessor.readFileToLines(pemFilePath);

    return getKeyFromPemFileLines(pemFileLines);
  }

  private IContainer<String> getKeyLinesFromPemFileLines(final IContainer<String> pemFileLines) {
    return pemFileLines.getStoredSelected(this::isKeyLine);
  }
}
