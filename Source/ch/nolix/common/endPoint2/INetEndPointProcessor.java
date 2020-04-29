//package declaration
package ch.nolix.common.endPoint2;

//interface
interface INetEndPointProcessor {
	
	//method
	public default void sendRawMessage(final char rawMessage) {
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method declaration
	public abstract void sendRawMessage(final String rawMessage);
}
