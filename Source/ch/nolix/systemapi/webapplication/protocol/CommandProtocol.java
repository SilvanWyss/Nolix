/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.protocol;

/**
 * @author Silvan Wyss
 */
public final class CommandProtocol {
  public static final String SET_CSS = "SetCSS";

  public static final String SET_EVENT_FUNCTIONS = "SetEventFunctions";

  public static final String SET_HTML_ELEMENT = "SetHTMLElement";

  public static final String SET_ICON = "SetIcon";

  public static final String SET_ROOT_HTML_ELEMENT = "SetRootHTMLElement";

  public static final String SET_TITLE = "SetTitle";

  public static final String SET_USER_INPUT_FUNCTIONS = "SetUserInputFunctions";

  public static final String SET_USER_INPUTS = "SetUserInputs";

  private CommandProtocol() {
  }
}
