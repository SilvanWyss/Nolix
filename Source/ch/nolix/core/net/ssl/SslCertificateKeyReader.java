package ch.nolix.core.net.ssl;

import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.net.ssl.ISslCertificateKeyReader;

/**
 * @author Silvan Wyss
 */
public final class SslCertificateKeyReader implements ISslCertificateKeyReader {
  @Override
  public String getKeyFromPemFileLines(final IContainer<String> pemFileLines) {
    final var keyLines = SslCertificateKeyReaderHelper.getKeyLinesFromPemFileLines(pemFileLines);

    return keyLines.toConcatenatedString();
  }

  @Override
  public String readKeyFromPemFile(final String pemFilePath) {
    final var pemFileLines = FileSystemAccessor.readFileToLines(pemFilePath);

    return getKeyFromPemFileLines(pemFileLines);
  }
}
