package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import ch.nolix.coreapi.programcontrol.flowcontrol.IAsLongAsMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IAsSoonAsMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IFlowControllerMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IForCountMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IForMaxMillisecondsMediator;
import ch.nolix.coreapi.programcontrol.future.IFuture;
import ch.nolix.coreapi.programcontrol.future.IResultFuture;

/**
 * @author Silvan Wyss
 */
public final class FlowControllerMediator implements IFlowControllerMediator {
  /**
   * {@inheritDoc}
   */
  @Override
  public IAsLongAsMediator asLongAs(final BooleanSupplier condition) {
    return FlowController.asLongAs(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAsSoonAsMediator asSoonAs(final BooleanSupplier condition) {
    return FlowController.asSoonAs(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAsSoonAsMediator asSoonAsNoMore(final BooleanSupplier condition) {
    return FlowController.asSoonAsNoMore(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFuture enqueue(final Runnable job) {
    return FlowController.enqueue(job);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IForCountMediator forCount(final int maxRunCount) {
    return FlowController.forCount(maxRunCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
    return FlowController.forMaxMilliseconds(maxDurationInMilliseconds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
    return FlowController.forMaxSeconds(maxDurationInSeconds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFuture runInBackground(final Runnable job) {
    return FlowController.runInBackground(job);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <R> IResultFuture<R> runInBackground(final Supplier<R> resultJob) {
    return FlowController.runInBackground(resultJob);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAsLongAsMediator until(final BooleanSupplier condition) {
    return FlowController.until(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitAsLongAs(final BooleanSupplier condition) {
    FlowController.waitAsLongAs(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForMilliseconds(final int durationInMilliseconds) {
    FlowController.waitForMilliseconds(durationInMilliseconds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitForSeconds(final int durationInSeconds) {
    FlowController.waitForSeconds(durationInSeconds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntil(final BooleanSupplier condition) {
    FlowController.waitUntil(condition);
  }
}
