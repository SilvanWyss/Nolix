//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.netapi.targetuniversalapi.IServerTarget;

//class
public class ServerTarget implements IServerTarget {
	
	//static method
	public static ServerTarget forIpOrAddressNameAndPort(final String ipOrAddressName, final int port) {
		return new ServerTarget(ipOrAddressName, port);
	}
	
	//attribute
	private final String ipOrAddressName;
	
	//attribute
	private final int port;
	
	//constructor
	protected ServerTarget(final String ipOrAddressName, final int port) {
		
		GlobalValidator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		
		GlobalValidator.assertThat(port)
		.thatIsNamed(LowerCaseCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		this.ipOrAddressName = ipOrAddressName;
		this.port = port;
	}
	
	//method
	@Override
	public final String getIpOrAddressName() {
		return ipOrAddressName;
	}
	
	//method
	@Override
	public final int getPort() {
		return port;
	}
	
	//method
	@Override
	public String toURL() {
		return (getIpOrAddressName() + ":" + getPort());	
	}
}
