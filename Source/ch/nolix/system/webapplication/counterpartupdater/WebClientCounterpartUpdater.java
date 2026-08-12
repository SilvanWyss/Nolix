/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class WebClientCounterpartUpdater {
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  private final BooleanSupplier openStateRequestable;

  private final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner;

  private WebClientCounterpartUpdater(
    final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {
    Validator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    Validator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientCounterpartUpdater forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientCounterpartUpdater(counterpartRunner, openStateRequester);
  }

  public void updateCounterpartFromWebGui(final IWebGui<?> webGui) {
    webGui.applyStyleIfHasStyle();

    final var updateCommands = UPDATE_COMMAND_CREATOR.createUpdateCommandsForWebGui(webGui);

    if (openStateRequestable.getAsBoolean()) {
      counterpartRunner.accept(updateCommands);
    }
  }

}
