//package declaration
package ch.nolix.commonTest.endPointTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.endPoint.NetServer;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;

//test class
/**
 * A {@link NetServerTest} is a test for {@link NetServer}.
 * A {@link NetServerTest} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 40
 */
public final class NetServerTest extends Test {
	
	//method
	@TestCase
	public void testCase_close() {
		
		//test parameter
		final var port = 50000;
		
		//execution part 1
		final var netServer	= new NetServer(port, ep -> {});
		Sequencer.waitForMilliseconds(500);
		netServer.close();
		
		//execution part 2
		final var netServer2 = new NetServer(port, ep -> {});
		Sequencer.waitForMilliseconds(500);
		netServer2.close();
		
		//verification
		expect(netServer.isClosed());
		expect(netServer2.isClosed());
	}
}
