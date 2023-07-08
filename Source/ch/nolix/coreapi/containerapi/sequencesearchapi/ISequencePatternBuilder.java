//package declaration
package ch.nolix.coreapi.containerapi.sequencesearchapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;
import ch.nolix.coreapi.functionapi.skillapi.IBuilder;

//interface
/**
 * @author Silvan Wyss
 * @date 2023-02-13
 * @param <E> is the type of the elements of the searched-for sequences of a {@link ISequencePatternBuilder}.
 */
public interface ISequencePatternBuilder<E> extends EmptinessRequestable, IBuilder<ISequencePattern<E>> {
	
	//method declaration
	/**
	 * Adds a blank condition for the next element of the searched-for sequences to
	 * the current {@link ISequencePatternBuilder}.
	 * 
	 * @return the current {@link ISequencePatternBuilder}.
	 */
	ISequencePatternBuilder<E> addBlankForNext();
	
	//method declaration
	/**
	 * Adds the given condition for the next element of the searched-for sequences to
	 * the current {@link ISequencePatternBuilder}.
	 * 
	 * @param condition
	 * @return the current {@link ISequencePatternBuilder}.
	 * @throws RuntimeException if the given condition is null.
	 */
	ISequencePatternBuilder<E> addConditionForNext(IElementTakerBooleanGetter<E> condition);
	
	//method declaration
	/**
	 * @param count
	 * @return a new {@link INextMediator} for the current {@link ISequencePatternBuilder} with the given count.
	 * @throws RuntimeException if the given count is not positive
	 */
	INextMediator<E> forNext(int count);
}
