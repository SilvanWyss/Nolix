//package declaration
package ch.nolix.core.net.target;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.targetapi.IApplicationTarget;

//class
public class ApplicationTarget extends ServerTarget implements IApplicationTarget {
	
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
	protected ApplicationTarget(final String ipOrAddressName, final int port, final String applicationName) {
		
		super(ipOrAddressName, port);
		
		Validator.assertThat(applicationName).thatIsNamed("application name").isNotBlank();
		
		this.applicationName = applicationName;
	}
	
	//method
	@Override
	public final String getApplicationName() {
		return applicationName;
	}

}
