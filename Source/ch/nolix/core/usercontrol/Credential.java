//package declaration
package ch.nolix.core.usercontrol;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
public final class Credential {
	
	//attributes
	private final String loginName;
	private final String password;
	
	//static method	
	public static LoginNameMediator withLoginName(final String loginName) {
		return new LoginNameMediator(loginName);
	}
	
	//constructor
	Credential(final String loginName, final String password) {
		
		GlobalValidator.assertThat(loginName).thatIsNamed(LowerCaseCatalogue.LOGIN_NAME).isNotBlank();
		GlobalValidator.assertThat(password).thatIsNamed(LowerCaseCatalogue.PASSWORD).isNotBlank();
		
		this.loginName = loginName;
		this.password = password;
	}
	
	//method
	public String getLoginName() {
		return loginName;
	}
	
	//method
	public String getPassword() {
		return password;
	}
}
