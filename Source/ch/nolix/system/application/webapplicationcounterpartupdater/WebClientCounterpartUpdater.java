//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//Java imports
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClientCounterpartUpdater {

  //constant
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  //attribute
  private final BooleanSupplier openStateRequestable;

  //attribute
  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  //constructor
  private WebClientCounterpartUpdater(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {

    GlobalValidator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  //static method
  public static WebClientCounterpartUpdater forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientCounterpartUpdater(counterpartRunner, openStateRequester);
  }

  //method
  public void updateCounterpartFromWebGui(final IWebGui<?> webGui) {

    webGui.applyStyleIfHasStyle();

    final var updateCommands = createUpdateCommandsFromWebGui(webGui);

    if (openStateRequestable.getAsBoolean()) {
      counterpartRunner.accept(updateCommands);
    }
  }

  //method
  private IContainer<ChainedNode> createUpdateCommandsFromWebGui(final IWebGui<?> webGui) {
    return ImmutableList.withElement(
      UPDATE_COMMAND_CREATOR.createSetTitleCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetIconCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui));
  }
}
