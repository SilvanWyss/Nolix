package ch.nolix.system.application.basewebapplication;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.systemapi.applicationapi.basewebapplicationprotocol.RequestProtocol;

final class RequestCreator {

  public ChainedNode createGetCookieValueRequestForCookieName(final String cookieName) {
    return ChainedNode.withHeaderAndChildNode(
      RequestProtocol.GET_COOKIE_VALUE_BY_COOKIE_NAME,
      ChainedNode.withHeader(cookieName));
  }

  public ChainedNode createGetTextFromClipboardRequest() {
    return ChainedNode.withHeader(RequestProtocol.GET_TEXT_FROM_CLIPBOARD);
  }

  public ChainedNode createGetUrlParameterValueRequestForUrlParameterName(final String urlParameterName) {
    return ChainedNode.withHeaderAndChildNode(
      RequestProtocol.GET_URL_PARARAMETER_VALUE,
      ChainedNode.withHeader(urlParameterName));
  }
}
