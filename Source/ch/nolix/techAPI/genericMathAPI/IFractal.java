//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;

//interface
public interface IFractal {
	
	//method declaration
	public abstract int getBigDecimalScale();
	
	//method declaration
	public abstract Color getColor(int index);
	
	//method declaration
	public abstract int getHeightInPixel();
	
	//method declaration
	public abstract IClosedInterval getImaginaryComponentInterval();	
	
	//method declaration
	public abstract BigDecimal getMaxImaginaryComponent();
	
	//method declaration
	public abstract int getMaxIterationCount();

	//method declaration
	public abstract BigDecimal getMaxRealComponent();	
	
	//method declaration
	public abstract BigDecimal getMinImaginaryComponent();
	
	//method declaration
	public abstract BigDecimal getMinMagnitudeForConvergence();

	//method declaration
	public abstract BigDecimal getMinRealComponent();
	
	//method declaration
	public abstract I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	getNextValueFunction();

	//method declaration
	public abstract BigDecimal getPixelsPerUnit();
	
	//method declaration
	public abstract IClosedInterval getRealComponentInterval();
	
	//method declaration
	public abstract IComplexNumber[] getStartValues(final IComplexNumber complexNumber);
	
	//method declaration
	public abstract BigDecimal getUnitsPerPixel();
	
	//method declaration
	public abstract int getWidthInPixel();
	
	//method declaration
	public abstract IImageBuilder startImageBuild();
	
	//method declaration
	public abstract Image toImage();
}
