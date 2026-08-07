/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.messageandreplyserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.net.messageandreplyserverprotocol.MessageRole;

/**
 * A package in end point 3 is a package with a message role.
 * 
 * @author Silvan Wyss
 */
final class Package extends ch.nolix.base.net.messaging.IndexedPackage<String> {
  private final MessageRole messageRole;

  /**
   * Creates a new {@link Package} with the given index and messageRole and
   * message.
   * 
   * @param index
   * @param messageRole
   * @param message
   * @throws RuntimeException if the given message role is null
   * @throws RuntimeException if the given message is null
   */
  private Package(final int index, final MessageRole messageRole, final String message) {
    super(index, message);

    Validator.assertThat(messageRole).isOfType(MessageRole.class);

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
   * 
   * @param index
   * @param messageRole
   * @param message
   * @return a new {@link Package} with the given index and messageRole and
   *         message
   * @throws RuntimeException if the given message role is null
   * @throws RuntimeException if the given message is null
   */
  public static Package withIndexAndMessageRoleAndMessage(
    final int index,
    final MessageRole messageRole,
    final String message) {
    return new Package(index, messageRole, message);
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
