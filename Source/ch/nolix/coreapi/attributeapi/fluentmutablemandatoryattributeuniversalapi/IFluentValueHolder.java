//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IValueHolder;

//interface
/**
 * A {@link IFluentValueHolder} is a {@link IValueHolder} whose value can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-09-06
 * @param <FVH> is the type of a {@link IFluentValueHolder}.
 * @param <V> is the type of the value of a {@link IFluentValueHolder}.
 */
public interface IFluentValueHolder<FVH extends IFluentValueHolder<FVH, V>, V> extends IValueHolder<V> {
	
	//method declaration
	/**
	 * Sets the value of the current {@link IFluentValueHolder}.
	 * 
	 * @param value
	 * @return the current {@link IFluentValueHolder}.
	 * @throws RuntimeException if the given value is null.
	 */
	FVH setValue(V value);
}
