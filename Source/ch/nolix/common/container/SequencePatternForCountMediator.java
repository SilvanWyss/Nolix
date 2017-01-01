/*
 * file:	SequencePatternForCountMediator.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.common.container;

import ch.nolix.common.functional.IElementTakerBooleanGetter;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.util.Validator;

//class
public final class SequencePatternForCountMediator<E> {

	//attributes
	private final SequencePattern<E> sequencePattern;
	private final int count;
	
	//constructor
	/**
	 * Creates new for next mediator with the given sequence pattern and the givne next count.
	 * 
	 * @param sequencePattern
	 * @param nextCount
	 * @throws Exception if:
	 * -The given sequence is null.
	 * -The given next count is negative.
	 */
	public SequencePatternForCountMediator(final SequencePattern<E> sequencePattern, final int nextCount) {
		
		//Checks the given arguments.
		Validator.throwExceptionIfValueIsNull("sequence pattern", sequencePattern);
		Validator.throwExceptionIfValueIsNegative("next count", nextCount);
		
		//Sets the values of this for next mediator.
		this.sequencePattern = sequencePattern;
		this.count = nextCount;
	}
	
	//method
	public SequencePattern<E> addBlankForNext() {
			
		Sequencer.forCount(count).run(() -> sequencePattern.addBlankForNext());
		
		return sequencePattern;
	}
	
	//method
	public SequencePattern<E> addNextWithCondition(IElementTakerBooleanGetter<E> condition) {
		
		Sequencer.forCount(count).run(() -> sequencePattern.addNextWithCondition(condition));
		
		return sequencePattern;
	}
}
