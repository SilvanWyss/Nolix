/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.ssl;

import ch.nolix.base.environment.filesystem.FileSystemAccessor;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.net.ssl.ISslCertificateKeyReader;

/**
 * @author Silvan Wyss
 */
public final class SslCertificateKeyReader implements ISslCertificateKeyReader {
  /**
   * {@inheritDoc}
   */
  @Override
  public String getKeyFromPemFileLines(final IContainer<String> pemFileLines) {
    final var keyLines = SslCertificateKeyReaderHelper.getKeyLinesFromPemFileLines(pemFileLines);

    return keyLines.toConcatenatedString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String readKeyFromPemFile(final String pemFilePath) {
    final var pemFileLines = FileSystemAccessor.readFileToLines(pemFilePath);

    return getKeyFromPemFileLines(pemFileLines);
  }
}
