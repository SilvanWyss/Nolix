//package declaration
package ch.nolix.techAPI.genericMathAPI;

//own import
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
