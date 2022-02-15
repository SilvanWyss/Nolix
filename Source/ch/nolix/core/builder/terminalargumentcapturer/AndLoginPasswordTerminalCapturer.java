//package declaration
package ch.nolix.core.builder.terminalargumentcapturer;

//own imports
import ch.nolix.core.builder.base.TerminalArgumentCapturer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class AndLoginPasswordTerminalCapturer<O> extends TerminalArgumentCapturer<String, O> {
	
	//method
	public O andLoginPassword(final String loginPassword) {
		
		Validator.assertThat(loginPassword).thatIsNamed(LowerCaseCatalogue.LOGIN_PASSWORD).isNotBlank();
		
		return setArgumentAndBuild(loginPassword);
	}
}
