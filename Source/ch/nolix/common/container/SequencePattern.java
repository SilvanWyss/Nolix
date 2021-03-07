//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
//own imports
import ch.nolix.common.functionapi.IElementTakerBooleanGetter;
import ch.nolix.common.programcontrol.sequencer.Sequencer;

//class
/**
 * A {@link SequencePattern} is a pattern for sequences.
 * -The sequences of a {@link SequencePattern} must have a defined length.
 * -The elements of the sequences of a {@link SequencePattern}
 *  must fulfill the according element conditions of the {@link SequencePattern}.
 * -The sequences of a {@link SequencePattern} must fulfill the sequence conditions of the {@link SequencePattern}.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @lines 160
 * @param <E> is the type of the elements of the sequences of a {@link SequencePattern}.
 */
public final class SequencePattern<E> {
	
	//multi-attributes
	private final LinkedList<IElementTakerBooleanGetter<E>> elementConditions = new LinkedList<>();
	private final LinkedList<IElementTakerBooleanGetter<LinkedList<E>>> sequenceConditions = new LinkedList<>();
	
	//method
	/**
	 * Adds a blank condition for the next element of the sequences of the current {@link SequencePattern}.
	 * 
	 * @return this {@link SequencePattern}.
	 */
	public SequencePattern<E> addBlankForNext() {
		
		addConditionForNext(e -> true);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given condition for the next element of the sequences of the current {@link SequencePattern}.
	 * 
	 * @param condition
	 * @return the current {@link SequencePattern}.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public SequencePattern<E> addConditionForNext(final IElementTakerBooleanGetter<E> condition) {
		
		elementConditions.addAtEnd(condition);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given sequence condition to the current {@link SequencePattern}.
	 * The sequence conditions must be fulfilled from the sequences of a {@link SequencePattern}.
	 * 
	 * @param sequenceCondition
	 * @return this {@link SequencePattern}.
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
	 * @return a new {@link SequencePatternNextMediator} for the current {@link SequencePattern} with the given count.
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	public SequencePatternNextMediator<E> forNext(final int count) {
		return new SequencePatternNextMediator<>(this, count);
	}
	
	//method
	/**
	 * @return the number of elements of the sequences of the current {@link SequencePattern}.
	 */
	public int getSize() {
		return elementConditions.getElementCount();
	}
	
	//package visible method
	/**
	 * @param list
	 * @return the sequences that matches the given list.
	 */
	LinkedList<LinkedList<E>> getSequences(final LinkedList<E> list) {
		
		final var sequences = new LinkedList<LinkedList<E>>();
		
		final int maxSequenceCount = list.getElementCount() - getSize() + 1;
		
		//Iterates the given list.
		final LinkedListIterator<E> iterator = list.iterator();
		for (int i = 1; i <= maxSequenceCount; i++) {
		
			//Asserts that the current sequence fulfills the element conditions of the current SequencePattern.
			boolean sequenceFulfillsElementConditions = true;
			final LinkedListIterator<E> iterator2 = iterator.getCopy();
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
				
				//Asserts that the current sequence fulfills the sequence conditions of the current SequencePattern.
				if (sequenceConditions.containsOnly(sc -> sc.getOutput(sequence))) {
					sequences.addAtEnd(sequence);
				}
			}
			
			//Increments the iterator.
			iterator.next();
		}
		
		return sequences;
	}
	
	//visibility-reduced method
	/**
	 * @param list
	 * @return true if this {@link SequencePattern} matches the given list.
	 */
	boolean matches(final LinkedList<E> list) {
		
		//Asserts that the given list has as many elements as the current SequencePattern requires.
		if (list.getElementCount() != getSize()) {
			return false;
		}
		
		//Asserts that the elements of the given list
		//fulfill the according element conditions the current SequencePattern requires.
		final Iterator<IElementTakerBooleanGetter<E>> iterator = elementConditions.iterator();
		for (final E e: list) {
			if (!iterator.next().getOutput(e)) {
				return false;
			}
		}
		
		//Asserts that the given list fulfils the sequence conditions of the current SequencePattern.
		return sequenceConditions.containsOnly(sc -> sc.getOutput(list));
	}
}
