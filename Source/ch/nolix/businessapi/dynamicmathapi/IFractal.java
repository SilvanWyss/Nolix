//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java imports
import java.math.BigDecimal;

import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;

//interface
public interface IFractal {
	
	//method declaration
	ISequence<IComplexNumber> createSequenceFor(IComplexNumber complexNumber);
	
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
	IClosedInterval getRealComponentInterval();
	
	//method declaration
	int getWidthInPixel();
	
	//method declaration
	IImageGenerator startImageGeneration();
	
	//method declaration
	MutableImage toImage();
}
