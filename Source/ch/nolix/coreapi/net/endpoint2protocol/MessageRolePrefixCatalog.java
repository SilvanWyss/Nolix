/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint2protocol;

/**
 * @author Silvan Wyss
 */
public final class MessageRolePrefixCatalog {
  public static final char TARGET_APPLICATION_PREFIX = 'T';

  public static final char RESPONSE_EXPECTING_MESSAGE_PREFIX = 'M';

  public static final char SUCCESS_RESPONSE_PREFIX = 'R';

  public static final char ERROR_RESPONSE_PREFIX = 'E';

  private MessageRolePrefixCatalog() {
  }
}
