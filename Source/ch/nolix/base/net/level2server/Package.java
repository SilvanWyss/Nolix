/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level2server;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.net.level2serverprotocol.MessageRole;

/**
 * A package in end point 3 is a package with a message role.
 * 
 * @author Silvan Wyss
 */
final class Package extends ch.nolix.base.net.messaging.IndexedPackage<String> {
  private final MessageRole messageRole;

  /**
   * Creates a new zeta package with the given index and message and the role the
   * given message has.
   * 
   * @param index
   * @param messageRole
   * @param message
   * @throws RuntimeException if the given message role is null.
   * @throws RuntimeException if the given message is null.
   */
  public Package(final int index, final MessageRole messageRole, final String message) {
    //Calls constructor of the base class.
    super(index, message);

    //Asserts that the given message role is not null.
    Validator.assertThat(messageRole).isOfType(MessageRole.class);

    //Sets the message role of thsis zeta package.
    this.messageRole = messageRole;
  }

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

  /**
   * @return the role of the message of this zeta package.
   */
  public MessageRole getMessageRole() {
    return messageRole;
  }

  /**
   * @return a {@link String} representation of this zeta package.
   */
  @Override
  public String toString() {
    return String.format("%08d%c%s", getIndex(), getMessageRole().getPrefix(), getStoredContent());
  }
}
