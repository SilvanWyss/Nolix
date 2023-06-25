//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetuniversalapi.IApplicationInstanceTarget;

//class
public class ApplicationInstanceTarget extends ServerTarget implements IApplicationInstanceTarget {
	
	//static method
	public static ApplicationInstanceTarget
	forIpOrAddressNameAndPortAndApplicationInstanceNameAndSecurityLevelForConnections(
		final String ipOrAddressName,
		final int port,
		final String applicationName,
		final SecurityLevel securityLevelForConnections
	) {
		return new ApplicationInstanceTarget(ipOrAddressName, port, applicationName, securityLevelForConnections);
	}
	
	//attribute
	private final String applicationInstanceName;
	
	//constructor
	protected ApplicationInstanceTarget(
		final String ipOrAddressName,
		final int port,
		final String applicationName,
		final SecurityLevel securityLevelForConnections
	) {
		
		super(ipOrAddressName, port, securityLevelForConnections);
		
		GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		
		this.applicationInstanceName = applicationName;
	}
	
	//method
	@Override
	public final String getApplicationInstanceName() {
		return applicationInstanceName;
	}
	
	//method
	@Override
	public String toUrl() {
		return (super.toUrl() + "?app=" + getApplicationInstanceName().replace(" ", "_"));
	}
}
