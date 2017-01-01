/*
 * file:	AlphaEndPointTest
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.commonTest.alphaEndPointTest;

//own imports
import ch.nolix.common.zetaEndPoint.AlphaEndPoint;
import ch.nolix.common.zetaEndPoint.AlphaEndPointListener;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
public final class AlphaEndPointTest extends ZetaTest {

	//test method
	public void testConstructor() throws InterruptedException {
		
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
	}
	
	//test method
	public void testSend() throws InterruptedException {
		
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
	}
}
