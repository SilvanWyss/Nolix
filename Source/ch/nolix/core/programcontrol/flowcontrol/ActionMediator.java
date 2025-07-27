package ch.nolix.core.programcontrol.flowcontrol;

/**
 * @author Silvan Wyss
 * @version 2020-08-15
 */
public final class ActionMediator {

  private static final FlowControllerMediator SEQUENCER_MEDIATOR = new FlowControllerMediator();

  /**
   * Creates a new {@link ActionMediator}.
   */
  ActionMediator() {
  }

  /**
   * @return a {@link FlowControllerMediator}.
   */
  public FlowControllerMediator andThen() {
    return SEQUENCER_MEDIATOR;
  }
}
