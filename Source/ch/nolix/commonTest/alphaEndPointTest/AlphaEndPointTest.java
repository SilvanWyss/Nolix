//package declaration
package ch.nolix.commonTest.alphaEndPointTest;

//own imports
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.IZetaReceiver;
import ch.nolix.common.util.Validator;
import ch.nolix.common.zetaEndPoint.AlphaEndPoint;
import ch.nolix.common.zetaEndPoint.AlphaEndPointListener;
import ch.nolix.common.zetaEndPoint.IAlphaEndPointTaker;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public final class AlphaEndPointTest extends ZetaTest {

	//test method
	public void test_constructor() throws InterruptedException {
		
		//test parameter
		final int port = 50000;
				
		//setup
		AlphaEndPointTakerMock alphaEndPointTakerMock = new AlphaEndPointTakerMock();
		new AlphaEndPointListener(port, alphaEndPointTakerMock);
		Thread.sleep(200);
		
		//execution
		new AlphaEndPoint(port);
		Thread.sleep(200);
		
		//verification
		expectThat(alphaEndPointTakerMock.hasLastAlphaEndPoint());
		expectThat(alphaEndPointTakerMock.getLastAlphaEndPoint().isRunning());
		
		//TODO: Stop alpha end point listener.
	}
	
	//test method
	public void test_send() throws InterruptedException {
		
		//test parameter
		final int port = 51000;
				
		//setup
		AlphaEndPointTakerMock alphaEndPointTakerMock = new AlphaEndPointTakerMock();
		new AlphaEndPointListener(port, alphaEndPointTakerMock);
		Thread.sleep(200);
		
		//execution
		final AlphaEndPoint alphaEndPoint = new AlphaEndPoint(port);
		String response = alphaEndPoint.sendAndGetResponse("test");
		Thread.sleep(200);
		
		//verification
		expectThat(alphaEndPointTakerMock.hasLastAlphaEndPoint());
		expectThat(alphaEndPointTakerMock.getLastAlphaEndPoint().isRunning());
		expectThat(response).equals("ok");
		
		//TODO: Stop alpha end point listener.
	}
	
	//mock class
	/**
	 * An alpha end point taker mock is a mock of an alpha end point taker.
	 */
	private static final class AlphaEndPointTakerMock implements IAlphaEndPointTaker {

		//optional attribute
		private AlphaEndPoint lastAlphaEndPoint;

		//method
		/**
		 * @return the last alpha end point of this alpha end point taker mock
		 * @throws Exception if this alpha end point taker mock has no last alpha end point
		 */
		public AlphaEndPoint getLastAlphaEndPoint() {
			
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
		public void takeAlphaEndPoint(final AlphaEndPoint alphaEndPoint) {

			Validator.throwExceptionIfValueIsNull("alpha end point", alphaEndPoint);
			
			alphaEndPoint.setAlphaReceiver(new ZetaReceiverMock());
			lastAlphaEndPoint = alphaEndPoint;
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
