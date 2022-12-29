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
	public ChainedNode createGetCookieValueRequestForCookieName(final String cookieName) {
		return
		ChainedNode.withHeaderAndChildNode(
			RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME,
			ChainedNode.withHeader(cookieName)
		);
	}
	
	//method
	public ChainedNode createGetTextFromClipboardRequest() {
		return ChainedNode.withHeader(RequestProtocol.GET_TEXT_FROM_CLIPBOARD);
	}
}
