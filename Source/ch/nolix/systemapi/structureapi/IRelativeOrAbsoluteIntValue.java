//package declaration
package ch.nolix.systemapi.structureapi;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//interface
public interface IRelativeOrAbsoluteIntValue extends Specified {
	
	//method declaration
	int getAbsoluteValue();
	
	//method declaration
	double getPercentage();
	
	//method declaration
	int getValueRelativeToHundredPercentValue(int hundredPercentValue);
	
	//method declaration
	boolean isAbsolute();
	
	//method declaration
	boolean isPositive();
	
	//method declaration
	boolean isRelative();
}
