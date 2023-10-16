//package declaration
package ch.nolix.core.programcontrol.sequencer;

//class
/**
 * @author Silvan Wyss
 * @date 2020-08-15
 */
public final class ActionMediator {

  //constant
  private static final SequencerMediator SEQUENCER_MEDIATOR = new SequencerMediator();

  //constructor
  /**
   * Creates a new {@link ActionMediator}.
   */
  ActionMediator() {
  }

  //method
  /**
   * @return a {@link SequencerMediator}.
   */
  public SequencerMediator andThen() {
    return SEQUENCER_MEDIATOR;
  }
}
