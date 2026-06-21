/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.counterpartupdater;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class WebClientPartialCounterpartUpdater {
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  private final BooleanSupplier openStateRequestable;

  private final Consumer<ExtendedIterable<? extends IChainedNode>> counterpartRunner;

  private WebClientPartialCounterpartUpdater(
    final Consumer<ExtendedIterable<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {
    Validator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    Validator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientPartialCounterpartUpdater forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<ExtendedIterable<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientPartialCounterpartUpdater(counterpartRunner, openStateRequester);
  }

  public void updateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {
    final ExtendedIterable<IControl<?, ?>> controls = ImmutableList.withElements(control);

    updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  public void updateControlsOnCounterpart(
    final ExtendedIterable<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    Validator.assertThat(controls).thatIsNamed(PluralLowerCaseVariableCatalog.CONTROLS).isNotEmpty();

    if (updateConstellationOrStyle) {
      final var webGui = controls.getStoredFirst().getStoredParentGui();
      webGui.applyStyleIfHasStyle();
    }

    final var updateCommands = //
    UPDATE_COMMAND_CREATOR.createUpdateCommandsForControls(controls, updateConstellationOrStyle);

    if (openStateRequestable.getAsBoolean()) {
      counterpartRunner.accept(updateCommands);
    }
  }
}
