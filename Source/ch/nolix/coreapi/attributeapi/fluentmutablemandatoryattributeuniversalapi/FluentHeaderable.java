//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Headered;

//interface
/**
 * A {@link FluentHeaderable} is a {@link Headered} whose header can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-04-15
 * @param <FH> is the type of a {@link FluentHeaderable}.
 */
public interface FluentHeaderable<FH extends Headered> extends Headered {
	
	//method declaration
	/**
	 * Sets the header of the current {@link FluentHeaderable}.
	 * 
	 * @param header
	 * @return the current {@link FluentHeaderable}.
	 * @throws RuntimeException if the given header is null.
	 * @throws RuntimeException if the given header is blank.
	 */
	FH setHeader(String header);
}
