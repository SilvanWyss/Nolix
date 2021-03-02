//package declaration
package ch.nolix.common.localcomputer;

//own imports
import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.nolix.common.errorcontrol.exception.WrapperException;

//class
public final class LocalComputer {
	
	//static method
	public static String getLANIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (final UnknownHostException unknownHostException) {
			throw new WrapperException(unknownHostException);
		}
	}
	
	//visibility-reduced constructor
	private LocalComputer () {}
}
