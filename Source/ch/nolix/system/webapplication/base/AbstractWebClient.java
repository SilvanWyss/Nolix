package ch.nolix.system.webapplication.base;

import java.util.Optional;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;
import ch.nolix.coreapi.web.cookie.ICookieManager;
import ch.nolix.system.application.main.AbstractBackendClient;
import ch.nolix.system.webapplication.protocol.ClientCommandCreator;
import ch.nolix.systemapi.application.basewebapplicationprotocol.CommandProtocol;

public abstract class AbstractWebClient<C extends AbstractWebClient<C, S>, S>
extends AbstractBackendClient<C, S>
implements ICookieManager {

  private static final ClientCommandCreator BACKEND_WEB_CLIENT_COMMAND_CREATOR = //
  new ClientCommandCreator();

  private static final RequestCreator BACKEND_WEB_CLIENT_REQUEST_CREATOR = //
  new RequestCreator();

  private final FileReader fileReader = FileReader.forBackendWebClient(this);

  @Override
  public final void deleteCookieByName(final String name) {

    final var deleteCookieCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createDeleteCookieCommand(name);

    runOnCounterpart(deleteCookieCommand);
  }

  @Override
  public final Optional<String> getOptionalCookieValueByCookieName(final String cookieName) {

    final var getCookieValueRequest = BACKEND_WEB_CLIENT_REQUEST_CREATOR
      .createGetCookieValueRequestForCookieName(cookieName);

    return getOptionalCookieValueByCookieNameFromData(getDataFromCounterpart(getCookieValueRequest));
  }

  public final Optional<String> getOptionalUrlParameterValueByUrlParameterName(final String urlParameterName) {

    final var getUrlParameterValueRequest = BACKEND_WEB_CLIENT_REQUEST_CREATOR
      .createGetUrlParameterValueRequestForUrlParameterName(urlParameterName);

    final var urlParameterValueReply = getDataFromCounterpart(getUrlParameterValueRequest);

    return urlParameterValueReply.getOptionalHeader();
  }

  @Override
  public final void setOrAddCookieWithNameAndValue(final String name, final String value) {

    final var addOrSetCookieCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createAddOrSetCookieCommand(name, value);

    runOnCounterpart(addOrSetCookieCommand);
  }

  @Override
  protected final void runHere(final IChainedNode command) {
    switch (command.getHeader()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case CommandProtocol.RECEIVE_OPTIONAL_FILE:
        receiveOptionalFileFromCounterpart(command);
        break;
      default:
        runHereOnBaseBackendWebClient(command);
    }
  }

  protected abstract void runHereOnBaseBackendWebClient(IChainedNode command);

  final IContainer<byte[]> internalGetFilesFromClipboardOfCounterpart() {
    throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "getFilesFromClipboard");
  }

  final String internalGetTextFromClipboardOfCounterpart() {

    final var getTextFromClipBoardCommand = BACKEND_WEB_CLIENT_REQUEST_CREATOR.createGetTextFromClipboardRequest();

    return getDataFromCounterpart(getTextFromClipBoardCommand).getHeader();
  }

  final void internalOpenNewTabOnCounterpartWithUrl(final String url) {

    final var openNewTabCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createOpenNewTabCommand(url);

    runOnCounterpart(openNewTabCommand);
  }

  final Optional<byte[]> internalReadOptionalFileFromCounterpart() {
    return fileReader.readOptionalFileFromCounterpart();
  }

  final void internalRedirectCounterpartTo(final IApplicationInstanceTarget applicationInstanceTarget) {

    final var redirectCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createRedirectCommand(applicationInstanceTarget);

    runOnCounterpart(redirectCommand);
  }

  final void internalRedirectCounterpartToUrl(final String url) {

    final var redirectToUrlCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createRedirectCommand(url);

    runOnCounterpart(redirectToUrlCommand);
  }

  final void internalRunOnCounterpart(final ChainedNode command) {
    runOnCounterpart(command);
  }

  final void internalSaveFileOnCounterpart(final byte[] bytes) {

    final var saveFileCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createSaveFileCommand(bytes);

    runOnCounterpart(saveFileCommand);
  }

  final void internalWriteTextToClipboardOfCounterpart(final String text) {

    final var writeTextToClipboardCommand = BACKEND_WEB_CLIENT_COMMAND_CREATOR.createWriteTextToClipBoardCommand(text);

    runOnCounterpart(writeTextToClipboardCommand);
  }

  private Optional<String> getOptionalCookieValueByCookieNameFromData(final INode<?> data) {
    return data.getOptionalHeader();
  }

  private void receiveOptionalFileFromCounterpart(final IChainedNode receiveOptionalFileCommand) {
    fileReader.receiveOptionalFileFromCounterpart(receiveOptionalFileCommand);
  }
}
