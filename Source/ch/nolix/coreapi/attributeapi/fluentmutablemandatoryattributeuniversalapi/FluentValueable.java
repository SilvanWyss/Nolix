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
 * @param <VA> is the type of a {@link FluentValueable}.
 * @param <V> is the type of the value of a {@link FluentValueable}.
 */
public interface FluentValueable<VA extends FluentValueable<VA, V>, V> extends Valued<VA, V> {
	
	//method declaration
	/**
	 * Sets the value of the current {@link FluentValueable}.
	 * 
	 * @param value
	 * @return the current {@link FluentValueable}.
	 */
	VA setValue(V value);
}