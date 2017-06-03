//package declaration
package ch.nolix.coreTest.endPoint3Test;

//own imports
import ch.nolix.core.basic.NamedElement;
import ch.nolix.core.communicationInterfaces.IReplier;
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.endPoint3.NetServer;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.test2.Test;
import ch.nolix.core.validator.Validator;

//test class
/**
 * This class is a test class for the end point class.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public final class EndPointTest extends Test {

	//test method
	public void test_constructor() throws InterruptedException {
		
		//test parameter
		final int port = 50000;
		final String reply = "ok";
				
		//setup
		final EndPointTakerMock endPointTakerMock = new EndPointTakerMock("Target", new ReplierMock(reply));
		final NetServer netServer = new NetServer(port);
		netServer.addEndPointTaker(endPointTakerMock);
		Thread.sleep(200);
		
		//execution
		new NetEndPoint(port, "Target");
		Thread.sleep(200);
		
		//verification
		expectThat(endPointTakerMock.hasLastEndPoint());
		expectThat(endPointTakerMock.getLastEndPoint().isRunning());
		
		//cleanup
		netServer.abort();
	}
	
	//test method
	public void test_send() throws InterruptedException {
		
		//test parameter
		final int port = 50000;
		final String reply = "ok";
				
		//setup
		final EndPointTakerMock endPointTakerMock = new EndPointTakerMock("Target", new ReplierMock(reply));
		final NetServer netServer = new NetServer(port);
		netServer.addEndPointTaker(endPointTakerMock);
		Thread.sleep(200);
		
		//execution
		final NetEndPoint netEndPoint = new NetEndPoint(port, "Target");
		final String received_reply = netEndPoint.sendAndGetReply("test");
		Thread.sleep(200);
		
		//verification
		expectThat(endPointTakerMock.hasLastEndPoint());
		expectThat(endPointTakerMock.getLastEndPoint().isRunning());
		expectThat(received_reply).equals(reply);
		
		//cleanup
		netServer.abort();
	}
	
	//mock class
	/**
	 * An alpha end point taker mock is a mock of an alpha end point taker.
	 */
	private static final class EndPointTakerMock extends NamedElement implements IEndPointTaker {

		final IReplier replier;
		
		public EndPointTakerMock(final String name, final IReplier replier) {
			
			super(name);
			
			this.replier = replier;
		}
		
		//optional attribute
		private EndPoint lastEndPoint;

		//method
		/**
		 * @return the last alpha end point of this alpha end point taker mock
		 * @throws UnexistingAttributeException if this alpha end point taker mock has no last alpha end point
		 */
		public EndPoint getLastEndPoint() {
			
			if (!hasLastEndPoint()) {
				throw new UnexistingAttributeException(this, "last end point");
			}
			
			return lastEndPoint;
		}
		
		//method
		/**
		 * @return true if this alpha end point taker mock has a last alpha end point
		 */
		public boolean hasLastEndPoint() {
			return (lastEndPoint != null);
		}
		
		//method
		/**
		 * Lets this alpha end point taker mock take the given alpha end point.
		 * 
		 * @param alphaEndPoint
		 * @throws Exception if the given alpha end point is null
		 */
		public void takeEndPoint(final EndPoint endPoint) {

			Validator.throwExceptionIfValueIsNull("end point", endPoint);
			
			endPoint.setReplier(replier);
			lastEndPoint = endPoint;
		}
	}
	
	//mock class
	/**
	 * A zeta receiver mock is a mock of a zeta receiver.
	 */
	private static final class ReplierMock implements IReplier {

		//attribute
		private final String reply;
		
		public ReplierMock(final String reply) {
			this.reply = reply;
		}
		
		//method
		/**
		 * Lets this zeta receiver mock receive the given message.
		 */
		public String getReply(final String message) {
			return reply;
		}
	}
}
