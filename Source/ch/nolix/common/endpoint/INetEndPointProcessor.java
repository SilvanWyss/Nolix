//package declaration
package ch.nolix.common.endpoint;

//interface
interface INetEndPointProcessor {
	
	//method
	default void sendRawMessage(final char rawMessage) {
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method declaration
	void sendRawMessage(final String rawMessage);
}
