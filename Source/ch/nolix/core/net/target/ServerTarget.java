//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;

//class
public class ServerTarget implements IServerTarget {
	
	//static method
	public static ServerTarget forIpOrAddressNameAndPortAndSecurityLevelForConnections(
		final String ipOrAddressName,
		final int port,
		final SecurityLevel securityLevelForConnections
	) {
		return new ServerTarget(ipOrAddressName, port, securityLevelForConnections);
	}
	
	//attribute
	private final String ipOrAddressName;
	
	//attribute
	private final int port;
	
	//attribute
	private final SecurityLevel securityLevelForConnections;
	
	//constructor
	protected ServerTarget(
		final String ipOrAddressName,
		final int port,
		final SecurityLevel securityLevelForConnections
	) {
		
		GlobalValidator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		GlobalValidator.assertThat(port).thatIsNamed(LowerCaseCatalogue.PORT).isPort();
		
		GlobalValidator
		.assertThat(securityLevelForConnections)
		.thatIsNamed("security level for connections")
		.isNotNull();
				
		this.ipOrAddressName = ipOrAddressName;
		this.port = port;
		this.securityLevelForConnections = securityLevelForConnections;
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
	public final SecurityLevel getSecurityLevelForConnections() {
		return securityLevelForConnections;
	}
	
	//method
	@Override
	public String toUrl() {
		return
		switch (getSecurityLevelForConnections()) {
			case UNSECURE ->
				toHttpUrl();
			case SECURE ->
				toHttpsUrl();
		};
	}
	
	//method
	private String toHttpsUrl() {
		
		if (getPort() == PortCatalogue.HTTPS) {
			return String.format("https://%s", getIpOrAddressName());
		}
		
		return String.format("https://%s:%s", getIpOrAddressName(), getPort());
	}
	
	//method
	private String toHttpUrl() {
		
		if (getPort() == PortCatalogue.HTTP) {
			return String.format("http://%s", getIpOrAddressName());
		}
		
		return String.format("http://%s:%s", getIpOrAddressName(), getPort());
	}
}
