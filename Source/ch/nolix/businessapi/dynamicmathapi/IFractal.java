//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;

//interface
public interface IFractal {
	
	//method declaration
	int getBigDecimalScale();
	
	//method declaration
	Color getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(int iterationCount);
	
	//method declaration
	int getHeightInPixel();
	
	//method declaration
	IClosedInterval getImaginaryComponentInterval();	
	
	//method declaration
	int getMaxIterationCount();
	
	//method declaration
	BigDecimal getMinMagnitudeForDivergence();
	
	//method declaration
	BigDecimal getPixelCountPerHorizontalUnit();
	
	//method declaration
	BigDecimal getPixelCountPerVerticalUnit();
	
	//method declaration
	IClosedInterval getRealComponentInterval();
	
	//method declaration
	int getWidthInPixel();
	
	//method declaration
	IImageBuilder startImageBuild();
	
	//method declaration
	MutableImage toImage();
}
