package ch.nolix.core.programcontrol.flowcontrol;

/**
 * @author Silvan Wyss
 * @version 2020-08-15
 */
public final class ActionMediator {

  private static final SequencerMediator SEQUENCER_MEDIATOR = new SequencerMediator();

  /**
   * Creates a new {@link ActionMediator}.
   */
  ActionMediator() {
  }

  /**
   * @return a {@link SequencerMediator}.
   */
  public SequencerMediator andThen() {
    return SEQUENCER_MEDIATOR;
  }
}
