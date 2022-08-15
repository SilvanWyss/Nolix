//package declaration
package ch.nolix.system.application.basewebapplication;

//Java imports
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.netapi.targetuniversalapi.IApplicationTarget;
import ch.nolix.system.application.basewebapplicationprotocol.CommandProtocol;
import ch.nolix.system.application.basewebapplicationprotocol.ObjectProtocol;

//class
final class BaseBackendWebClientCommandCreator {
	
	//static attribute
	public static final BaseBackendWebClientCommandCreator INSTANCE = new BaseBackendWebClientCommandCreator();
	
	//constructor
	private BaseBackendWebClientCommandCreator() {}
	
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
	public ChainedNode createRedirectCommand(final IApplicationTarget applicationTarget) {
		return
		ChainedNode.withHeaderAndChildNode(
			CommandProtocol.REDIRECT,
			ChainedNode.withHeader(applicationTarget.toURL())
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
}
