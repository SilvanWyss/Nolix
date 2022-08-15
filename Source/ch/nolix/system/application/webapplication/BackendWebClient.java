//package declaration
package ch.nolix.system.application.webapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.application.basewebapplication.BaseBackendWebClient;

//class
public final class BackendWebClient<AC> extends BaseBackendWebClient<BackendWebClient<AC>, AC> {
	
	//method
	@Override
	protected Node getDataFromHereFromBaseBackendWebClient(final ChainedNode request) {
		throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REQUEST, request);
	}
	
	//method
	@Override
	protected void runHereOnBaseBackendWebClient(final ChainedNode command) {
		throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.COMMAND, command);
	}
}
