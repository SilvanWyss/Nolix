//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.application.main.BasicApplication;
import ch.nolix.system.application.main.LocalServer;
import ch.nolix.system.application.maintestutil.TestSession;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class BasicApplicationOnServerTest extends Test {
	
	//method
	@TestCase
	public void testCase_isAddedToServer() {
		
		try (final var localServer = new LocalServer()) {
			
			//setup applicationContext
			final var applicationContext = new VoidObject();
			
			//setup testUnit
			@SuppressWarnings("unchecked")
			final var testUnit =
			BasicApplication.withNameAndInitialSessionClassAndContext(
				"My application",
				TestSession.withClientClass(WebClient.class).getClass(),
				applicationContext				
			);
			
			//execution
			localServer.addApplicationWithNameAddendum(testUnit, "Instance1");
			
			//verification
			expect(testUnit.getApplicationName()).isEqualTo("My application");
			expect(testUnit.getInstanceName()).isEqualTo("My application Instance1");
			expect(testUnit.hasNameAddendum());
		}
	}
}
