/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class WebClientCounterpartUpdater {
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  private final BooleanSupplier openStateRequestable;

  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  private WebClientCounterpartUpdater(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {
    Validator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    Validator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientCounterpartUpdater forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientCounterpartUpdater(counterpartRunner, openStateRequester);
  }

  public void updateCounterpartFromWebGui(final IWebGui<?> webGui) {
    webGui.applyStyleIfHasStyle();

    final var updateCommands = createUpdateCommandsFromWebGui(webGui);

    if (openStateRequestable.getAsBoolean()) {
      counterpartRunner.accept(updateCommands);
    }
  }

  private IContainer<IChainedNode> createUpdateCommandsFromWebGui(final IWebGui<?> webGui) {
    return ImmutableList.withElements(
      UPDATE_COMMAND_CREATOR.createSetTitleCommandForWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetIconCommandForWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandForWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetCssCommandForWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandForWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandForWebGui(webGui));
  }
}
