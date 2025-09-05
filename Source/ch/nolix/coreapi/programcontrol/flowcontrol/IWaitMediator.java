package ch.nolix.coreapi.programcontrol.flowcontrol;

/**
 * @author Silvan Wyss
 * @version 2025-07-28
 */
public interface IWaitMediator {
  /**
   * @return a new {@link IFlowControllerMediator}.
   */
  IFlowControllerMediator andThen();
}
