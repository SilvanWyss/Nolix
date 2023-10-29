//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.application.main.BasicApplication;
import ch.nolix.system.application.maintestutil.TestSession;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class BasicApplicationTest extends Test {

  //method
  @TestCase
  public void testCase_withNameAndInitialSessionClassAndContext() {

    //setup
    final var applicationContext = new VoidObject();

    //execution
    @SuppressWarnings("unchecked")
    final var result = BasicApplication.withNameAndInitialSessionClassAndContext(
      "My application",
      TestSession.withClientClass(WebClient.class).getClass(),
      applicationContext);

    //verification
    expect(result.getApplicationName()).isEqualTo("My application");
    expectNot(result.hasNameAddendum());
    expect(result.getStoredApplicationContext()).is(applicationContext);
    expectNot(result.hasClientConnected());
  }
}
