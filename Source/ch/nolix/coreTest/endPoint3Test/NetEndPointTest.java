//package declaration
package ch.nolix.coreTest.endPoint3Test;

//own imports
import ch.nolix.core.communicationAPI.IReplier;
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.endPoint3.NetServer;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.test.Test;

//test class
/**
 * This class is a test class for the end point class.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public final class NetEndPointTest extends Test {

	//test case
	public void testCase_creation() throws InterruptedException {
		
		//test parameters
		final int port = 50000;
		final String reply = "ok";
				
		//setup
		final NetServer netServer = new NetServer(port);
		final EndPointTakerMock endPointTakerMock
		= new EndPointTakerMock(new ReplierMock(reply));
		netServer.addEndPointTaker(endPointTakerMock);
		
		//execution
		new NetEndPoint(port, endPointTakerMock.getName());
		Thread.sleep(200);
		
		//verification
		expect(endPointTakerMock.hasLastEndPoint());
		expect(endPointTakerMock.getLastEndPoint().isOpen());
		
		//cleanup
		netServer.close();
	}
	
	//test case
	public void testCase_send() throws InterruptedException {
		
		//test parameters
		final int port = 50000;
		final String reply = "ok";
		
		//setup
		final NetServer netServer = new NetServer(port);
		final EndPointTakerMock endPointTakerMock = new EndPointTakerMock(new ReplierMock(reply));
		netServer.addEndPointTaker(endPointTakerMock);
		
		//execution
		final NetEndPoint netEndPoint = new NetEndPoint(port, endPointTakerMock.getName());
		final String received_reply = netEndPoint.sendAndGetReply("test");
		Thread.sleep(200);
		
		//verification
		expect(endPointTakerMock.hasLastEndPoint());
		expect(endPointTakerMock.getLastEndPoint().isOpen());
		expect(received_reply).isEqualTo(reply);
		
		//cleanup
		netServer.close();
	}
	
	//mock class
	private class EndPointTakerMock implements IEndPointTaker {

		//name
		private static final String NAME = "Target";
		
		//attribute
		final IReplier replier;
		
		//constructor
		public EndPointTakerMock(final IReplier replier) {
			this.replier = replier;
		}
		
		//optional attribute
		private EndPoint lastEndPoint;

		//method
		public EndPoint getLastEndPoint() {
			
			if (!hasLastEndPoint()) {
				throw new ArgumentMissesAttributeException(this, "last end point");
			}
			
			return lastEndPoint;
		}
		
		//method
		@Override
		public String getName() {
			return NAME;
		}
		
		//method
		public boolean hasLastEndPoint() {
			return (lastEndPoint != null);
		}
		
		//method
		@Override
		public void takeEndPoint(final EndPoint endPoint) {
			endPoint.setReplier(replier);
			lastEndPoint = endPoint;
		}
	}
	
	//mock class
	private class ReplierMock implements IReplier {

		//attribute
		private final String reply;
		
		//constructor
		public ReplierMock(final String reply) {
			this.reply = reply;
		}
		
		//method
		@Override
		public String getReply(final String message) {
			return reply;
		}
	}
}
