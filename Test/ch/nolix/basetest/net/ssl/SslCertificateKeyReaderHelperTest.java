/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.ssl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.net.ssl.SslCertificateKeyReaderHelper;
import ch.nolix.base.testing.standardtest.StandardTest;

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
