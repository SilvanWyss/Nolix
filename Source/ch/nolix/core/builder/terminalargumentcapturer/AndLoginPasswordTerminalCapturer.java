//package declaration
package ch.nolix.core.builder.terminalargumentcapturer;

//own imports
import ch.nolix.core.builder.base.TerminalArgumentCapturer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class AndLoginPasswordTerminalCapturer<O> extends TerminalArgumentCapturer<String, O> {
	
	//method
	public O andLoginPassword(final String loginPassword) {
		
		GlobalValidator.assertThat(loginPassword).thatIsNamed(LowerCaseCatalogue.LOGIN_PASSWORD).isNotBlank();
		
		return setArgumentAndBuild(loginPassword);
	}
	
	//method
	public String getLoginPassword() {
		return getRefArgument();
	}
}
