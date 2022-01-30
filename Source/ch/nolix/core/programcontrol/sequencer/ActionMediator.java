//package declaration
package ch.nolix.core.programcontrol.sequencer;

//class
/**
 * @author Silvan Wyss
 * @date 2020-08-15
 * @lines 20
 */
public final class ActionMediator {
	
	//static attribute
	private static final SequencerMediator sequencerMediator = new SequencerMediator();
	
	//constructor
	/**
	 * Creates a new {@link ActionMediator}.
	 */
	ActionMediator() {}
	
	//method
	/**
	 * @return a {@link SequencerMediator}.
	 */
	public SequencerMediator andThen() {
		return sequencerMediator;
	}
}
