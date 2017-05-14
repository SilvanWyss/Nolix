//package declaration
package ch.nolix.core.container;

//own imports
import ch.nolix.core.functional.IElementTakerBooleanGetter;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.zetaValidator.ZetaValidator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 70
 * @param <E> - The type of the elements of the sequences of the sequence pattern of a sequence pattern next mediator.
 */
public final class SequencePatternNextMediator<E> {

	//attributes
	private final SequencePattern<E> sequencePattern;
	private final int count;
	
	//package-visible constructor
	/**
	 * Creates new sequence pattern next mediator that:
	 * -Belongs to the given sequence pattern.
	 * -Has the given count.
	 * 
	 * @param sequencePattern
	 * @param count
	 * @throws NullArgumentException if the given sequence pattern is null.
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	SequencePatternNextMediator(final SequencePattern<E> sequencePattern, final int count) {
		
		//Checks if the given sequence pattern is not null.
		ZetaValidator.supposeThat(sequencePattern).thatIsInstanceOf(SequencePattern.class).isNotNull();
		
		//Checks if the given count is not negative.
		ZetaValidator.supposeThat(count).thatIsNamed("count").isNotNegative();
		
		this.sequencePattern = sequencePattern;
		this.count = count;
	}
	
	//method
	/**
	 * Adds a blank condition for the next elements of the sequences of the sequence pattern this sequence pattern next mediator belongs to.
	 * 
	 * @return the sequence pattern this sequence pattern next mediator belongs to.
	 */
	public SequencePattern<E> addBlank() {
			
		Sequencer.forCount(count).run(() -> sequencePattern.addBlankForNext());
		
		return sequencePattern;
	}
	
	//method
	/**
	 * Adds the given condition for the next elements of the sequences of the sequence pattern this sequence pattern next mediator belongs to.
	 * 
	 * @param condition
	 * @return the sequence pattern this sequence pattern next mediator belongs to.
	 * @throws NullArgumentException if the given condition is null.
	 */
	public SequencePattern<E> addCondition(final IElementTakerBooleanGetter<E> condition) {
		
		Sequencer.forCount(count).run(() -> sequencePattern.addConditionForNext(condition));
		
		return sequencePattern;
	}
}
