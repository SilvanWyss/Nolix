//package declaration
package ch.nolix.system.application.basewebapplication;

//Java imports
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;
import ch.nolix.system.application.basewebapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.basewebapplicationprotocol.ObjectProtocol;

//class
final class BaseBackendWebClientCommandCreator {
	
	//constant
	public static final BaseBackendWebClientCommandCreator INSTANCE = new BaseBackendWebClientCommandCreator();
	
	//constructor
	private BaseBackendWebClientCommandCreator() {}
	
	//method
	public ChainedNode createDeleteCookieByNameCommand(final String cookieName) {
		return
		ChainedNode.withHeaderAndChildNode(
			CommandProtocol.DELETE_COOKIE_BY_NAME,
			ChainedNode.withHeader(cookieName)
		);
	}
	
	//method
	public ChainedNode createOpenNewTabCommand(final String pURL) {
		return
		ChainedNode.withHeaderAndChildNode(
			CommandProtocol.OPEN_NEW_TAB,
			ChainedNode.withHeaderAndChildNode(
				ObjectProtocol.URL,
				ChainedNode.withHeader(pURL)
			)
		);
	}
	
	//method
	public ChainedNode createRedirectCommand(final IApplicationInstanceTarget applicationInstanceTarget) {
		return
		ChainedNode.withHeaderAndChildNode(
			CommandProtocol.REDIRECT,
			ChainedNode.withHeader(applicationInstanceTarget.toURL())
		);
	}
	
	//method
	public ChainedNode createSaveFileCommand(final byte[] bytes) {
		return
		ChainedNode.withHeaderAndChildNodesFromNodes(
			CommandProtocol.SAVE_FILE,
			Node.withHeader(new String(bytes, StandardCharsets.UTF_8))
		);
	}
	
	//method
	public ChainedNode createSetOrAddCookieCommandForCookieWithNameAndValue(final String name, final String value) {
		return
		ChainedNode.withHeaderAndChildNodes(
			CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE,
			ImmutableList.withElements(
				ChainedNode.withHeader(name),
				ChainedNode.withHeader(value)
			)
		);
	}
}
