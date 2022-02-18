//package declaration
package ch.nolix.core.functionapi;

//functional interface
/**
 * A {@link IIntGetter} has a method that returns an int.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
@FunctionalInterface
public interface IIntGetter {
	
	//method declaration
	/**
	 * @return an int.
	 */
	int getOutput();
}
