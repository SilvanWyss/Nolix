package ch.nolix.core.net.ssl;

import ch.nolix.core.environment.filesystem.GlobalFileSystemAccessor;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.sslapi.ISslCertificateKeyReader;

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

    final var pemFileLines = GlobalFileSystemAccessor.readFileToLines(pemFilePath);

    return getKeyFromPemFileLines(pemFileLines);
  }

  private IContainer<String> getKeyLinesFromPemFileLines(final IContainer<String> pemFileLines) {
    return pemFileLines.getStoredSelected(this::isKeyLine);
  }
}
