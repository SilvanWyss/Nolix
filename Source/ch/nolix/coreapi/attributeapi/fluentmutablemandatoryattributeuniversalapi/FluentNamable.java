//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;

//interface
/**
 * A {@link FluentNamable} is a {@link Named} whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <N> is the type of a {@link FluentNamable}.
 */
public interface FluentNamable<N extends FluentNamable<N>> extends Named {
	
	//method declaration
	/**
	 * Sets the name of the current {@link FluentNamable}.
	 * 
	 * @param name
	 * @return the current {@link FluentNamable}.
	 */
	N setName(String name);
}
