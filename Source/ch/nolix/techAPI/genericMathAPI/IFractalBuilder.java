//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java imports
import java.math.BigDecimal;
import java.util.ArrayList;

//own imports
import ch.nolix.core.functionAPI.I3ElementTakerElementGetter;
import ch.nolix.core.functionAPI.I4ElementTakerElementGetter;
import ch.nolix.core.functionAPI.I5ElementTakerElementGetter;
import ch.nolix.core.functionAPI.IIntTakerElementGetter;
import ch.nolix.core.functionAPI.ITwoElementTakerElementGetter;
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
	public abstract IFractalBuilder setRealComponentInterval(double min, double max);
	
	//abstract method
	public abstract IFractalBuilder setRealComponentInterval(IClosedInterval realComponentInterval);
	
	//abstract method
	public abstract IFractalBuilder setSequencesMaxIterationCount(int sequencesMaxIterationCount);
	
	//abstract method
	public abstract IFractalBuilder setSequencesMinDivergenceMagnitude(BigDecimal sequencesMinDivergenceMagnitude);
	
	//abstract method
	public abstract IFractalBuilder setSequencesMinDivergenceMagnitude(double sequencesMinDivergenceMagnitude);
	
	//abstract method
	public abstract IFractalBuilder setSequencesNextValueFunction(
		ITwoElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber, IComplexNumber>
		sequenceNextValueFunction
	);
	
	//default method
	public default IFractalBuilder setSequencesNextValueFunctionFor1Predecessor(
		final ITwoElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber>
		sequenceNextValueFunction
	) {
		return setSequencesNextValueFunction((p, c) -> sequenceNextValueFunction.getOutput(p.get(0), c));
	}
	
	//default method
	public default IFractalBuilder setSequencesNextValueFunctionFor2Predecessor(
		final I3ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		sequenceNextValueFunction
	) {
		return setSequencesNextValueFunction((p, c) -> sequenceNextValueFunction.getOutput(p.get(0), p.get(1), c));
	}
	
	//default method
	public default IFractalBuilder setSequencesNextValueFunctionFor3Predecessor(
		final I4ElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber, IComplexNumber>
		sequenceNextValueFunction
	) {
		return
		setSequencesNextValueFunction((p, c) -> sequenceNextValueFunction.getOutput(p.get(0), p.get(1), p.get(2), c));
	}
	
	//default method
	public default IFractalBuilder setSequencesNextValueFunctionFor4Predecessor(
		I5ElementTakerElementGetter<
			IComplexNumber,
			IComplexNumber,
			IComplexNumber,
			IComplexNumber,
			IComplexNumber,
			IComplexNumber
		>
		sequenceNextValueFunction
	) {
		return
		setSequencesNextValueFunction(
			(p, z) -> sequenceNextValueFunction.getOutput(p.get(0), p.get(1), p.get(2), p.get(3), z)
		);
	}
	
	//abstract method
	public abstract IFractalBuilder setSequencesStartValues(IComplexNumber... sequencesStartValues);
	
	//abstract method
	public abstract IFractalBuilder setWidthInPixel(int widthInPixel);
}
