//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.sequencer.Sequencer;

//class
/**
 * A sequence pattern is a pattern for sequences.
 * -The sequences of a sequence pattern must have a defined length.
 * -The elements of the sequences of a sequence pattern
 * must fulfil the according element conditions of the sequence pattern.
 * -The sequences of a sequence pattern must fulfil the sequence conditions of the sequence pattern.
 * 
 * @author Silvan Wyss
 * @motnh 2016-09
 * @lines 160
 * @param <E> - The type of the elements of the sequences of a sequence pattern.
 */
public final class SequencePattern<E> {
	
	//multi-attributes
	private final LinkedList<IElementTakerBooleanGetter<E>> elementConditions = new LinkedList<>();
	private final LinkedList<IElementTakerBooleanGetter<LinkedList<E>>> sequenceConditions = new LinkedList<>();
	
	//method
	/**
	 * Adds a blank condition for the next element of the sequences of this sequence pattern.
	 * 
	 * @return this sequence pattern.
	 */
	public SequencePattern<E> addBlankForNext() {
		
		addConditionForNext(e -> true);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given condition for the next element of the sequences of this sequence pattern.
	 * 
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public SequencePattern<E> addConditionForNext(final IElementTakerBooleanGetter<E> condition) {
		
		elementConditions.addAtEnd(condition);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given sequence condition to this sequence pattern.
	 * The sequence conditions must be fulfilled from the sequences of a sequence pattern.
	 * 
	 * @param sequenceCondition
	 * @return this sequence pattern.
	 * @throws ArgumentIsNullException if the given sequence condition is null.
	 * 
	 */
	public SequencePattern<E> addSequenceCondition(
		final IElementTakerBooleanGetter<LinkedList<E>> sequenceCondition
	) {
		
		sequenceConditions.addAtEnd(sequenceCondition);
		
		return this;
	}
	
	//method
	/**
	 * @param count
	 * @return a new sequence pattern next mediator of this sequence pattern with the given count.
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	public SequencePatternNextMediator<E> forNext(final int count) {
		return new SequencePatternNextMediator<>(this, count);
	}
	
	//method
	/**
	 * @return the number of elements of the sequences of this sequence pattern.
	 */
	public int getSize() {
		return elementConditions.getSize();
	}
	
	//package visible method
	/**
	 * @param list
	 * @return the sequences that matches the given list.
	 */
	LinkedList<LinkedList<E>> getSequences(final LinkedList<E> list) {
		
		final var sequences = new LinkedList<LinkedList<E>>();
		
		final int maxSequenceCount = list.getSize() - getSize() + 1;
		
		//Iterates the given list.
		final ListIterator<E> iterator = list.iterator();
		for (int i = 1; i <= maxSequenceCount; i++) {
		
			//Asserts that the current sequence fulfills the element conditions of this sequence pattern.
			boolean sequenceFulfillsElementConditions = true;
			final ListIterator<E> iterator2 = iterator.getCopy();
			for (final IElementTakerBooleanGetter<E> c : elementConditions) {
				
				final E element = iterator2.next();
				
				if (!c.getOutput(element)) {
					sequenceFulfillsElementConditions = false;
					break;
				}
			}
			
			if (sequenceFulfillsElementConditions) {
				
				final var sequence = new LinkedList<E>();
				final var iterator3 = iterator.getCopy();
				Sequencer.forCount(getSize()).run(()->sequence.addAtEnd(iterator3.next()));
				
				//Asserts that the current sequence fulfills the sequence conditions of this sequence pattern.
				if (sequenceConditions.containsOnly(sc -> sc.getOutput(sequence))) {
					sequences.addAtEnd(sequence);
				}
			}
			
			//Increments the iterator.
			iterator.next();
		}
		
		return sequences;
	}
	
	//method
	/**
	 * @param list
	 * @return true if this sequence pattern matches the given list.
	 */
	boolean matches(final LinkedList<E> list) {
		
		//Asserts that the given list has as many elements as this sequence pattern requires.
		if (list.getSize() != getSize()) {
			return false;
		}
		
		//Asserts that the elements of the given list
		//fulfill the according element conditions this sequence pattern requires.
		final Iterator<IElementTakerBooleanGetter<E>> iterator = elementConditions.iterator();
		for (final E e: list) {
			if (!iterator.next().getOutput(e)) {
				return false;
			}
		}
		
		//Asserts that the given list fulfils the sequence conditions of this sequence pattern.
		return sequenceConditions.containsOnly(sc -> sc.getOutput(list));
	}
}
