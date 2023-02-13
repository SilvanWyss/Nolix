//package declaration
package ch.nolix.coreapi.containerapi.sequencesearchapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;

//interface
/**
 * @author Silvan Wyss
 * @date 2023-02-13
 * @param <E> is the type of the elements of the searched-for sequences of the pattern {@link ISequencePatternBuilder} of
 * a {@link INextMediator}.
 */
public interface INextMediator<E> {
	
	//method declaration
	/**
	 * Adds a blank condition to the current {@link INextMediator} for the next element of
	 * the searched-for sequences of the parent {@link ISequencePatternBuilder} of the current {@link INextMediator}.
	 * 
	 * @return the parent {@link ISequencePatternBuilder} of the current {@link INextMediator}.
	 */
	ISequencePatternBuilder<E> addBlank();
	
	//method declaration
	/**
	 * Adds the given condition to the current {@link INextMediator} for the next element of
	 * the searched-for sequences of the parent {@link ISequencePatternBuilder} of the current {@link INextMediator}.
	 * 
	 * @param condition
	 * @return the parent {@link ISequencePatternBuilder} of the current {@link INextMediator}.
	 * @throws RuntimeException if the given condition is null.
	 */
	ISequencePatternBuilder<E> addCondition(IElementTakerBooleanGetter<E> condition);
}
