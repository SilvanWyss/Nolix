/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class WebClientPartialCounterpartUpdater {
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  private final BooleanSupplier openStateRequestable;

  private final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner;

  private WebClientPartialCounterpartUpdater(
    final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {
    Validator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    Validator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientPartialCounterpartUpdater forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientPartialCounterpartUpdater(counterpartRunner, openStateRequester);
  }

  public void updateControlOnCounterpart(final Control<?, ?> control, final boolean updateConstellationOrStyle) {
    final ExtendedIterable<Control<?, ?>> controls = ImmutableList.withElements(control);

    updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  public void updateControlsOnCounterpart(
    final ExtendedIterable<Control<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    Validator.assertThat(controls).thatIsNamed(PluralLowerCaseVariableNameCatalog.CONTROLS).isNotEmpty();

    if (updateConstellationOrStyle) {
      final var webGui = controls.getStoredFirstNonNull().getStoredParentGui();
      webGui.applyStyleIfHasStyle();
    }

    final var updateCommands = //
    UPDATE_COMMAND_CREATOR.createUpdateCommandsForControls(controls, updateConstellationOrStyle);

    if (openStateRequestable.getAsBoolean()) {
      counterpartRunner.accept(updateCommands);
    }
  }
}
