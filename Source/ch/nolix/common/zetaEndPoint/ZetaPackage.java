/*
 * file:	AdvancedPackage.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	80
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

//own imports
import ch.nolix.common.util.Package;
import ch.nolix.common.zetaValidator.ZetaValidator;

//package-visible class
/**
 * An advanced package is a package whose message has a specific role.
 */
final class ZetaPackage extends Package {
	
	//attribute
	private final MessageRole messageRole;
	
	//constructor
	/**
	 * Creates new advanced package with the given index, message role and message.
	 * 
	 * @param index
	 * @param messageRole
	 * @param message
	 * @throws Exception if:
	 * -The given message role is null.
	 * -The given message is null.
	 */
	public ZetaPackage(int index, MessageRole messageRole, String message) {
		
		//Calls constructor of the base class.
		super(index, message);
		
		//Checks the given message.
		ZetaValidator.supposeThat(messageRole).thatIsNamed("message role").isNotNull();
		
		this.messageRole = messageRole;
	}
	
	//constructor
	/**
	 * Creates new advanced package the given string represents.
	 * 
	 * @param string
	 * @throws Exception if the given string is not valid
	 */
	public ZetaPackage(String string) {
		
		//Calls other constructor.
		this(
			Integer.parseInt(string.substring(0, 8)),
			MessageRole.createMessageRole(string.charAt(8)),
			string.substring(9)
		);	
	}
	
	//method
	/**
	 * @return the role of the message of this advanced package
	 */
	public final MessageRole getMessageRole() {
		return messageRole;
	}
	
	//method
	/**
	 * @return a string representation of this advanced package
	 */
	public String toString() {
		return (String.format("%08d", getIndex()) + getMessageRole().getPrefix() + getMessage());
	}
}
