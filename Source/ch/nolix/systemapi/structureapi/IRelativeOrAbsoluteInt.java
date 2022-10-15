//package declaration
package ch.nolix.systemapi.structureapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
/**
 * A {@link IRelativeOrAbsoluteInt} stores either an integer or a percentage.
 *  
 * @author Silvan Wyss
 * @date 2022-10-15
 */
public interface IRelativeOrAbsoluteInt extends Specified {
	
	//method declaration
	/**
	 * @return the absolute value of the current {@link IRelativeOrAbsoluteInt}.
	 * @throws RuntimeException if the current {@link IRelativeOrAbsoluteInt} is not absolute.
	 */
	int getAbsoluteValue();
	
	//method declaration
	/**
	 * @return the percentage of the current {@link IRelativeOrAbsoluteInt}.
	 * @throws RuntimeException if the current {@link IRelativeOrAbsoluteInt} is not relative.
	 */
	double getPercentage();
	
	//method declaration
	/**
	 * @param hundredPercentValue
	 * @return the value of the current {@link IRelativeOrAbsoluteInt} relative to the given hundredPercentValue.
	 */
	int getValueRelativeToHundredPercentValue(int hundredPercentValue);
	
	//method declaration
	/**
	 * @return true if the current {@link IRelativeOrAbsoluteInt} is absolute.
	 */
	boolean isAbsolute();
	
	//method declaration
	/**
	 * @return true if the current {@link IRelativeOrAbsoluteInt} is positive.
	 */
	boolean isPositive();
	
	//method declaration
	/**
	 * @return true if the current {@link IRelativeOrAbsoluteInt} is relative.
	 */
	boolean isRelative();
}
