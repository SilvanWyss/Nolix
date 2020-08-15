//package declaration
package ch.nolix.common.sequencer;

//class
/**
 * @author Silvan Wyss
 * @month 2020-08
 * @lines 20
 */
public final class ActionMediator {
	
	//static attribute
	private static final SequencerMediator sequencerMediator = new SequencerMediator();
	
	//visibility-reducing constructor
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
