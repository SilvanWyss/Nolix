//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.pair.Pair;

//interface
public interface IClosedInterval {
	
	//method declaration
	public abstract boolean contains(BigDecimal value);
	
	//method declaration
	public abstract int getBigDecimalScale();
	
	//method declaration
	public abstract Pair<IClosedInterval, IClosedInterval> getHalfs();
	
	//method declaration
	public abstract IClosedInterval getInBigDecimalScale(int bigDecimalScale);
	
	//method declaration
	public abstract BigDecimal getLength();
	
	//method declaration
	public abstract BigDecimal getMax();
	
	//method declaration
	public abstract BigDecimal getMidPoint();
	
	//method declaration
	public abstract BigDecimal getMin();
	
	//method declaration
	public abstract boolean intersects(IClosedInterval closedInterval);
}
