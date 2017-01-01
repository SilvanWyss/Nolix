/*
 * file:	SequencePattern.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.sequencer.Sequencer;

//class
public final class SequencePattern<E> {
	
	//multiple attributes
	private final List<IElementTakerBooleanGetter<E>> conditions = new List<IElementTakerBooleanGetter<E>>();
	private final List<IElementTakerBooleanGetter<List<E>>> sequenceConditions = new List<IElementTakerBooleanGetter<List<E>>>(); 
	
	//method
	/**
	 * Adds a blank condition for the next element of the sequences of this sequence pattern.
	 */
	public SequencePattern<E> addBlankForNext() {
		
		addNextWithCondition(e -> {return true;});
		
		return this;
	}
	
	//method
	/**
	 * Adds the condition for the next element of the sequences of this sequence pattern.
	 * 
	 * @param condition
	 */
	public SequencePattern<E> addNextWithCondition(IElementTakerBooleanGetter<E> condition) {
		
		conditions.addAtEnd(condition);
		
		return this;
	}
	
	//method
	/**
	 * @param count
	 * @return a new for count mediator of this sequence pattern with the given next count
	 * @throws Exception if the given count is negative
	 */
	public SequencePatternForCountMediator<E> forCount(final int count) {
		return new SequencePatternForCountMediator<E>(this, count);
	}
	
	//method
	/**
	 * @return the number of elements of the sequences of this sequence pattern
	 */
	public int getSize() {
		return conditions.getSize();
	}
	
	public SequencePattern<E> addSequenceCondition(IElementTakerBooleanGetter<List<E>> sequenceCondition) {
		
		sequenceConditions.addAtEnd(sequenceCondition);
		
		return this;
	}
	
	//package visible method
	/**
	 * The complexity of this method is O((n-m)*m) if the given container contains n elements and m is the size of the sequences of this sequence pattern.
	 * 
	 * @param container
	 * @return the sequences that match this sequence pattern
	 */
	List<List<E>> getSequences(final List<E> container) {
		
		final List<List<E>> sequences = new List<List<E>>();
		
		final int maxSequenceCount = container.getSize() - getSize() + 1;
		int sequenceIndex = 1;	
		final ListIterator<E> iterator = container.iterator();
		while (sequenceIndex <= maxSequenceCount) {
		
			boolean sequenceCanMatch = true;
			final ListIterator<E> iterator2 = iterator.getCopy();
			for (IElementTakerBooleanGetter<E> c: conditions) {
				
				final E element = iterator2.next();
				
				if (!c.getOutput(element)) {
					sequenceCanMatch = false;
					break;
				}
			}
			
			if (sequenceCanMatch) {
				
				final List<E> sequence = new List<E>();
				final ListIterator<E> iterator3 = iterator.getCopy();
				Sequencer.forCount(getSize()).run(()->sequence.addAtEnd(iterator3.next()));
				
				if (sequenceConditions.isTrueForEach(sc -> sc.getOutput(sequence))) {
					sequences.addAtEnd(sequence);
				}
			}
			
			//Increments the sequence index and the iterator.
			sequenceIndex++;
			iterator.next();
		}
		
		return sequences;
	}
	
	//package-visible method
	/**
	 * The complexity of this method is 
	 * 
	 * @param container
	 * @return true if the given container matches this sequence pattern
	 */
	boolean matches(List<E> container) {
		
		if (container.getSize() != getSize()) {
			return false;
		}
		
		Iterator<IElementTakerBooleanGetter<E>> iterator = conditions.iterator();
		for (E e: container) {
			if (!iterator.next().getOutput(e)) {
				return false;
			}
		}
		
		return sequenceConditions.isTrueForEach(sc -> sc.getOutput(container));
	}
}
