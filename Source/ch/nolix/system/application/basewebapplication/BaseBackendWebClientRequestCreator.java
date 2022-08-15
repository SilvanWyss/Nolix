//package declaration
package ch.nolix.system.application.basewebapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.system.application.basewebapplicationprotocol.RequestProtocol;

//class
final class BaseBackendWebClientRequestCreator {
	
	//static attribute
	public static final BaseBackendWebClientRequestCreator INSTANCE = new BaseBackendWebClientRequestCreator();
	
	//constructor
	private BaseBackendWebClientRequestCreator() {}
	
	//method
	public ChainedNode createGetTextFromClipboardRequest() {
		return ChainedNode.withHeader(RequestProtocol.GET_TEXT_FROM_CLIPBOARD);
	}
}
