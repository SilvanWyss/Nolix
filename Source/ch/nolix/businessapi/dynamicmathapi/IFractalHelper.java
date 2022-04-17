//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface IFractalHelper {
	
	//method declaration
	BigDecimal getHeightInPixelAsBigDecimalOf(IFractal fractal);
	
	//method declaration
	BigDecimal getHeightInUnitsOf(IFractal fractal);
	
	//method declaration
	BigDecimal getMaxXOf(IFractal fractal);
	
	//method declaration
	BigDecimal getMaxYOf(IFractal fractal);
	
	//method declaration
	BigDecimal getMinXOf(IFractal fractal);
	
	//method declaration
	BigDecimal getMinYOf(IFractal fractal);
	
	//method declaration
	BigDecimal getPixelCountPerHorizontalUnitOf(IFractal fractal);
	
	//method declaration
	BigDecimal getPixelCountPerVerticalUnitOf(IFractal fractal);
	
	//method declaration
	BigDecimal getUnitsPerHorizontalPixelOf(IFractal fractal);
	
	//method declaration
	BigDecimal getUnitsPerVerticalPixelOf(IFractal fractal);
	
	//method declaration
	BigDecimal getWidthInPixelAsBigDecimalOf(IFractal fractal);
	
	//method declaration
	BigDecimal getWidthInUnitsOf(IFractal fractal);
}
