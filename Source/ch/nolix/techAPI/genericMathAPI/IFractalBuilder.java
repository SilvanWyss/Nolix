//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.common.functionAPI.I3ElementTakerElementGetter;
import ch.nolix.common.functionAPI.I4ElementTakerElementGetter;
import ch.nolix.common.functionAPI.I5ElementTakerElementGetter;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.functionAPI.IIntTakerElementGetter;
import ch.nolix.element.color.Color;

//interface
public interface IFractalBuilder {
	
	//abstract method
	public abstract IFractal build();
	
	//abstract method
	public abstract IFractalBuilder setBigDecimalScale(int bigDecumalScale);
	
	//abstract method
	public abstract IFractalBuilder setColorFunction(IIntTakerElementGetter<Color> colorFunction);
	
	//abstract method
	public abstract IFractalBuilder setImaginaryComponentInterval(double min, double max);
	
	//abstract method
	public abstract IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);
	
	//abstract method
	public abstract IFractalBuilder setMaxIterationCount(int maxIterationCount);

	//abstract method
	public abstract IFractalBuilder setMinMagnitudeForConvergence(BigDecimal minMagnitudeForConvergence);

	//abstract method
	public abstract IFractalBuilder setMinMagnitudeForConvergence(double minMagnitudeForConvergence);
	
	//abstract method
	public abstract IFractalBuilder setNextValueFunction(
		I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber> nextValueFunction
	);
	
	//default method
	public default IFractalBuilder setNextValueFunctionFor1Predecessor(
		final I2ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], c));
	}
	
	//default method
	public default IFractalBuilder setNextValueFunctionFor2Predecessor(
		final I3ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], p[1], c));
	}
	
	//default method
	public default IFractalBuilder setNextValueFunctionFor3Predecessor(
		final I4ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], p[1], p[2], c));
	}

	//default method
	public default IFractalBuilder setNextValueFunctionFor4Predecessor(
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

	//abstract method
	public abstract IFractalBuilder setRealComponentInterval(double min, double max);

	//abstract method
	public abstract IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);

	//abstract method
	public abstract IFractalBuilder setStartValues(IComplexNumber... startValues);
	
	//abstract method
	public abstract IFractalBuilder setStartValuesFunction(
		IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> startValuesFunction
	);
	
	//abstract method
	public abstract IFractalBuilder setWidthInPixel(int widthInPixel);
}
