//package declaration
package ch.nolix.techapi.dynamicmathapi;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.pair.Pair;

//interface
public interface IClosedInterval {
	
	//method declaration
	boolean contains(BigDecimal value);
	
	//method declaration
	int getBigDecimalScale();
	
	//method declaration
	Pair<IClosedInterval, IClosedInterval> getHalfs();
	
	//method declaration
	IClosedInterval getInBigDecimalScale(int bigDecimalScale);
	
	//method declaration
	BigDecimal getLength();
	
	//method declaration
	BigDecimal getMax();
	
	//method declaration
	BigDecimal getMidPoint();
	
	//method declaration
	BigDecimal getMin();
	
	//method declaration
	boolean intersects(IClosedInterval closedInterval);
}
