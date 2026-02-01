/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.main;

import java.util.Base64;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.webapplication.base.AbstractWebClient;
import ch.nolix.system.webapplication.refreshqueue.WebClientRefreshQueue;
import ch.nolix.systemapi.webapplication.component.IComponent;
import ch.nolix.systemapi.webapplication.protocol.CommandProtocol;
import ch.nolix.systemapi.webapplication.protocol.ControlCommandProtocol;
import ch.nolix.systemapi.webapplication.protocol.ObjectProtocol;
import ch.nolix.systemapi.webatomiccontrol.uploader.IUploader;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link WebClient}.
 */
public final class WebClient<C> extends AbstractWebClient<WebClient<C>, C> {
  private final WebClientRefreshQueue refreshQueue = //
  WebClientRefreshQueue.forCounterpartRunnerAndOpenStateRequestable(this::runOnCounterpart, this::isOpen);

  @Override
  protected INode<?> getDataFromHere(final IChainedNode request) {
    throw InvalidArgumentException.forArgumentAndArgumentName(request, LowerCaseVariableCatalog.REQUEST);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void runHereOnBaseBackendWebClient(final IChainedNode command) {
    switch (command.getHeader()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case ObjectProtocol.GUI:
        runGuiCommand(command.getNextNode());
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(command, LowerCaseVariableCatalog.COMMAND);
    }
  }

  void internalUpdateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {
    refreshQueue.updateControlOnCounterpart(control, updateConstellationOrStyle);
  }

  void internalUpdateControlsOnCounterpart(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    refreshQueue.updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  void internalUpdateCounterpartFromWebGui(final IWebGui<?> webGui, final boolean updateConstellationOrStyle) {
    refreshQueue.updateWebGuiOfCounterpart(webGui, updateConstellationOrStyle);
  }

  void internalRunOnCounterpart(final IContainer<? extends IChainedNode> updateCommands) {
    runOnCounterpart(updateCommands);
  }

  private void refreshCounterpartGui() {
    ((WebClientSession<C>) getStoredCurrentSession()).refresh();
  }

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
        throw InvalidArgumentException.forArgumentAndArgumentName(command, LowerCaseVariableCatalog.COMMAND);
    }
  }

  private void runControlCommand(final IChainedNode guiCommand) {
    final var command = guiCommand.getNextNode();
    final var internalControlId = guiCommand.getSingleChildNodeHeader();
    final var webClientSession = (WebClientSession<C>) getStoredCurrentSession();
    final var gui = webClientSession.getStoredGui();
    final var controls = gui.getStoredControls();
    final var control = controls.getOptionalStoredFirst(c -> c.hasInternalId(internalControlId));

    //The Control could be removed on the server in the meanwhile.
    if (control.isPresent()) {
      runCommandOnControl(control.get(), command);
    }
  }

  private void runGuiCommand(final IChainedNode guiCommand) {
    switch (guiCommand.getHeader()) {
      case ObjectProtocol.CONTROL_BY_INTERNAL_ID:
        runControlCommand(guiCommand);
        break;
      case CommandProtocol.SET_USER_INPUTS:
        runSetUserInputsCommand(guiCommand);
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(guiCommand, "GUI command");
    }
  }

  private void runHtmlEventCommand(final IControl<?, ?> triggeredControl, final IChainedNode htmlEventCommand) {
    final var htmlEvent = htmlEventCommand.getSingleChildNodeHeader();

    WebClientHtmlEventExecutor.runHtmlEventOfTriggeredControlAndUpdateAccordingly(
      triggeredControl,
      htmlEvent,
      this::isOpen,
      this::refreshCounterpartGui,
      this::updateCounterpartWhenOpen);
  }

  private void runSetUserInputsCommand(final IChainedNode guiCommand) {
    final var webClientSession = (WebClientSession<C>) getStoredCurrentSession();
    final var gui = webClientSession.getStoredGui();
    final var controls = gui.getStoredControls();

    for (final var p : guiCommand.getChildNodes()) {
      final var internalControlId = p.getChildNodeAtOneBasedIndex(1).getHeader();
      final var userInput = p.getChildNodeAtOneBasedIndex(2).getHeaderOrEmptyString();
      final var control = controls.getOptionalStoredFirst(c -> c.hasInternalId(internalControlId));

      //The Control could be removed on the server in the meanwhile.
      if (control.isPresent()) {
        control.get().setUserInput(userInput);
      }
    }
  }

  private void updateCounterpartWhenOpen(final IComponent component) {
    final var refreshBehavior = component.getRefreshTrigger();

    switch (refreshBehavior) {
      case DO_NOT_REFRESH:
        break;
      case REFRESH_GUI:
        refreshCounterpartGui();
        break;
      case REFRESH_COMPONENT:
        ((WebClientSession<C>) getStoredCurrentSession()).updateControlOnCounterpart(component, true);
        break;
      default:
        throw InvalidArgumentException.forArgument(refreshBehavior);
    }
  }

  private void updateCounterpartWhenOpen(final IControl<?, ?> control) {
    final var component = ControlHelper.getOptionalStoredParentComponentOfControl(control);

    if (component.isEmpty()) {
      refreshCounterpartGui();
    } else {
      updateCounterpartWhenOpen(component.get());
    }
  }
}
