//package declaration
package ch.nolix.techapi.genericmathapi;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;

//interface
public interface IFractal {
	
	//method declaration
	int getBigDecimalScale();
	
	//method declaration
	Color getColor(int index);
	
	//method declaration
	int getHeightInPixel();
	
	//method declaration
	IClosedInterval getImaginaryComponentInterval();	
	
	//method declaration
	BigDecimal getMaxImaginaryComponent();
	
	//method declaration
	int getMaxIterationCount();

	//method declaration
	BigDecimal getMaxRealComponent();	
	
	//method declaration
	BigDecimal getMinImaginaryComponent();
	
	//method declaration
	BigDecimal getMinMagnitudeForConvergence();

	//method declaration
	BigDecimal getMinRealComponent();
	
	//method declaration
	I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	getNextValueFunction();

	//method declaration
	BigDecimal getPixelsPerUnit();
	
	//method declaration
	IClosedInterval getRealComponentInterval();
	
	//method declaration
	IComplexNumber[] getStartValues(final IComplexNumber complexNumber);
	
	//method declaration
	BigDecimal getUnitsPerPixel();
	
	//method declaration
	int getWidthInPixel();
	
	//method declaration
	IImageBuilder startImageBuild();
	
	//method declaration
	Image toImage();
}
