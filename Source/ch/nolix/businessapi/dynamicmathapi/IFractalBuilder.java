//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.functionapi.I2ElementTakerElementGetter;
import ch.nolix.common.functionapi.I3ElementTakerElementGetter;
import ch.nolix.common.functionapi.I4ElementTakerElementGetter;
import ch.nolix.common.functionapi.I5ElementTakerElementGetter;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.functionapi.IIntTakerElementGetter;
import ch.nolix.element.gui.color.Color;

//interface
public interface IFractalBuilder {
	
	//method declaration
	IFractal build();
	
	//method declaration
	IFractalBuilder setBigDecimalScale(int bigDecumalScale);
	
	//method declaration
	IFractalBuilder setColorFunction(IIntTakerElementGetter<Color> colorFunction);
	
	//method declaration
	IFractalBuilder setImaginaryComponentInterval(double min, double max);
	
	//method declaration
	IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);
	
	//method declaration
	IFractalBuilder setMaxIterationCount(int maxIterationCount);

	//method declaration
	IFractalBuilder setMinMagnitudeForConvergence(BigDecimal minMagnitudeForConvergence);

	//method declaration
	IFractalBuilder setMinMagnitudeForConvergence(double minMagnitudeForConvergence);
	
	//method declaration
	IFractalBuilder setNextValueFunction(
		I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber> nextValueFunction
	);
	
	//method
	default IFractalBuilder setNextValueFunctionFor1Predecessor(
		final I2ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], c));
	}
	
	//method
	default IFractalBuilder setNextValueFunctionFor2Predecessor(
		final I3ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], p[1], c));
	}
	
	//method
	default IFractalBuilder setNextValueFunctionFor3Predecessor(
		final I4ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], p[1], p[2], c));
	}

	//method
	default IFractalBuilder setNextValueFunctionFor4Predecessor(
		I5ElementTakerElementGetter<
			IComplexNumber,
			IComplexNumber,
			IComplexNumber,
			IComplexNumber,
			IComplexNumber,
			IComplexNumber
		>
		nextValueFunction
	) {
		return
		setNextValueFunction((p, z) -> nextValueFunction.getOutput(p[0], p[1], p[2], p[3], z));
	}

	//method declaration
	IFractalBuilder setRealComponentInterval(double min, double max);

	//method declaration
	IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);

	//method declaration
	IFractalBuilder setStartValues(IComplexNumber... startValues);
	
	//method declaration
	IFractalBuilder setStartValuesFunction(
		IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> startValuesFunction
	);
	
	//method declaration
	IFractalBuilder setWidthInPixel(int widthInPixel);
}
