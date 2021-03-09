//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.container.pair.Pair;

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
