//package declaration
package ch.nolix.system.application.webapplication;

//Java imports
import java.util.Base64;
import java.util.Optional;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.application.basewebapplication.BaseWebClient;
import ch.nolix.system.application.webapplicationrefreshqueue.WebClientRefreshQueue;
import ch.nolix.systemapi.applicationapi.componentapi.IComponent;
import ch.nolix.systemapi.applicationapi.webapplicationprotocol.CommandProtocol;
import ch.nolix.systemapi.applicationapi.webapplicationprotocol.ControlCommandProtocol;
import ch.nolix.systemapi.applicationapi.webapplicationprotocol.ObjectProtocol;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IUploader;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClient<AC> extends BaseWebClient<WebClient<AC>, AC> {

  //constant
  private static final WebClientHtmlEventExecutor WEB_CLIENT_HTML_EVENT_EXECUTOR = new WebClientHtmlEventExecutor();

  //attribute
  private final WebClientRefreshQueue refreshQueue = WebClientRefreshQueue
    .forCounterpartRunnerAndOpenStateRequestable(this::runOnCounterpart, this::isOpen);

  //method
  @Override
  protected INode<?> getDataFromHere(final IChainedNode request) {
    throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.REQUEST, request);
  }

  //method
  @Override
  protected void runHereOnBaseBackendWebClient(final IChainedNode command) {
    switch (command.getHeader()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case ObjectProtocol.GUI:
        runGuiCommand(command.getNextNode());
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.COMMAND, command);
    }
  }

  //method
  void internalUpdateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {
    refreshQueue.updateControlOnCounterpart(control, updateConstellationOrStyle);
  }

  //method
  void internalUpdateControlsOnCounterpart(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    refreshQueue.updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  //method
  void internalUpdateCounterpartFromWebGui(final IWebGui<?> webGui, final boolean updateConstellationOrStyle) {
    refreshQueue.updateWebGuiOfCounterpart(webGui, updateConstellationOrStyle);
  }

  //method
  void internalRunOnCounterpart(final IContainer<? extends IChainedNode> updateCommands) {
    runOnCounterpart(updateCommands);
  }

  //method
  private Optional<IComponent> getOptionalStoredParentComponentOfControl(final IControl<?, ?> control) {

    if (control.isLinkedToAnObject()
    && control.getStoredLinkedObjects().getStoredFirst() instanceof IComponent component) {
      return Optional.of(component);
    }

    if (control.belongsToControl()) {
      return getOptionalStoredParentComponentOfControl(control.getStoredParentControl());
    }

    return Optional.empty();
  }

  //method
  private void refreshCounterpartGui() {
    ((WebClientSession<AC>) getStoredCurrentSession()).refresh();
  }

  //method
  private void runCommandOnControl(final IControl<?, ?> control, final IChainedNode command) {
    switch (command.getHeader()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case ControlCommandProtocol.RUN_HTML_EVENT:
        runHtmlEventCommand(control, command);
        break;
      case ControlCommandProtocol.SET_FILE:

        final var uploader = (IUploader) control;

        final var fileString = command.getSingleChildNodeHeader();

        //Important: The received fileString is a Base 64 encoded string.
        final var bytes = Base64.getDecoder().decode(fileString.substring(fileString.indexOf(',') + 1));

        uploader.internalSetFile(bytes);

        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.COMMAND, command);
    }
  }

  //method
  private void runControlCommand(final IChainedNode guiCommand) {

    final var command = guiCommand.getNextNode();
    final var internalControlId = guiCommand.getSingleChildNodeHeader();
    final var webClientSession = (WebClientSession<AC>) getStoredCurrentSession();
    final var gui = webClientSession.getStoredGui();
    final var controls = gui.getStoredControls();
    final var control = controls.getOptionalStoredFirst(c -> c.hasInternalId(internalControlId));

    //The Control could be removed on the server in the meanwhile.
    if (control.isPresent()) {
      runCommandOnControl(control.get(), command);
    }
  }

  //method
  private void runGuiCommand(final IChainedNode guiCommand) {
    switch (guiCommand.getHeader()) {
      case ObjectProtocol.CONTROL_BY_INTERNAL_ID:
        runControlCommand(guiCommand);
        break;
      case CommandProtocol.SET_USER_INPUTS:
        runSetUserInputsCommand(guiCommand);
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument("GUI command", guiCommand);
    }
  }

  private void runHtmlEventCommand(final IControl<?, ?> triggeredControl, final IChainedNode htmlEventCommand) {

    final var htmlEvent = htmlEventCommand.getSingleChildNodeHeader();

    WEB_CLIENT_HTML_EVENT_EXECUTOR.runHtmlEventOfTriggeredControlAndUpdateAccordingly(
      triggeredControl,
      htmlEvent,
      this::isOpen,
      this::refreshCounterpartGui,
      this::updateCounterpartWhenOpen);
  }

  //method
  private void runSetUserInputsCommand(final IChainedNode guiCommand) {

    final var webClientSession = (WebClientSession<AC>) getStoredCurrentSession();
    final var gui = webClientSession.getStoredGui();
    final var controls = gui.getStoredControls();

    for (final var p : guiCommand.getChildNodes()) {

      final var internalControlId = p.getChildNodeAt1BasedIndex(1).getHeader();
      final var userInput = p.getChildNodeAt1BasedIndex(2).getHeaderOrEmptyString();
      final var control = controls.getOptionalStoredFirst(c -> c.hasInternalId(internalControlId));

      //The Control could be removed on the server in the meanwhile.
      if (control.isPresent()) {
        control.get().setUserInput(userInput);
      }
    }
  }

  //method
  private void updateCounterpartWhenOpen(final IComponent component) {
    switch (component.getRefreshBehavior()) {
      case DO_NOT_REFRESH_ANYTHING:
        break;
      case REFRESH_GUI:
        refreshCounterpartGui();
        break;
      case REFRESH_SELF:
        ((WebClientSession<AC>) getStoredCurrentSession()).updateControlOnCounterpart(component, true);
        break;
    }
  }

  //method
  private void updateCounterpartWhenOpen(final IControl<?, ?> control) {

    final var component = getOptionalStoredParentComponentOfControl(control);

    if (component.isEmpty()) {
      refreshCounterpartGui();
    } else {
      updateCounterpartWhenOpen(component.get());
    }
  }
}
