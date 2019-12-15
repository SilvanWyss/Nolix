//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IComplexNumber {
	
	//method declaration
	public abstract int getBigDecimalScale();
	
	//method declaration
	public abstract IComplexNumber getConjugate();
	
	//method declaration
	public abstract BigDecimal getImaginaryComponent();
	
	//method declaration
	public abstract IComplexNumber getInBigDecimalScale(int bigDecimalScale);
	
	//method declaration
	public abstract BigDecimal getMagnitude();
	
	//method declaration
	public abstract IComplexNumber getPower(int exponent);
	
	//method declaration
	public abstract IComplexNumber getProduct(BigDecimal number);
	
	//method declaration
	public abstract IComplexNumber getProduct(double number);
	
	//method declaration
	public abstract IComplexNumber getProduct(IComplexNumber complexNumber);
	
	//method declaration
	public abstract BigDecimal getRealComponent();
	
	//method declaration
	public abstract int getScale();
	
	//method declaration
	public abstract IComplexNumber getPower2();
	
	//method declaration
	public abstract IComplexNumber getPower3();
	
	//method declaration
	public abstract IComplexNumber getPower4();
	
	//method declaration
	public abstract BigDecimal getSquaredMagnitude();
	
	//method declaration
	public abstract IComplexNumber getSum(BigDecimal number);
	
	//method declaration
	public abstract IComplexNumber getSum(double number);

	//method declaration
	public abstract IComplexNumber getSum(IComplexNumber complexNumber);
	
	//method declaration
	public abstract boolean isPureImaginary();
	
	//method declaration
	public abstract boolean isPureReal();
}
