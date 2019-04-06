//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java imports
import java.math.BigDecimal;
import java.util.ArrayList;

//own imports
import ch.nolix.core.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;

//interface
public interface IFractal {
	
	//abstract method
	public abstract int getBigDecimalScale();
	
	//abstract method
	public abstract Color getColor(int index);
	
	//abstract method
	public abstract int getHeightInPixel();
	
	//abstract method
	public abstract IClosedInterval getImaginaryComponentInterval();	
	
	//abstract method
	public abstract BigDecimal getMaxImaginaryComponent();
	
	//abstract method
	public abstract BigDecimal getMaxRealComponent();	
	
	//abstract method
	public abstract BigDecimal getMinImaginaryComponent();
	
	//abstract method
	public abstract BigDecimal getMinRealComponent();
	
	//abstract method
	public abstract BigDecimal getPixelsPerUnit();
	
	//abstract method
	public abstract IClosedInterval getRealComponentInterval();
	
	//abstract method
	public abstract int getSequencesMaxIterationCount();
	
	//abstract method
	public abstract BigDecimal getSequencesMinDivergenceMagnitude();
	
	//abstract method
	public abstract I2ElementTakerElementGetter<ArrayList<IComplexNumber>, IComplexNumber, IComplexNumber>
	getSequencesNextValueFunction();
	
	//abstract method
	public abstract ArrayList<IComplexNumber> getSequencesStartValues(final IComplexNumber complexNumber);
	
	//abstract method
	public abstract BigDecimal getUnitsPerPixel();
	
	//abstract method
	public abstract int getWidthInPixel();
	
	//abstract method
	public abstract IImageBuilder startImageBuild();
	
	//abstract method
	public abstract Image toImage();
}
