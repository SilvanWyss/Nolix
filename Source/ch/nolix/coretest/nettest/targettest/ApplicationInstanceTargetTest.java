//package declaration
package ch.nolix.coretest.nettest.targettest;

//own imports
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class ApplicationInstanceTargetTest extends StandardTest {

  //method
  @TestCase
  public void testCase_toUrl_forHttpPortAndUnsecureSecurityLevel() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityLevelForConnections(
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
  @TestCase
  public void testCase_toUrl_forHttpsPortAndSecureSecurityLevel() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityLevelForConnections(
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
  @TestCase
  public void testCase_toUrl_forCustomPortAndUnsecureSecurityLevel() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityLevelForConnections(
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
  @TestCase
  public void testCase_toUrl_forCustomPortAndSecureSecurityLevel() {

    //setup
    final var testUnit = ApplicationInstanceTarget
      .forIpOrDomainAndPortAndApplicationInstanceNameAndApplicationUrlInstanceNameAndSecurityLevelForConnections(
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
