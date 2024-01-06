//package declaration
package ch.nolix.coretest.nettest.targettest;

//own imports
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.net.target.ApplicationInstanceTarget;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
public final class ApplicationInstanceTargetTest extends Test {

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
