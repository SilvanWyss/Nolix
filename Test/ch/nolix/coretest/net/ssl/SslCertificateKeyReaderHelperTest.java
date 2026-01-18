/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.net.ssl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.core.net.ssl.SslCertificateKeyReaderHelper;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SslCertificateKeyReaderHelperTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(strings = {
  "",
  " ",
  "  ",
  "   ",
  "    ",
  "-----BEGIN PRIVATE KEY-----",
  "-----END PRIVATE KEY-----",
  " -----BEGIN PRIVATE KEY----- ",
  " -----END PRIVATE KEY----- ",
  "  -----BEGIN PRIVATE KEY-----  ",
  "  -----END PRIVATE KEY-----  " })
  void testCase_isKeyLine_whenGivenLineIsNotAKeyLine(final String line) {

    //execution
    final var result = SslCertificateKeyReaderHelper.isKeyLine(line);

    //verification
    expect(result).isFalse();
  }
}
