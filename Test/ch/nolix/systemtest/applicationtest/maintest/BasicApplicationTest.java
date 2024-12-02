package ch.nolix.systemtest.applicationtest.maintest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.BasicApplication;
import ch.nolix.system.application.maintestutil.TestSession;
import ch.nolix.system.application.webapplication.WebClient;

final class BasicApplicationTest extends StandardTest {

  @Test
  void testCase_withNameAndInitialSessionClassAndContext() {

    //setup
    final var applicationService = new VoidObject();

    //execution
    @SuppressWarnings("unchecked")
    final var result = BasicApplication.withNameAndInitialSessionClassAndContext(
      "My application",
      TestSession.withClientClass(WebClient.class).getClass(),
      applicationService);

    //verification
    expect(result.getApplicationName()).isEqualTo("My application");
    expectNot(result.hasNameAddendum());
    expect(result.getStoredApplicationService()).is(applicationService);
    expectNot(result.hasClientConnected());
  }
}
