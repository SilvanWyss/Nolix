//package declaration
package ch.nolix.core.builder.argumentcapturer;

import ch.nolix.core.builder.main.ArgumentCapturer;
import ch.nolix.core.builder.main.BaseArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public class AndLoginPasswordCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//constructor
	public AndLoginPasswordCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final NAC andLoginPassword(final String loginPassword) {
		
		GlobalValidator.assertThat(loginPassword).thatIsNamed(LowerCaseCatalogue.LOGIN_PASSWORD).isNotBlank();
		
		return setArgumentAndGetRefNextArgumentCapturer(loginPassword);
	}
	
	//method
	public final String getLoginPassword() {
		return getRefArgument();
	}
}
