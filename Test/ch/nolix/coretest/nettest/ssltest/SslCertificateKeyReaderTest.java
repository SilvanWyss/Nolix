//package declaration
package ch.nolix.coretest.nettest.ssltest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.net.ssl.SslCertificateKeyReader;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class SslCertificateKeyReaderTest extends StandardTest {

  //method
  @Test
  void testCase_getKeyFromPemFileLines() {

    //setup
    final var pemFileLines = //
    ImmutableList.withElement(
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

  //method
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
  void testCase_whenGivenLineIsNotAKeyLine(final String line) {

    //setup
    final var testUnit = new SslCertificateKeyReader();

    //execution
    final var result = testUnit.isKeyLine(line);

    //verification
    expectNot(result);
  }
}
