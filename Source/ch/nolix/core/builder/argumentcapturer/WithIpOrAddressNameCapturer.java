//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.constant.IPv4Catalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public class WithIpOrAddressNameCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//method
	public WithIpOrAddressNameCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public String getIpOrAddressName() {
		return getRefArgument();
	}
	
	//method
	public NAC withIpOrAddressName(final String ipOrAddressName) {
		
		Validator.assertThat(ipOrAddressName).thatIsNamed("ip or address name").isNotBlank();
		
		return setArgumentAndGetRefNextArgumentCapturer(ipOrAddressName);
	}
	
	//method
	public NAC withLocalAddress() {
		return withIpOrAddressName(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
}
