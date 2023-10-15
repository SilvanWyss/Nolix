//package declaration
package ch.nolix.system.application.webapplicationcounterpartupdater;

import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class WebClientPartialCounterpartUpdater {

  // constant
  private static final UpdateCommandCreator UPDATE_COMMAND_CREATOR = new UpdateCommandCreator();

  // static method
  public static WebClientPartialCounterpartUpdater forCounterpartRunner(
      final Consumer<IContainer<? extends IChainedNode>> counterpartRunner) {
    return new WebClientPartialCounterpartUpdater(counterpartRunner);
  }

  // attribute
  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  // constructor
  private WebClientPartialCounterpartUpdater(
      final Consumer<IContainer<? extends IChainedNode>> counterpartRunner) {

    GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.counterpartRunner = counterpartRunner;
  }

  // method
  public void updateControlOnCounterpart(final IControl<?, ?> control) {

    final var webGui = control.getStoredParentGui();
    webGui.applyStyleIfHasStyle();

    final var updateCommands = createUpdateCommandsFromControl(control);

    counterpartRunner.accept(updateCommands);
  }

  // method
  private IContainer<ChainedNode> createUpdateCommandsFromControl(final IControl<?, ?> control) {

    final var webGui = control.getStoredParentGui();

    return ImmutableList.withElement(
        UPDATE_COMMAND_CREATOR.createSetRootHtmlElementCommandFromControl(control),
        UPDATE_COMMAND_CREATOR.createSetCssCommandFromWebGui(webGui),
        UPDATE_COMMAND_CREATOR.createSetEventFunctionsCommandFromWebGui(webGui),
        UPDATE_COMMAND_CREATOR.createSetUserInputFunctionsCommandFromWebGui(webGui));
  }
}
