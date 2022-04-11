//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.functionapi.I2ElementTakerElementGetter;
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
	BigDecimal getMaxImaginaryComponent();
	
	//method declaration
	int getMaxIterationCount();

	//method declaration
	BigDecimal getMaxRealComponent();	
	
	//method declaration
	BigDecimal getMinImaginaryComponent();
	
	//method declaration
	BigDecimal getMinMagnitudeForDivergence();
	
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
	MutableImage toImage();
}
