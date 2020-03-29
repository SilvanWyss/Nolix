//package declaration
package ch.nolix.common.containers;

//own imports
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 70
 * @param <E>
 * - The type the elements of the sequences of the sequence pattern of a sequence pattern next mediator.
 */
public final class SequencePatternNextMediator<E> {

	//attributes
	private final SequencePattern<E> sequencePattern;
	private final int count;
	
	//constructor
	/**
	 * Creates a new sequence pattern next mediator for the given sequence pattern that has the given count.
	 * 
	 * @param sequencePattern
	 * @param count
	 * @throws ArgumentIsNullException if the given sequence pattern is null.
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	SequencePatternNextMediator(final SequencePattern<E> sequencePattern, final int count) {
		
		//Checks if the given sequence pattern is not null.
		Validator.suppose(sequencePattern).isOfType(SequencePattern.class);
		
		//Checks if the given count is not negative.
		Validator.suppose(count).thatIsNamed("count").isNotNegative();
		
		//Sets the sequence pattern and the count of this sequence pattern next mediator.
		this.sequencePattern = sequencePattern;
		this.count = count;
	}
	
	//method
	/**
	 * Adds a blank condition for the next elements
	 * of the sequences of the sequence pattern of this sequence pattern next mediator.
	 * 
	 * @return the sequence pattern of this sequence pattern next mediator.
	 */
	public SequencePattern<E> addBlank() {
			
		Sequencer.forCount(count).run(() -> sequencePattern.addBlankForNext());
		
		return sequencePattern;
	}
	
	//method
	/**
	 * Adds the given condition for the next elements
	 * of the sequences of the sequence pattern of this sequence pattern next mediator.
	 * 
	 * @param condition
	 * @return the sequence pattern of this sequence pattern next mediator.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public SequencePattern<E> addCondition(final IElementTakerBooleanGetter<E> condition) {
		
		Sequencer.forCount(count).run(() -> sequencePattern.addConditionForNext(condition));
		
		return sequencePattern;
	}
}
