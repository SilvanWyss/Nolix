/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.base;

import ch.nolix.base.document.chainednode.ImmutableChainedNode;
import ch.nolix.systemapi.webapplication.basewebclientprotocol.RequestProtocol;

/**
 * @author Silvan Wyss
 */
public final class RequestCreator {
  private RequestCreator() {
  }

  public static ImmutableChainedNode createGetCookieValueRequestForCookieName(final String cookieName) {
    return //
    ImmutableChainedNode.withHeaderAndChildNode(
      RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME,
      ImmutableChainedNode.withHeader(cookieName));
  }

  public static ImmutableChainedNode createGetTextFromClipboardRequest() {
    return ImmutableChainedNode.withHeader(RequestProtocol.GET_TEXT_FROM_CLIPBOARD);
  }

  public static ImmutableChainedNode createGetUrlParameterValueRequestForUrlParameterName(final String urlParameterName) {
    return //
    ImmutableChainedNode.withHeaderAndChildNode(
      RequestProtocol.GET_URL_PARARAMETER_VALUE,
      ImmutableChainedNode.withHeader(urlParameterName));
  }
}
