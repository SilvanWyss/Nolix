//package declaration
package ch.nolix.system.client.base;

//class
/**
* @author Silvan Wyss
* @data 2022-03-18
* @param <FC> is the type of a {@link FrontendClient}.
*/
public abstract class FrontendClient<FC extends FrontendClient<FC>> extends Client<FC> {
	
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
}
