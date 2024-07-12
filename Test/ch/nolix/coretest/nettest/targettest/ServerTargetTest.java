//package declaration
package ch.nolix.coretest.nettest.targettest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
final class ServerTargetTest extends StandardTest {

  //method
  @Test
  void testCase_forIpOrDomainAndPortAndSecurityLevelForConnections() {

    //parameter definition
    final var domain = "nolix.ch";
    final var port = 443;
    final var securiyMode = SecurityMode.SSL;

    //execution
    final var result = ServerTarget.forIpOrDomainAndPortAndSecurityLevelForConnections(domain, port, securiyMode);

    //verification
    expect(result.getIpOrDomain()).isEqualTo(domain);
    expect(result.getPort()).isEqualTo(port);
    expect(result.getSecurityLevelForConnections()).isEqualTo(securiyMode);
  }
}
