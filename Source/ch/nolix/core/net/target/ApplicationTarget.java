//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.targetuniversalapi.IApplicationTarget;

//class
public final class ApplicationTarget extends ServerTarget implements IApplicationTarget {
	
	//static method
	public static ApplicationTarget forIpOrAddressNameAndPortAndApplicationName(
		final String ipOrAddressName,
		final int port,
		final String applicationName
	) {
		return new ApplicationTarget(ipOrAddressName, port, applicationName);
	}
	
	//attribute
	private final String applicationName;
	
	//constructor
	private ApplicationTarget(final String ipOrAddressName, final int port, final String applicationName) {
		
		super(ipOrAddressName, port);
		
		GlobalValidator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		
		this.applicationName = applicationName;
	}
	
	//method
	@Override
	public String getApplicationName() {
		return applicationName;
	}
	
	//method
	@Override
	public String toURL() {
		return (super.toURL() + "/" + getApplicationName().replace(" ", "_"));
	}
}
