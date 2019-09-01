//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.functionAPI.I2ElementTakerElementGetter;
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
	public abstract int getMaxIterationCount();

	//abstract method
	public abstract BigDecimal getMaxRealComponent();	
	
	//abstract method
	public abstract BigDecimal getMinImaginaryComponent();
	
	//abstract method
	public abstract BigDecimal getMinMagnitudeForConvergence();

	//abstract method
	public abstract BigDecimal getMinRealComponent();
	
	//abstract method
	public abstract I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	getNextValueFunction();

	//abstract method
	public abstract BigDecimal getPixelsPerUnit();
	
	//abstract method
	public abstract IClosedInterval getRealComponentInterval();
	
	//abstract method
	public abstract IComplexNumber[] getStartValues(final IComplexNumber complexNumber);
	
	//abstract method
	public abstract BigDecimal getUnitsPerPixel();
	
	//abstract method
	public abstract int getWidthInPixel();
	
	//abstract method
	public abstract IImageBuilder startImageBuild();
	
	//abstract method
	public abstract Image toImage();
}
