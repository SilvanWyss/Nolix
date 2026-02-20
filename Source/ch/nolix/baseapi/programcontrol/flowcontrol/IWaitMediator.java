/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.flowcontrol;

/**
 * @author Silvan Wyss
 */
public interface IWaitMediator {
  /**
   * @return a new {@link IFlowControllerMediator}.
   */
  IFlowControllerMediator andThen();
}
