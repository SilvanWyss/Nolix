//package declaration
package ch.nolix.core.usercontrol;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

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
		
		Validator.assertThat(loginName).thatIsNamed(LowerCaseCatalogue.LOGIN_NAME).isNotBlank();
		Validator.assertThat(password).thatIsNamed(LowerCaseCatalogue.PASSWORD).isNotBlank();
		
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
