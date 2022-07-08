//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.builder.main.BaseArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class AndPortCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<Integer, NAC> {
	
	//attribute
	private final int defaultPort;
	
	//constructor
	public AndPortCapturer(final int defaultPort, final NAC nextArgumentCapturer) {
		
		super(nextArgumentCapturer);
		
		GlobalValidator
		.assertThat(defaultPort)
		.thatIsNamed("default port")
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		this.defaultPort = defaultPort;
	}
	
	//method
	public final NAC andDefaultPort() {
		return andPort(defaultPort);
	}
	
	//method
	public final NAC andPort(final int port) {
		
		GlobalValidator
		.assertThat(port)
		.thatIsNamed(LowerCaseCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		return setArgumentAndGetRefNextArgumentCapturer(port);
	}
	
	//method
	public final int getPort() {
		return getRefArgument();
	}
}
