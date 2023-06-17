//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;

//class
public final class ApplicationTarget extends ServerTarget implements IApplicationInstanceTarget {
	
	//static method
	public static ApplicationTarget forIpOrAddressNameAndPortAndApplicationNameAndSecurityLevelForConnections(
		final String ipOrAddressName,
		final int port,
		final String applicationName,
		final SecurityLevel securityLevelForConnections
	) {
		return new ApplicationTarget(ipOrAddressName, port, applicationName, securityLevelForConnections);
	}
	
	//attribute
	private final String applicationName;
	
	//constructor
	private ApplicationTarget(
		final String ipOrAddressName,
		final int port,
		final String applicationName,
		final SecurityLevel securityLevelForConnections
	) {
		
		super(ipOrAddressName, port, securityLevelForConnections);
		
		GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		
		this.applicationName = applicationName;
	}
	
	//method
	@Override
	public String getApplicationInstanceName() {
		return applicationName;
	}
	
	//method
	@Override
	public String toURL() {
		return (super.toURL() + "?app=" + getApplicationInstanceName().replace(" ", "_"));
	}
}
