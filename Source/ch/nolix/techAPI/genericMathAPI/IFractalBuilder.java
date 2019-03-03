//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java imports
import java.math.BigDecimal;
import java.util.ArrayList;

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
		ITwoElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber, IComplexNumber>
		sequenceNextValueFunction
	);
	
	//abstract method
	public abstract IFractalBuilder setSequencesStartValues(ArrayList<IComplexNumber> sequencesStartValues);
	
	//abstract method
	public abstract IFractalBuilder setWidthInPixel(int widthInPixel);
}
