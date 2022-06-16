//package declaration
package ch.nolix.core.builder.argumentcapturer;

//own imports
import ch.nolix.core.builder.base.ArgumentCapturer;
import ch.nolix.core.builder.base.BaseArgumentCapturer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public class UsingLoginNameCapturer<NAC extends BaseArgumentCapturer<?>> extends ArgumentCapturer<String, NAC> {
	
	//method
	public UsingLoginNameCapturer(final NAC nextArgumentCapturer) {
		super(nextArgumentCapturer);
	}
	
	//method
	public final NAC usingLoginName(final String loginName) {
		
		GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseCatalogue.LOGIN_NAME).isNotBlank();
		
		return setArgumentAndGetRefNextArgumentCapturer(loginName);
	}
	
	//method
	public final String getLoginName() {
		return getRefArgument();
	}
}
