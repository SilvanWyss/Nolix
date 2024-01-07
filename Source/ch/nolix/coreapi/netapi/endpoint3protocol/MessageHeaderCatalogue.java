//package declaration
package ch.nolix.coreapi.netapi.endpoint3protocol;

//class
/**
 * Of the {@link MessageHeaderCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class MessageHeaderCatalogue {

  //constant
  public static final String COMMANDS_HEADER = "Commands";

  //constant
  public static final String DONE_HEADER = "Done";

  //constant
  public static final String ERROR_HEADER = "Error";

  //constant
  public static final String MULTI_DATA_HEADER = "MultiData";

  //constant
  public static final String MULTI_DATA_REQUEST_HEADER = "MultiDataRequest";

  //constructor
  /**
   * Prevents that an instance of the {@link MessageHeaderCatalogue} can be
   * created.
   */
  private MessageHeaderCatalogue() {
  }
}
