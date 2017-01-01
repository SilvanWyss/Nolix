/*
 * file:	AlphaEndPointTakerMock
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	60
 */

//package declaration
package ch.nolix.commonTest.alphaEndPointTest;

//own imports
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.util.Validator;
import ch.nolix.common.zetaEndPoint.AlphaEndPoint;
import ch.nolix.common.zetaEndPoint.IAlphaEndPointTaker;

//mock class
/**
 * An alpha end point taker mock is a mock of an alpha end point taker.
 */
public final class AlphaEndPointTakerMock
implements IAlphaEndPointTaker {

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
		
		alphaEndPoint.setAlphaReceiver(new ReceiverMock());
		lastAlphaEndPoint = alphaEndPoint;
	}
}
