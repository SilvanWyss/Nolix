//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;

//class
public class ApplicationInstanceTarget extends ServerTarget implements IApplicationInstanceTarget {
	
	//static method
	public static ApplicationInstanceTarget
	forIpOrDomainAndPortAndApplicationInstanceNameAndSecurityLevelForConnections(
		final String ipOrDomain,
		final int port,
		final String applicationInstanceName,
		final SecurityLevel securityLevelForConnections
	) {
		return new ApplicationInstanceTarget(ipOrDomain, port, applicationInstanceName, securityLevelForConnections);
	}
	
	//attribute
	private final String applicationInstanceName;
	
	//constructor
	protected ApplicationInstanceTarget(
		final String ipOrDomain,
		final int port,
		final String applicationInstanceName,
		final SecurityLevel securityLevelForConnections
	) {
		
		super(ipOrDomain, port, securityLevelForConnections);
		
		GlobalValidator.assertThat(applicationInstanceName).thatIsNamed("application instance name").isNotBlank();
		
		this.applicationInstanceName = applicationInstanceName;
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
