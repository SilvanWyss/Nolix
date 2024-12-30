package ch.nolix.applicationtest.serverdashboardtest.backendtest.datatooltest;

import org.junit.jupiter.api.Test;

import ch.nolix.application.serverdashboard.backend.tool.WebApplicationExtractor;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.LocalServer;

final class WebApplicationExtractorTest extends StandardTest {

  @Test
  void testCase_whenTheGivenServerDoesNotContainWebApplications() {

    //setup
    final var server = new LocalServer();
    final var testUnit = new WebApplicationExtractor();

    //setup verification
    expect(server.isEmpty()).isTrue();

    //execution
    final var result = testUnit.getStoredWebApplicationsOfServer(server);

    //verification
    expect(result).isEmpty();
  }
}
