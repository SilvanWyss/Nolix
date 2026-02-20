/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import ch.nolix.baseapi.programcontrol.flowcontrol.IFlowControllerMediator;
import ch.nolix.baseapi.programcontrol.flowcontrol.IWaitMediator;

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
