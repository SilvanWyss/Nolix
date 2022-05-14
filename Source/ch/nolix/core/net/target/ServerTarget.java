//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PortCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.targetapi.IServerTarget;

//class
public final class ServerTarget implements IServerTarget {
	
	//static method
	public static ServerTarget forIpOrAddressNameAndPort(final String ipOrAddressName, final int port) {
		return new ServerTarget(ipOrAddressName, port);
	}
	
	//attribute
	private final String ipOrAddressName;
	
	//attribute
	private final int port;
	
	//constructor
	private ServerTarget(final String ipOrAddressName, final int port) {
		
		Validator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		
		Validator.assertThat(port)
		.thatIsNamed(LowerCaseCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		this.ipOrAddressName = ipOrAddressName;
		this.port = port;
	}
	
	//method
	@Override
	public String getIpOrAddressName() {
		return ipOrAddressName;
	}
	
	//method
	@Override
	public int getPort() {
		return port;
	}
}
