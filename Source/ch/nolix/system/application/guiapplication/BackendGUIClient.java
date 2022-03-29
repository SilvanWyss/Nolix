//package declaration
package ch.nolix.system.application.guiapplication;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.application.baseguiapplication.BaseBackendGUIClient;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <AC> is the type of the context of the parent {@link Application} of a {@link BackendGUIClient}.
 */
public final class BackendGUIClient<AC> extends BaseBackendGUIClient<BackendGUIClient<AC>, AC> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Node getDataFromHere(final ChainedNode request) {
		throw new InvalidArgumentException(LowerCaseCatalogue.REQUEST, request, "is not valid");
	}
}
