package ch.nolix.system.webapplication.base;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.systemapi.application.basewebapplicationprotocol.RequestProtocol;

/**
 * @author Silvan Wyss
 * @version 2022-08-15
 */
public final class RequestCreator {
  private RequestCreator() {
  }

  public static ChainedNode createGetCookieValueRequestForCookieName(final String cookieName) {
    return //
    ChainedNode.withHeaderAndChildNode(
      RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME,
      ChainedNode.withHeader(cookieName));
  }

  public static ChainedNode createGetTextFromClipboardRequest() {
    return ChainedNode.withHeader(RequestProtocol.GET_TEXT_FROM_CLIPBOARD);
  }

  public static ChainedNode createGetUrlParameterValueRequestForUrlParameterName(final String urlParameterName) {
    return //
    ChainedNode.withHeaderAndChildNode(
      RequestProtocol.GET_URL_PARARAMETER_VALUE,
      ChainedNode.withHeader(urlParameterName));
  }
}
