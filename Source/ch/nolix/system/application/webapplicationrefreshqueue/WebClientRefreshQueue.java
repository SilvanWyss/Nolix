//package declaration
package ch.nolix.system.application.webapplicationrefreshqueue;

//Java imports
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.system.application.webapplicationcounterpartupdater.WebClientCounterpartUpdater;
import ch.nolix.system.application.webapplicationcounterpartupdater.WebClientPartialCounterpartUpdater;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebClientRefreshQueue {

  //attribute
  private final BooleanSupplier openStateRequestable;

  //attribute
  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  //attribute
  private boolean updatingCounterpart;

  //optional attribute
  private UpdateTicket updateTicket;

  //constructor
  private WebClientRefreshQueue(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {

    GlobalValidator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    GlobalValidator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  //static method
  public static WebClientRefreshQueue forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientRefreshQueue(counterpartRunner, openStateRequester);
  }

  //method
  public void updateControlOnCounterpart(final IControl<?, ?> control) {

    final IContainer<IControl<?, ?>> controls = ImmutableList.withElement(control);

    updateControlsOnCounterpart(controls);
  }

  //method
  public void updateControlsOnCounterpart(final IContainer<IControl<?, ?>> controls) {

    setUpdatingControlsOnCounterpartAsRequired(controls);

    if (!isUpdatingCounterpart()) {
      updateCounterpartAsLongAsRequired();
    }
  }

  //method
  public void updateWebGuiOfCounterpart(final IWebGui<?> webGui) {

    setUpdatingWebGuiOfCounterpartAsRequired(webGui);

    if (!isUpdatingCounterpart()) {
      updateCounterpartAsLongAsRequired();
    }
  }

  //method
  private void assertUpdatingCounterpartIsRequired() {
    if (!updatingCounterpartIsRequired()) {
      throw GeneralException.withErrorMessage("Updating counterpart is not required.");
    }
  }

  //method
  private synchronized UpdateTicket getNextUpdateTicket() {

    assertUpdatingCounterpartIsRequired();

    updatingCounterpart = true;

    final var localUpdateTicket = updateTicket;

    updateTicket = null;

    return localUpdateTicket;
  }

  //method
  private LinkedList<IControl<?, ?>> getStoredAllControlsFromUpdateTicketAndGivenControls(
    final IContainer<IControl<?, ?>> controls) {

    final var allControls = LinkedList.fromIterable(updateTicket.getStoredControls());

    for (final var c : controls) {
      if (!allControls.contains(c)) {
        allControls.addAtEnd(c);
      }
    }

    return allControls;
  }

  //method
  private synchronized boolean isUpdatingCounterpart() {
    return updatingCounterpart;
  }

  //method
  private synchronized void setFinishedUpdateCounterpart() {
    updatingCounterpart = false;
  }

  //method
  private synchronized void setUpdatingControlsOnCounterpartAsRequired(final IContainer<IControl<?, ?>> controls) {
    if (updatingCounterpartIsRequired()) {
      if (updateTicket.isForSpecificControls()) {

        final var allControls = getStoredAllControlsFromUpdateTicketAndGivenControls(controls);

        updateTicket = UpdateTicket.forControls(allControls);
      }
    } else {
      updateTicket = UpdateTicket.forControls(controls);
    }
  }

  //method
  private synchronized void setUpdatingWebGuiOfCounterpartAsRequired(final IWebGui<?> webGui) {
    if (!updatingCounterpartIsRequired() || updateTicket.isForSpecificControls()) {
      updateTicket = UpdateTicket.forWebGui(webGui);
    }
  }

  //method
  private void updateCounterpart() {

    final var nextUpdateTicket = getNextUpdateTicket();

    updateCounterpart(nextUpdateTicket);

    setFinishedUpdateCounterpart();
  }

  //method
  private void updateCounterpart(final UpdateTicket updateTicket) {
    if (updateTicket.isForWholeWebGui()) {
      WebClientCounterpartUpdater
        .forCounterpartRunnerAndOpenStateRequestable(counterpartRunner, openStateRequestable)
        .updateCounterpartFromWebGui(updateTicket.getStoredWebGui());
    } else {
      WebClientPartialCounterpartUpdater
        .forCounterpartRunnerAndOpenStateRequestable(counterpartRunner, openStateRequestable)
        .updateControlsOnCounterpart(updateTicket.getStoredControls());
    }
  }

  //method
  private void updateCounterpartAsLongAsRequired() {
    while (updatingCounterpartIsRequiredSynchronized()) {
      updateCounterpart();
    }
  }

  //method
  private boolean updatingCounterpartIsRequired() {
    return (updateTicket != null);
  }

  //method
  private synchronized boolean updatingCounterpartIsRequiredSynchronized() {
    return updatingCounterpartIsRequired();
  }
}
