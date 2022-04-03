//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//own imports
import java.math.BigDecimal;

//interface
public interface IClosedIntervalFactory {
	
	//method declaration
	IClosedInterval createClosedInterval(BigDecimal min, BigDecimal max);
	
	//method declaration
	IClosedInterval createClosedInterval(BigDecimal min, BigDecimal max, int bigDecimalScale);
	
	//method declaration
	IClosedInterval createClosedInterval(double min, double max);
	
	//method declaration
	IClosedInterval createClosedInterval(double min, double max, int bigDecimalScale);
}
