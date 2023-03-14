//package declaration
package ch.nolix.core.net.endpoint2;

import ch.nolix.coreapi.netapi.endpoint2api.ISlot;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-27
 */
public final class LocalServer extends BaseServer {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedDefaultEndPointTaker(final ISlot defaultEndPointTaker) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteAddedEndPointTaker(final ISlot endPointTaker) {
		//Does nothing.
	}
}
