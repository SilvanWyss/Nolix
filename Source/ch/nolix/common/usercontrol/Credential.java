//package declaration
package ch.nolix.common.usercontrol;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

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
