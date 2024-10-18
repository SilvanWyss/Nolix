package ch.nolix.coreapi.netapi.endpoint3protocol;

/**
 * Of the {@link MessageHeaderCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class MessageHeaderCatalogue {

  public static final String COMMANDS_HEADER = "Commands";

  public static final String DONE_HEADER = "Done";

  public static final String ERROR_HEADER = "Error";

  public static final String MULTI_DATA_HEADER = "MultiData";

  public static final String MULTI_DATA_REQUEST_HEADER = "MultiDataRequest";

  /**
   * Prevents that an instance of the {@link MessageHeaderCatalogue} can be
   * created.
   */
  private MessageHeaderCatalogue() {
  }
}
