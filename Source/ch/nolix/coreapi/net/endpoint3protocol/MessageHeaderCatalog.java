/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint3protocol;

/**
 * Of the {@link MessageHeaderCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class MessageHeaderCatalog {
  public static final String COMMANDS_HEADER = "Commands";

  public static final String DONE_HEADER = "Done";

  public static final String ERROR_HEADER = "Error";

  public static final String MULTI_DATA_HEADER = "MultiData";

  public static final String MULTI_DATA_REQUEST_HEADER = "MultiDataRequest";

  /**
   * Prevents that an instance of the {@link MessageHeaderCatalog} can be created.
   */
  private MessageHeaderCatalog() {
  }
}
