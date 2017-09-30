//package declaration
package ch.nolix.core.endPoint4;

//own import
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A package in end point 3 is a package with a message role.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 70
 */
final class Package extends ch.nolix.core.util.Package<String> {
	
	//attribute
	private final MessageRole messageRole;
	
	//static method
	/**
	 * @param string
	 * @return a new zeta package the given string represents.
	 */
	public static Package createZetaPackageFromString(final String string) {
		return new Package(
			Integer.parseInt(string.substring(0, 8)),
			MessageRole.createMessageRole(string.charAt(8)),
			string.substring(9)
		);
	}
	
	//constructor
	/**
	 * Creates new zeta package with the given index and message and the role the given message has.
	 * 
	 * @param index
	 * @param messageRole
	 * @param message
	 * @throws NullArgumentException if the given message role is null.
	 * @throws NullArgumentExcetpion if the givne message is null.
	 */
	public Package(final int index, final MessageRole messageRole, final String message) {
		
		//Calls constructor of the base class.
		super(index, message);
		
		//Checks if the given message role is not null.
		Validator.suppose(messageRole).thatIsInstanceOf(MessageRole.class).isNotNull();
		
		//Sets the message role of thsis zeta package.
		this.messageRole = messageRole;
	}
	
	//method
	/**
	 * @return the role of the message of this zeta package.
	 */
	public final MessageRole getMessageRole() {
		return messageRole;
	}
	
	//method
	/**
	 * @return a string representation of this zeta package.
	 */
	public String toString() {
		return String.format("%08d%c%s", getIndex(), getMessageRole().getPrefix(), getRefContent());
	}
}
