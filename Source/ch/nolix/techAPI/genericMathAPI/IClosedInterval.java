//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//own import
import ch.nolix.core.pair.MonoPair;

//interface
public interface IClosedInterval {
	
	//abstract method
	public abstract boolean contains(BigDecimal value);
	
	//abstract method
	public abstract int getBigDecimalScale();
	
	//abstract method
	public abstract MonoPair<IClosedInterval> getHalfs();
	
	//abstract method
	public abstract IClosedInterval getInBigDecimalScale(int bigDecimalScale);
	
	//abstract method
	public abstract BigDecimal getLength();
	
	//abstract method
	public abstract BigDecimal getMax();
	
	//abstract method
	public abstract BigDecimal getMidPoint();
	
	//abstract method
	public abstract BigDecimal getMin();
	
	//abstract method
	public abstract boolean intersects(IClosedInterval closedInterval);
}
