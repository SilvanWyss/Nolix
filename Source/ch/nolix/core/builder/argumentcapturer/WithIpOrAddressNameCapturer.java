//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.constant.IPv4Catalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public class WithIpOrAddressNameCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//constructor
	public WithIpOrAddressNameCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final String getIpOrAddressName() {
		return getRefArgument();
	}
	
	//method
	public final NAC withIpOrAddressName(final String ipOrAddressName) {
		
		GlobalValidator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		
		return setArgumentAndGetRefNextArgumentCapturer(ipOrAddressName);
	}
	
	//method
	public final NAC withLocalAddress() {
		return withIpOrAddressName(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
}
