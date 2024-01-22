//package declaration
package ch.nolix.system.application.basewebapplication;

//Java imports
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.programcontrolapi.targetapi.IApplicationInstanceTarget;
import ch.nolix.systemapi.applicationapi.basewebapplicationprotocol.CommandProtocol;
import ch.nolix.systemapi.applicationapi.basewebapplicationprotocol.ObjectProtocol;

//class
final class BaseWebClientCommandCreator {

  //method
  public ChainedNode createDeleteCookieByNameCommand(final String cookieName) {
    return ChainedNode.withHeaderAndChildNode(
      CommandProtocol.DELETE_COOKIE_BY_NAME,
      ChainedNode.withHeader(cookieName));
  }

  //method
  public ChainedNode createOpenNewTabCommand(final String url) {
    return ChainedNode.withHeaderAndChildNode(
      CommandProtocol.OPEN_NEW_TAB,
      ChainedNode.withHeaderAndChildNode(
        ObjectProtocol.URL,
        ChainedNode.withHeader(url)));
  }

  //method
  public ChainedNode createRedirectCommand(final IApplicationInstanceTarget applicationInstanceTarget) {
    return ChainedNode.withHeaderAndChildNode(
      CommandProtocol.REDIRECT,
      ChainedNode.withHeader(applicationInstanceTarget.toUrl()));
  }

  //method
  public ChainedNode createSaveFileCommand(final byte[] bytes) {
    return ChainedNode.withHeaderAndChildNodesFromNodes(
      CommandProtocol.SAVE_FILE,
      Node.withHeader(new String(bytes, StandardCharsets.UTF_8)));
  }

  //method
  public ChainedNode createSetOrAddCookieCommandForCookieWithNameAndValue(final String name, final String value) {
    return ChainedNode.withHeaderAndChildNodes(
      CommandProtocol.SET_OR_ADD_COOKIE_WITH_NAME_AND_VALUE,
      ImmutableList.withElement(
        ChainedNode.withHeader(name),
        ChainedNode.withHeader(value)));
  }

  //method
  public ChainedNode createWriteTextToClipBoardCommand(final String text) {
    return ChainedNode.withHeaderAndChildNode(CommandProtocol.WRITE_TEXT_TO_CLIPBOARD, ChainedNode.withHeader(text));
  }
}
