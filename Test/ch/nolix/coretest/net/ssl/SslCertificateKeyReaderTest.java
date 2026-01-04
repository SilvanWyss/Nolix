package ch.nolix.coretest.net.ssl;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.net.ssl.SslCertificateKeyReader;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SslCertificateKeyReaderTest extends StandardTest {
  @Test
  void testCase_getKeyFromPemFileLines() {
    //setup
    final var pemFileLines = //
    ImmutableList.withElements(
      "-----BEGIN PRIVATE KEY-----",
      "AAAAA",
      "BBBBB",
      "-----END PRIVATE KEY-----");
    final var testUnit = new SslCertificateKeyReader();

    //execution
    final var result = testUnit.getKeyFromPemFileLines(pemFileLines);

    //verification
    expect(result).isEqualTo("AAAAABBBBB");
  }
}
