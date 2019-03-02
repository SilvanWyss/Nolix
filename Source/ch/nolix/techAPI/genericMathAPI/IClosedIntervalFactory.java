//package declaration
package ch.nolix.techAPI.genericMathAPI;

//own import
import java.math.BigDecimal;

//interface
public interface IClosedIntervalFactory {
	
	//abstract method
	public abstract IClosedInterval create(BigDecimal min, BigDecimal max);
	
	//abstract method
	public abstract IClosedInterval create(BigDecimal min, BigDecimal max, int bigDecimalScale);
	
	//abstract method
	public abstract IClosedInterval create(double min, double max);
	
	//abstract method
	public abstract IClosedInterval create(double min, double max, int bigDecimalScale);
}
