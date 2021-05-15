//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//own imports
import java.math.BigDecimal;

//interface
public interface IClosedIntervalFactory {
	
	//method declaration
	IClosedInterval create(BigDecimal min, BigDecimal max);
	
	//method declaration
	IClosedInterval create(BigDecimal min, BigDecimal max, int bigDecimalScale);
	
	//method declaration
	IClosedInterval create(double min, double max);
	
	//method declaration
	IClosedInterval create(double min, double max, int bigDecimalScale);
}
