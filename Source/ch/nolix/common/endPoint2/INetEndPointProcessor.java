//package declaration
package ch.nolix.common.endPoint2;

//package-visible interface
interface INetEndPointProcessor {
	
	//method declaration
	public abstract NetEndPointCounterpartType getCounterpartType();
	
	//method
	public default void sendRawMessage(final char rawMessage) {
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//method declaration
	public abstract void sendRawMessage(final String rawMessage);
}
