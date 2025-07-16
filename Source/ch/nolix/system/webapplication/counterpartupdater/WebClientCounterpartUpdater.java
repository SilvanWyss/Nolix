package ch.nolix.system.webapplication.counterpartupdater;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

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
    return ImmutableList.withElement(
      UPDATE_COMMAND_CREATOR.createSetTitleCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetIconCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui));
  }
}
