//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

//Java imports
import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class WebClientPartialCounterpartUpdater {

  //constant
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  //attribute
  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  //constructor
  private WebClientPartialCounterpartUpdater(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner) {

    GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.counterpartRunner = counterpartRunner;
  }

  //static method
  public static WebClientPartialCounterpartUpdater forCounterpartRunner(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner) {
    return new WebClientPartialCounterpartUpdater(counterpartRunner);
  }

  //method
  public void updateControlOnCounterpart(final IControl<?, ?> control) {

    final var webGui = control.getStoredParentGui();
    webGui.applyStyleIfHasStyle();

    final var updateCommands = createUpdateCommandsForControl(control);

    counterpartRunner.accept(updateCommands);
  }

  //method
  public void updateControlsOnCounterpart(final IContainer<IControl<?, ?>> controls) {

    GlobalValidator.assertThat(controls).thatIsNamed("controls").isNotEmpty();

    final var webGui = controls.getStoredFirst().getStoredParentGui();
    webGui.applyStyleIfHasStyle();

    final var updateCommands = createUpdateCommandsForControls(controls);
    counterpartRunner.accept(updateCommands);
  }

  //method
  private IContainer<ChainedNode> createUpdateCommandsForControl(final IControl<?, ?> control) {

    final var webGui = control.getStoredParentGui();

    return ImmutableList.withElement(
      UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandFromControl(control),
      UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui));
  }

  //method
  private IContainer<ChainedNode> createUpdateCommandsForControls(final IContainer<IControl<?, ?>> controls) {

    GlobalValidator.assertThat(controls).thatIsNamed("controls").isNotEmpty();

    final var webGui = controls.getStoredFirst().getStoredParentGui();

    final var updatedCommands = new LinkedList<ChainedNode>();

    updatedCommands.addAtEnd(controls.to(UPDATE_COMMAND_CREATOR::createSetRootHtmlElementCommandFromControl));

    updatedCommands.addAtEnd(
      UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
      UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui));

    return updatedCommands;
  }
}
