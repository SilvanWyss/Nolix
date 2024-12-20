package ch.nolix.techtest.serverdashboardtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.LocalServer;
import ch.nolix.tech.serverdashboard.WebApplicationExtractor;

final class WebApplicationExtractorTest extends StandardTest {

  @Test
  void testCase_whenTheGivenServerDoesNotContainWebApplications() {

    //setup
    final var server = new LocalServer();
    final var testUnit = new WebApplicationExtractor();

    //setup verification
    expect(server.isEmpty());

    //execution
    final var result = testUnit.getStoredWebApplicationsOfServer(server);

    //verification
    expect(result).isEmpty();
  }
}
