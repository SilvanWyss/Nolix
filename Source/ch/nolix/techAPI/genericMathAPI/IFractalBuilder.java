//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.common.functionAPI.I3ElementTakerElementGetter;
import ch.nolix.common.functionAPI.I4ElementTakerElementGetter;
import ch.nolix.common.functionAPI.I5ElementTakerElementGetter;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.functionAPI.IIntTakerElementGetter;
import ch.nolix.element.color.Color;

//interface
public interface IFractalBuilder {
	
	//method declaration
	public abstract IFractal build();
	
	//method declaration
	public abstract IFractalBuilder setBigDecimalScale(int bigDecumalScale);
	
	//method declaration
	public abstract IFractalBuilder setColorFunction(IIntTakerElementGetter<Color> colorFunction);
	
	//method declaration
	public abstract IFractalBuilder setImaginaryComponentInterval(double min, double max);
	
	//method declaration
	public abstract IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);
	
	//method declaration
	public abstract IFractalBuilder setMaxIterationCount(int maxIterationCount);

	//method declaration
	public abstract IFractalBuilder setMinMagnitudeForConvergence(BigDecimal minMagnitudeForConvergence);

	//method declaration
	public abstract IFractalBuilder setMinMagnitudeForConvergence(double minMagnitudeForConvergence);
	
	//method declaration
	public abstract IFractalBuilder setNextValueFunction(
		I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber> nextValueFunction
	);
	
	//method
	public default IFractalBuilder setNextValueFunctionFor1Predecessor(
		final I2ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], c));
	}
	
	//method
	public default IFractalBuilder setNextValueFunctionFor2Predecessor(
		final I3ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], p[1], c));
	}
	
	//method
	public default IFractalBuilder setNextValueFunctionFor3Predecessor(
		final I4ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		nextValueFunction
	) {
		return setNextValueFunction((p, c) -> nextValueFunction.getOutput(p[0], p[1], p[2], c));
	}

	//method
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

	//method declaration
	public abstract IFractalBuilder setRealComponentInterval(double min, double max);

	//method declaration
	public abstract IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);

	//method declaration
	public abstract IFractalBuilder setStartValues(IComplexNumber... startValues);
	
	//method declaration
	public abstract IFractalBuilder setStartValuesFunction(
		IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> startValuesFunction
	);
	
	//method declaration
	public abstract IFractalBuilder setWidthInPixel(int widthInPixel);
}
