//package declaration
package ch.nolix.coreTest.zetaEndPointTest;

//own imports
import ch.nolix.core.interfaces.IZetaReceiver;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.util.Validator;
import ch.nolix.core.zetaEndPoint.IZetaEndPointTaker;
import ch.nolix.core.zetaEndPoint.Server;
import ch.nolix.core.zetaEndPoint.ZetaEndPoint;
import ch.nolix.core.zetaTest.ZetaTest;

//test class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public final class ZetaEndPointTest extends ZetaTest {

	//test method
	public void test_constructor() throws InterruptedException {
		
		//test parameter
		final int port = 50000;
				
		//setup
		AlphaEndPointTakerMock alphaEndPointTakerMock = new AlphaEndPointTakerMock();
		new Server(port, alphaEndPointTakerMock);
		Thread.sleep(200);
		
		//execution
		new ZetaEndPoint(port);
		Thread.sleep(200);
		
		//verification
		expectThat(alphaEndPointTakerMock.hasLastAlphaEndPoint());
		expectThat(alphaEndPointTakerMock.getLastAlphaEndPoint().isNotAborted());
		
		//TODO: Stop alpha end point listener.
	}
	
	//test method
	public void test_send() throws InterruptedException {
		
		//test parameter
		final int port = 51000;
				
		//setup
		AlphaEndPointTakerMock alphaEndPointTakerMock = new AlphaEndPointTakerMock();
		new Server(port, alphaEndPointTakerMock);
		Thread.sleep(200);
		
		//execution
		final ZetaEndPoint alphaEndPoint = new ZetaEndPoint(port);
		String response = alphaEndPoint.sendMessageAndGetReply("test");
		Thread.sleep(200);
		
		//verification
		expectThat(alphaEndPointTakerMock.hasLastAlphaEndPoint());
		expectThat(alphaEndPointTakerMock.getLastAlphaEndPoint().isNotAborted());
		expectThat(response).equals("ok");
		
		//TODO: Stop alpha end point listener.
	}
	
	//mock class
	/**
	 * An alpha end point taker mock is a mock of an alpha end point taker.
	 */
	private static final class AlphaEndPointTakerMock implements IZetaEndPointTaker {

		//optional attribute
		private ZetaEndPoint lastAlphaEndPoint;

		//method
		/**
		 * @return the last alpha end point of this alpha end point taker mock
		 * @throws UnexistingAttributeException if this alpha end point taker mock has no last alpha end point
		 */
		public ZetaEndPoint getLastAlphaEndPoint() {
			
			if (!hasLastAlphaEndPoint()) {
				throw new UnexistingAttributeException(this, "last alpha end point");
			}
			
			return lastAlphaEndPoint;
		}
		
		//method
		/**
		 * @return true if this alpha end point taker mock has a last alpha end point
		 */
		public boolean hasLastAlphaEndPoint() {
			return (lastAlphaEndPoint != null);
		}
		
		//method
		/**
		 * Lets this alpha end point taker mock take the given alpha end point.
		 * 
		 * @param alphaEndPoint
		 * @throws Exception if the given alpha end point is null
		 */
		public void takeAlphaEndPoint(final ZetaEndPoint alphaEndPoint) {

			Validator.throwExceptionIfValueIsNull("alpha end point", alphaEndPoint);
			
			alphaEndPoint.setReceiver(new ZetaReceiverMock());
			lastAlphaEndPoint = alphaEndPoint;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	//mock class
	/**
	 * A zeta receiver mock is a mock of a zeta receiver.
	 */
	private static final class ZetaReceiverMock implements IZetaReceiver {

		//method
		/**
		 * Lets this zeta receiver mock receive the given message.
		 */
		public String receiveMessageAndGetReply(final String message) {
			return "ok";
		}
	}
}
