//package declaration
package ch.nolix.core.builder.withargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv4Catalogue;

//class
public class WithIpOrAddressNameCapturer<N> extends ArgumentCapturer<String, N> {
	
	//constructor
	public WithIpOrAddressNameCapturer(final N nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final String getIpOrAddressName() {
		return getStoredArgument();
	}
	
	//method
	public final N withIpOrAddressName(final String ipOrAddressName) {
		
		GlobalValidator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		
		return setArgumentAndGetNext(ipOrAddressName);
	}
	
	//method
	public final N withLocalAddress() {
		return withIpOrAddressName(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
}
