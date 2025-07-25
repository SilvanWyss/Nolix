package ch.nolix.coretest.net.target;

import org.junit.jupiter.api.Test;

import ch.nolix.core.net.target.ServerTarget;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;

final class ServerTargetTest extends StandardTest {

  @Test
  void testCase_forIpOrDomainAndPortAndSecurityModeForConnections() {

    //parameter definition
    final var domain = "nolix.ch";
    final var port = 443;
    final var securiyMode = SecurityMode.SSL;

    //execution
    final var result = ServerTarget.forIpOrDomainAndPortAndSecurityModeForConnections(domain, port, securiyMode);

    //verification
    expect(result.getIpOrDomain()).isEqualTo(domain);
    expect(result.getPort()).isEqualTo(port);
    expect(result.getSecurityModeForConnection()).isEqualTo(securiyMode);
  }
}
