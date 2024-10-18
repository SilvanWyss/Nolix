package ch.nolix.system.application.webapplicationcounterpartupdater;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public final class WebClientPartialCounterpartUpdater {

  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  private final BooleanSupplier openStateRequestable;

  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  private WebClientPartialCounterpartUpdater(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {

    GlobalValidator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientPartialCounterpartUpdater forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientPartialCounterpartUpdater(counterpartRunner, openStateRequester);
  }

  public void updateControlOnCounterpart(final IControl<?, ?> control, final boolean updateConstellationOrStyle) {

    final IContainer<IControl<?, ?>> controls = ImmutableList.withElement(control);

    updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  public void updateControlsOnCounterpart(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {

    GlobalValidator.assertThat(controls).thatIsNamed("controls").isNotEmpty();

    if (updateConstellationOrStyle) {
      final var webGui = controls.getStoredFirst().getStoredParentGui();
      webGui.applyStyleIfHasStyle();
    }

    final var updateCommands = createUpdateCommandsForControls(controls, updateConstellationOrStyle);

    if (openStateRequestable.getAsBoolean()) {
      counterpartRunner.accept(updateCommands);
    }
  }

  private IContainer<IChainedNode> createUpdateCommandsForControls(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {

    GlobalValidator.assertThat(controls).thatIsNamed("controls").isNotEmpty();

    final var webGui = controls.getStoredFirst().getStoredParentGui();

    final ILinkedList<IChainedNode> updatedCommands = LinkedList.createEmpty();

    updatedCommands.addAtEnd(controls.to(UPDATE_COMMAND_CREATOR::createSetRootHtmlElementCommandFromControl));

    if (updateConstellationOrStyle) {
      updatedCommands.addAtEnd(
        UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
        UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
        UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui));
    }

    return updatedCommands;
  }
}
