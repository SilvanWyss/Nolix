package ch.nolix.applicationtest.serverdashboardtest.frontendtest.maintest;

import org.junit.jupiter.api.Test;

import ch.nolix.application.serverdashboard.frontend.main.ServerDashboardApplication;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.LocalServer;

final class ServerDashboardApplicationTest extends StandardTest {

  @Test
  void testCase_getApplicationName() {

    //setup
    final var server = new LocalServer();
    final var testUnit = ServerDashboardApplication.forServer(server);

    //execution
    final var result = testUnit.getApplicationName();

    //verification
    expect(result).isEqualTo("Server Dashboard");
  }
}
