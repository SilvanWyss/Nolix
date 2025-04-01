package ch.nolix.system.application.webapplicationrefreshqueue;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.system.application.webapplicationcounterpartupdater.WebClientCounterpartUpdater;
import ch.nolix.system.application.webapplicationcounterpartupdater.WebClientPartialCounterpartUpdater;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

public final class WebClientRefreshQueue {

  private final BooleanSupplier openStateRequestable;

  private final Consumer<IContainer<? extends IChainedNode>> counterpartRunner;

  private boolean updatingCounterpart;

  private UpdateTicket updateTicket;

  private WebClientRefreshQueue(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {

    Validator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    Validator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientRefreshQueue forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<IContainer<? extends IChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientRefreshQueue(counterpartRunner, openStateRequester);
  }

  public void updateControlOnCounterpart(
    final IControl<?, ?> control,
    final boolean updateConstellationOrStyle) {

    final IContainer<IControl<?, ?>> controls = ImmutableList.withElement(control);

    updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  public void updateControlsOnCounterpart(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {

    setUpdatingControlsOnCounterpartAsRequired(controls, updateConstellationOrStyle);

    if (!isUpdatingCounterpart()) {
      updateCounterpartAsLongAsRequired();
    }
  }

  public void updateWebGuiOfCounterpart(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {

    setUpdatingWebGuiOfCounterpartAsRequired(webGui, updateConstellationOrStyle);

    if (!isUpdatingCounterpart()) {
      updateCounterpartAsLongAsRequired();
    }
  }

  private void assertUpdatingCounterpartIsRequired() {
    if (!updatingCounterpartIsRequired()) {
      throw GeneralException.withErrorMessage("Updating counterpart is not required.");
    }
  }

  private synchronized UpdateTicket getNextUpdateTicket() {

    assertUpdatingCounterpartIsRequired();

    updatingCounterpart = true;

    final var localUpdateTicket = updateTicket;

    updateTicket = null;

    return localUpdateTicket;
  }

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

  private synchronized boolean isUpdatingCounterpart() {
    return updatingCounterpart;
  }

  private synchronized void setFinishedUpdateCounterpart() {
    updatingCounterpart = false;
  }

  private synchronized void setUpdatingControlsOnCounterpartAsRequired(
    final IContainer<IControl<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    if (updatingCounterpartIsRequired()) {
      if (updateTicket.isForSpecificControls()) {

        final var allControls = getStoredAllControlsFromUpdateTicketAndGivenControls(controls);

        updateTicket = UpdateTicket.forControls(allControls, updateConstellationOrStyle);
      }
    } else {
      updateTicket = UpdateTicket.forControls(controls, updateConstellationOrStyle);
    }
  }

  private synchronized void setUpdatingWebGuiOfCounterpartAsRequired(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {
    if (!updatingCounterpartIsRequired() || updateTicket.isForSpecificControls()) {
      updateTicket = UpdateTicket.forWebGui(webGui, updateConstellationOrStyle);
    }
  }

  private void updateCounterpart() {

    final var nextUpdateTicket = getNextUpdateTicket();

    updateCounterpart(nextUpdateTicket);

    setFinishedUpdateCounterpart();
  }

  private void updateCounterpart(final UpdateTicket updateTicket) {
    if (updateTicket.isForWholeWebGui()) {
      WebClientCounterpartUpdater
        .forCounterpartRunnerAndOpenStateRequestable(counterpartRunner, openStateRequestable)
        .updateCounterpartFromWebGui(updateTicket.getStoredWebGui());
    } else {
      WebClientPartialCounterpartUpdater
        .forCounterpartRunnerAndOpenStateRequestable(counterpartRunner, openStateRequestable)
        .updateControlsOnCounterpart(updateTicket.getStoredControls(), updateTicket.shouldUpdateConstellationOrStyle());
    }
  }

  private void updateCounterpartAsLongAsRequired() {
    while (updatingCounterpartIsRequiredSynchronized()) {
      updateCounterpart();
    }
  }

  private boolean updatingCounterpartIsRequired() {
    return (updateTicket != null);
  }

  private synchronized boolean updatingCounterpartIsRequiredSynchronized() {
    return updatingCounterpartIsRequired();
  }
}
