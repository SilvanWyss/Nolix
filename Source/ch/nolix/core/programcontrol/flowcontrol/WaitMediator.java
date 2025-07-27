package ch.nolix.core.programcontrol.flowcontrol;

/**
 * @author Silvan Wyss
 * @version 2020-08-15
 */
public final class WaitMediator {

  private static final FlowControllerMediator SEQUENCER_MEDIATOR = new FlowControllerMediator();

  /**
   * Creates a new {@link WaitMediator}.
   */
  WaitMediator() {
  }

  /**
   * @return a {@link FlowControllerMediator}.
   */
  public FlowControllerMediator andThen() {
    return SEQUENCER_MEDIATOR;
  }
}
