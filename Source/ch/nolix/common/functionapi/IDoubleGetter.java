//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {#link IDoubleGetter} has a method that returns a double.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 10
 */
@FunctionalInterface
public interface IDoubleGetter {
	
	//method declaration
	/**
	 * @return a double.
	 */
	double getOutput();
}
