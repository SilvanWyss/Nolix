//package declaration
package ch.nolix.core.programcontrol.usercontrol;

//class
public final class LoginNameMediator {
	
	//attribute
	private final String loginName;
	
	//constructor
	LoginNameMediator(final String loginName) {
		this.loginName = loginName;
	}
	
	//method
	public Credential andPassword(final String password) {
		return new Credential(loginName, password);
	}
}
