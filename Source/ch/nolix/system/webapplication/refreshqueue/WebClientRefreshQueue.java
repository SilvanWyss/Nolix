/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webapplication.refreshqueue;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.system.webapplication.counterpartupdater.WebClientCounterpartUpdater;
import ch.nolix.system.webapplication.counterpartupdater.WebClientPartialCounterpartUpdater;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class WebClientRefreshQueue {
  private final BooleanSupplier openStateRequestable;

  private final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner;

  private boolean updatingCounterpart;

  private UpdateTicket memberUpdateTicket;

  private WebClientRefreshQueue(
    final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequestable) {
    Validator.assertThat(openStateRequestable).thatIsNamed("open state requestable").isNotNull();
    Validator.assertThat(counterpartRunner).thatIsNamed("counterpart runner").isNotNull();

    this.openStateRequestable = openStateRequestable;
    this.counterpartRunner = counterpartRunner;
  }

  public static WebClientRefreshQueue forCounterpartRunnerAndOpenStateRequestable(
    final Consumer<ExtendedIterable<? extends ChainedNode>> counterpartRunner,
    final BooleanSupplier openStateRequester) {
    return new WebClientRefreshQueue(counterpartRunner, openStateRequester);
  }

  public void updateControlOnCounterpart(
    final Control<?, ?> control,
    final boolean updateConstellationOrStyle) {
    final ExtendedIterable<Control<?, ?>> controls = ImmutableList.withElements(control);

    updateControlsOnCounterpart(controls, updateConstellationOrStyle);
  }

  public void updateControlsOnCounterpart(
    final ExtendedIterable<Control<?, ?>> controls,
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

    final var localUpdateTicket = memberUpdateTicket;

    memberUpdateTicket = null;

    return localUpdateTicket;
  }

  private LinkedList<Control<?, ?>> getStoredAllControlsFromUpdateTicketAndGivenControls(
    final ExtendedIterable<Control<?, ?>> controls) {
    final var allControls = LinkedList.fromIterable(memberUpdateTicket.getStoredControls());

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
    final ExtendedIterable<Control<?, ?>> controls,
    final boolean updateConstellationOrStyle) {
    if (updatingCounterpartIsRequired()) {
      if (memberUpdateTicket.isForSpecificControls()) {
        final var allControls = getStoredAllControlsFromUpdateTicketAndGivenControls(controls);

        memberUpdateTicket = UpdateTicket.forControls(allControls, updateConstellationOrStyle);
      }
    } else {
      memberUpdateTicket = UpdateTicket.forControls(controls, updateConstellationOrStyle);
    }
  }

  private synchronized void setUpdatingWebGuiOfCounterpartAsRequired(
    final IWebGui<?> webGui,
    final boolean updateConstellationOrStyle) {
    if (!updatingCounterpartIsRequired() || memberUpdateTicket.isForSpecificControls()) {
      memberUpdateTicket = UpdateTicket.forWebGui(webGui, updateConstellationOrStyle);
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
    return (memberUpdateTicket != null);
  }

  private synchronized boolean updatingCounterpartIsRequiredSynchronized() {
    return updatingCounterpartIsRequired();
  }
}
