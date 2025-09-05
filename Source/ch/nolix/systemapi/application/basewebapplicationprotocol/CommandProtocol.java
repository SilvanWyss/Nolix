package ch.nolix.systemapi.application.basewebapplicationprotocol;

public final class CommandProtocol {
  public static final String DELETE_COOKIE_BY_NAME = "DeleteCookieByName";

  public static final String OPEN_NEW_TAB = "OpenNewTab";

  public static final String RECEIVE_OPTIONAL_FILE = "ReceiveOptionalFile";

  public static final String REDIRECT = "Redirect";

  public static final String SAVE_FILE = "SaveFile";

  public static final String SEND_OPTIONAL_FILE = "SendOptionalFile";

  public static final String SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE = "SetOrAddCookieWithNameAndValue";

  public static final String WRITE_TEXT_TO_CLIPBOARD = "WriteTextToClipBoard";

  private CommandProtocol() {
  }
}
