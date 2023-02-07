//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Valued;

//interface
/**
 * A {@link FluentValueable} is a {@link Valued} whose value can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-09-06
 * @param <FV> is the type of a {@link FluentValueable}.
 * @param <V> is the type of the value of a {@link FluentValueable}.
 */
public interface FluentValueable<FV extends FluentValueable<FV, V>, V> extends Valued<V> {
	
	//method declaration
	/**
	 * Sets the value of the current {@link FluentValueable}.
	 * 
	 * @param value
	 * @return the current {@link FluentValueable}.
	 * @throws RuntimeException if the given value is null.
	 */
	FV setValue(V value);
}
