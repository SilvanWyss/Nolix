//package declaration
package ch.nolix.businessapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;
import ch.nolix.core.functionuniversalapi.IIntTakerElementGetter;
import ch.nolix.system.gui.color.Color;

//interface
public interface IFractalBuilder {
	
	//method declaration
	IFractal build();
	
	//method declaration
	int getMaxIterationCount();
	
	//method declaration
	IFractalBuilder setBigDecimalScale(int bigDecumalScale);
	
	//method declaration
	IFractalBuilder setColorFunction(IIntTakerElementGetter<Color> colorFunction);
	
	//method declaration
	IFractalBuilder setHeightInPixel(final int heightInPixel);
	
	//method declaration
	IFractalBuilder setImaginaryComponentInterval(double min, double max);
	
	//method declaration
	IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);
	
	//method declaration
	IFractalBuilder setMaxIterationCount(int maxIterationCount);
	
	//method declaration
	IFractalBuilder setMinMagnitudeForDivergence(BigDecimal minMagnitudeForDivergence);
	
	//method declaration
	IFractalBuilder setMinMagnitudeForDivergence(double minMagnitudeForDivergence);
	
	//method declaration
	IFractalBuilder setRealComponentInterval(double min, double max);

	//method declaration
	IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);
	
	//method declaration
	IFractalBuilder setSequenceCreator(
		IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator
	);
	
	//method declaration
	IFractalBuilder setWidthInPixel(int widthInPixel);
}
