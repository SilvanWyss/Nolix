//package declaration
package ch.nolix.common.localComputer;

//own imports
import java.net.InetAddress;
import java.net.UnknownHostException;

//own import
import ch.nolix.common.wrapperException.WrapperException;

//class
public final class LocalComputer {
	
	//static method
	public static String getLANIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		}
		catch (final UnknownHostException unknownHostException) {
			throw new WrapperException(unknownHostException);
		}
	}
	
	//access-reducing constructor
	private LocalComputer () {}
}
