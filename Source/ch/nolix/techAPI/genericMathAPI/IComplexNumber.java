//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IComplexNumber {
	
	//abstract method
	public abstract int getBigDecimalScale();
	
	//abstract method
	public abstract IComplexNumber getConjugate();
	
	//abstract method
	public abstract BigDecimal getImaginaryComponent();
	
	//abstract method
	public abstract IComplexNumber getInBigDecimalScale(int bigDecimalScale);
	
	//abstract method
	public abstract BigDecimal getMagnitude();
	
	//abstract method
	public abstract IComplexNumber getPower(int exponent);
	
	//abstract method
	public abstract IComplexNumber getProduct(BigDecimal number);
	
	//abstract method
	public abstract IComplexNumber getProduct(double number);
	
	//abstract method
	public abstract IComplexNumber getProduct(IComplexNumber complexNumber);
	
	//abstract method
	public abstract BigDecimal getRealComponent();
	
	//abstract method
	public abstract int getScale();
	
	//abstract method
	public abstract IComplexNumber getPower2();
	
	//abstract method
	public abstract IComplexNumber getPower3();
	
	//abstract method
	public abstract IComplexNumber getPower4();
	
	//abstract method
	public abstract BigDecimal getSquaredMagnitude();
	
	//abstract method
	public abstract IComplexNumber getSum(BigDecimal number);
	
	//abstract method
	public abstract IComplexNumber getSum(double number);

	//abstract method
	public abstract IComplexNumber getSum(IComplexNumber complexNumber);
	
	//abstract method
	public abstract boolean isPureImaginary();
	
	//abstract method
	public abstract boolean isPureReal();
}
