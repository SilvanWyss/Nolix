/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.ssl;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.net.ssl.SslCertificateKeyReader;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SslCertificateKeyReaderTest extends StandardTest {
  @Test
  void testCase_getKeyFromPemFileLines() {
    // setup
    final var pemFileLines = //
    ImmutableList.withElements(
      "-----BEGIN PRIVATE KEY-----",
      "AAAAA",
      "BBBBB",
      "-----END PRIVATE KEY-----");
    final var testUnit = new SslCertificateKeyReader();

   // execute
    final var result = testUnit.getKeyFromPemFileLines(pemFileLines);

   // verify
    expect(result).isEqualTo("AAAAABBBBB");
  }
}
