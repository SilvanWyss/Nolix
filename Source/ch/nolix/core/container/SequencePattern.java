//package declaration
package ch.nolix.core.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.sequencer.Sequencer;

//class
/**
 * A sequence pattern is a pattern for sequences.
 * -The sequences of a sequence pattern must have a defined length.
 * -The elements of the sequences of a sequence pattern
 *  must fulfil the according element conditions of the sequence pattern.
 * -The sequences of a sequence pattern must fulfil the sequence conditions of the sequence pattern.
 * 
 * @author Silvan Wyss
 * @motnh 2016-09
 * @lines 160
 * @param <E> - The type of the elements of the sequences of a sequence pattern.
 */
public final class SequencePattern<E> {
	
	//multiple attributes
	private final List<IElementTakerBooleanGetter<E>> elementConditions
	= new List<IElementTakerBooleanGetter<E>>();
	private final List<IElementTakerBooleanGetter<List<E>>> sequenceConditions
	= new List<IElementTakerBooleanGetter<List<E>>>(); 
	
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
	 * @throws NullArgumentException if the given condition is null.
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
	 * @throws NullArgumentException if the given sequence condition is null.
	 * 
	 */
	public SequencePattern<E> addSequenceCondition(
		final IElementTakerBooleanGetter<List<E>> sequenceCondition
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
		return new SequencePatternNextMediator<E>(this, count);
	}
	
	//method
	/**
	 * @return the number of elements of the sequences of this sequence pattern.
	 */
	public int getSize() {
		return elementConditions.getElementCount();
	}
	
	//package visible method
	/**
	 * @param list
	 * @return the sequences that matches the given list.
	 */
	List<List<E>> getSequences(final List<E> list) {
		
		final List<List<E>> sequences = new List<List<E>>();
		
		final int maxSequenceCount = list.getElementCount() - getSize() + 1;
		
		//Iterates the given list.
		final ListIterator<E> iterator = list.iterator();
		for (int i = 1; i <= maxSequenceCount; i++) {
		
			//Checks if the current sequence fulfills the element conditions of this sequence pattern.
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
				
				final List<E> sequence = new List<E>();
				final ListIterator<E> iterator3 = iterator.getCopy();
				Sequencer.forCount(getSize()).run(()->sequence.addAtEnd(iterator3.next()));
				
				//Checks if the current sequence fulfills the sequence conditions of this sequence pattern.
				if (sequenceConditions.containsOnly(sc -> sc.getOutput(sequence))) {
					sequences.addAtEnd(sequence);
				}
			}
			
			//Increments the iterator.
			iterator.next();
		}
		
		return sequences;
	}
	
	//package-visible method
	/**
	 * @param list
	 * @return true if this sequence pattern matches the given list.
	 */
	boolean matches(final List<E> list) {
		
		//Checks if the given list has as many elements as this sequence pattern requires.
		if (list.getElementCount() != getSize()) {
			return false;
		}
		
		//Checks if the elements of the given list
		//fulfill the according element conditions this sequence pattern requires.
		final Iterator<IElementTakerBooleanGetter<E>> iterator = elementConditions.iterator();
		for (final E e: list) {
			if (!iterator.next().getOutput(e)) {
				return false;
			}
		}
		
		//Checks if the given list fulfils the sequence conditions of this sequence pattern.
		return sequenceConditions.containsOnly(sc -> sc.getOutput(list));
	}
}
