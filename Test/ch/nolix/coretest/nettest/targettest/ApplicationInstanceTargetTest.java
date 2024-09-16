//package declaration
package ch.nolix.coretest.nettest.targettest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
final class ApplicationInstanceTargetTest extends StandardTest {

  //method
  @Test
  void testCase_toUrl_forHttpPortAndNoneSecurityMode() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        PortCatalogue.HTTP,
        "Demo Application",
        "demo_application",
        SecurityMode.NONE);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("http://nolix.tech?app=demo_application");
  }

  //method
  @Test
  void testCase_toUrl_forHttpsPortAndSSLSecurityMode() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        PortCatalogue.HTTPS,
        "Demo Application",
        "demo_application",
        SecurityMode.SSL);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("https://nolix.tech?app=demo_application");
  }

  //method
  @Test
  void testCase_toUrl_forCustomPortAndNoneSecurityMode() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        50000,
        "Demo Application",
        "demo_application",
        SecurityMode.NONE);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("http://nolix.tech:50000?app=demo_application");
  }

  //method
  @Test
  void testCase_toUrl_forCustomPortAndSslSecurityMode() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityModeForConnections(
        "nolix.tech",
        50000,
        "Demo Application",
        "demo_application",
        SecurityMode.SSL);

    //execution
    final var result = testUnit.toUrl();

    //verification
    expect(result).isEqualTo("https://nolix.tech:50000?app=demo_application");
  }
}
