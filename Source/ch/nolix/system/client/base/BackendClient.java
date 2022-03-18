//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint3.EndPoint;

//class
/**
 * @author Silvan Wyss
 * @data 2022-03-18
 * @param <BC> is the type of a {@link BackendClient}.
 */
public abstract class BackendClient<BC extends BackendClient<BC>> extends Client<BC> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isBackendClient() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isFrontendClient() {
		return false;
	}
	
	//method
	/**
	 * Sets the {@link EndPoint} of the current {@link BackendClient}.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given endPoint is null.
	 * @throws InvalidArgumentException if the current {@link Client} is already connected.
	 */
	protected final void setEndPoint(final EndPoint endPoint) {
		internalSetEndPoint(endPoint);
	}
}
