//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//own imports
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
	public abstract IFractalBuilder setImaginaryComponentInterval(IClosedInterval imaginaryComponentInterval);
		
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
		ITwoElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> sequenceNextValueFunction
	);
	
	//abstract method
	public abstract IFractalBuilder setSequencesStartValue(IComplexNumber sequencesStartValue);
	
	//abstract method
	public abstract IFractalBuilder setWidthInPixel(int widthInPixel);
}
