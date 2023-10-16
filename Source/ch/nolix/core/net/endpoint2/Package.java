//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
/**
 * A package in end point 3 is a package with a message role.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
final class Package extends ch.nolix.core.net.messaging.IndexedPackage<String> {

  //attribute
  private final MessageRole messageRole;

  //static method
  /**
   * @param string
   * @return a new zeta package the given string represents.
   */
  public static Package createPackageFromString(final String string) {
    return new Package(
        Integer.parseInt(string.substring(0, 8)),
        MessageRole.fromPrefix(string.charAt(8)),
        string.substring(9));
  }

  //constructor
  /**
   * Creates a new zeta package with the given index and message and the role the
   * given message has.
   * 
   * @param index
   * @param messageRole
   * @param message
   * @throws ArgumentIsNullException if the given message role is null.
   * @throws ArgumentIsNullException if the given message is null.
   */
  public Package(final int index, final MessageRole messageRole, final String message) {

    //Calls constructor of the base class.
    super(index, message);

    //Asserts that the given message role is not null.
    GlobalValidator.assertThat(messageRole).isOfType(MessageRole.class);

    //Sets the message role of thsis zeta package.
    this.messageRole = messageRole;
  }

  //method
  /**
   * @return the role of the message of this zeta package.
   */
  public MessageRole getMessageRole() {
    return messageRole;
  }

  //method
  /**
   * @return a {@link String} representation of this zeta package.
   */
  @Override
  public String toString() {
    return String.format("%08d%c%s", getIndex(), getMessageRole().getPrefix(), getStoredContent());
  }
}
