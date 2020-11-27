//package declaration
package ch.nolix.common.endPoint2;

//interface
interface INetEndPointProcessor {
	
	//method
	default void sendRawMessage(final char rawMessage) {
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method declaration
	void sendRawMessage(final String rawMessage);
}
