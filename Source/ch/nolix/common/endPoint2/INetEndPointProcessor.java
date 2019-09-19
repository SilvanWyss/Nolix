//package declaration
package ch.nolix.common.endPoint2;

//package-visible interface
interface INetEndPointProcessor {
	
	//abstract method
	public abstract NetEndPointCounterpartType getCounterpartType();
	
	//default method
	public default void sendRawMessage(final char rawMessage) {
		sendRawMessage(String.valueOf(rawMessage));
	}
	
	//abstract method
	public abstract void sendRawMessage(final String rawMessage);
}
