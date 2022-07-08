//package declaration
package ch.nolix.businessapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.imageapi.IMutableImage;

//interface
public interface IFractal {
	
	//method declaration
	ISequence<IComplexNumber> createSequenceFor(IComplexNumber complexNumber);
	
	//method declaration
	int getBigDecimalScale();
	
	//method declaration
	IColor getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(int iterationCount);
	
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
	IMutableImage<?> toImage();
}
