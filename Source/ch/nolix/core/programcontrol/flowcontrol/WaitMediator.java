package ch.nolix.core.programcontrol.flowcontrol;

import ch.nolix.coreapi.programcontrol.flowcontrol.IFlowControllerMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IWaitMediator;

/**
 * @author Silvan Wyss
 */
public final class WaitMediator implements IWaitMediator {
  /**
   * @return a {@link FlowControllerMediator}.
   */
  @Override
  public IFlowControllerMediator andThen() {
    return new FlowControllerMediator();
  }
}
