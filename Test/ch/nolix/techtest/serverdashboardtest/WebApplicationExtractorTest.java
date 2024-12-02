package ch.nolix.techtest.serverdashboardtest;

import org.junit.jupiter.api.Test;

import ch.nolix.application.serverdashboard.backend.datatool.WebApplicationExtractor;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.LocalServer;

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
