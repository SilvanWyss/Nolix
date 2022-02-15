//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class AndLoginPasswordCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//constructor
	public AndLoginPasswordCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public NAC andLoginPassword(final String loginPassword) {
		
		Validator.assertThat(loginPassword).thatIsNamed(LowerCaseCatalogue.LOGIN_PASSWORD).isNotBlank();
		
		return setArgumentAndGetRefNextArgumentCapturer(loginPassword);
	}
}
