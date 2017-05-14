//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports

import ch.nolix.core.functional.IElementTakerBooleanGetter;
import ch.nolix.core.sequencer.Sequencer;

//class
/**
 * A sequence pattern is a pattern for sequences of elements from a list.
 * The sequences of a sequence pattern must have a defined length.
 * 
 * @author Silvan Wyss
 * @motnh 2016-09
 * @lines 160
 * @param <E> - The type of the elements of the sequences of a sequence pattern.
 */
public final class SequencePattern<E> {
	
	//multiple attributes
	private final List<IElementTakerBooleanGetter<E>> elementConditions = new List<IElementTakerBooleanGetter<E>>();
	private final List<IElementTakerBooleanGetter<List<E>>> sequenceConditions = new List<IElementTakerBooleanGetter<List<E>>>(); 
	
	//method
	/**
	 * Adds a blank condition for the next element of the sequences of this sequence pattern.
	 * 
	 * @return this sequence pattern.
	 */
	public SequencePattern<E> addBlankForNext() {
		
		addConditionForNext(e -> {return true;});	
		
		return this;
	}
	
	//method
	/**
	 * Adds the given condition for the next element of the sequences of this sequence pattern.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 */
	public SequencePattern<E> addConditionForNext(final IElementTakerBooleanGetter<E> condition) {
		
		elementConditions.addAtEnd(condition);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given sequence condition to this sequence pattern.
	 * A sequence condition must be fulfilled from the sequences of a sequence pattern.
	 * 
	 * @param sequenceCondition
	 * @return this sequenc pattern.
	 * @throws NullArgumentException if the given sequence condition is null.
	 * 
	 */
	public SequencePattern<E> addSequenceCondition(
		final IElementTakerBooleanGetter<List<E>> sequenceCondition)
	{
		
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
		return new SequencePatternNextMediator<E>(this, count);
	}
	
	//method
	/**
	 * @return the number of elements of the sequences of this sequence pattern.
	 */
	public int getSize() {
		return elementConditions.getSize();
	}
	
	//method
	/**
	 * The complexity of this method is O(n) if the given list contains n elements.
	 * 
	 * @param list
	 * @return true if this sequence pattern matches the given list.
	 */
	public boolean matches(List<E> list) {
		
		if (list.getSize() != getSize()) {
			return false;
		}
		
		final Iterator<IElementTakerBooleanGetter<E>> iterator = elementConditions.iterator();
		for (final E e: list) {
			if (!iterator.next().getOutput(e)) {
				return false;
			}
		}
		
		return sequenceConditions.containsOnly(sc -> sc.getOutput(list));
	}
	
	
	//package visible method
	/**
	 * @param list
	 * @return the sequences that matches the given list.
	 */
	List<List<E>> getSequences(final List<E> list) {
		
		final List<List<E>> sequences = new List<List<E>>();
		
		//Iterates the given list.
		final int maxSequenceCount = list.getSize() - getSize() + 1;
		int sequenceIndex = 1;	
		final ListIterator<E> iterator = list.iterator();
		while (sequenceIndex <= maxSequenceCount) {
		
			//Determines if the current sequence fulfils the element conditions pf this sequence pattern.
			boolean sequenceFulfilsElementConditions = true;
			final ListIterator<E> iterator2 = iterator.getCopy();
			for (IElementTakerBooleanGetter<E> c: elementConditions) {
				
				final E element = iterator2.next();
				
				if (!c.getOutput(element)) {
					sequenceFulfilsElementConditions = false;
					break;
				}
			}
			
			if (sequenceFulfilsElementConditions) {
				
				final List<E> sequence = new List<E>();
				final ListIterator<E> iterator3 = iterator.getCopy();
				Sequencer.forCount(getSize()).run(()->sequence.addAtEnd(iterator3.next()));
				
				if (sequenceConditions.containsOnly(sc -> sc.getOutput(sequence))) {
					sequences.addAtEnd(sequence);
				}
			}
			
			//Increments the sequence index and the iterator.
			sequenceIndex++;
			iterator.next();
		}
		
		return sequences;
	}
}
