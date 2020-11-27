//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.math.BigDecimal;

//interface
public interface IComplexNumber {
	
	//method declaration
	int getBigDecimalScale();
	
	//method declaration
	IComplexNumber getConjugate();
	
	//method declaration
	BigDecimal getImaginaryComponent();
	
	//method declaration
	IComplexNumber getInBigDecimalScale(int bigDecimalScale);
	
	//method declaration
	BigDecimal getMagnitude();
	
	//method declaration
	IComplexNumber getPower(int exponent);
	
	//method declaration
	IComplexNumber getProduct(BigDecimal number);
	
	//method declaration
	IComplexNumber getProduct(double number);
	
	//method declaration
	IComplexNumber getProduct(IComplexNumber complexNumber);
	
	//method declaration
	BigDecimal getRealComponent();
	
	//method declaration
	int getScale();
	
	//method declaration
	IComplexNumber getPower2();
	
	//method declaration
	IComplexNumber getPower3();
	
	//method declaration
	IComplexNumber getPower4();
	
	//method declaration
	BigDecimal getSquaredMagnitude();
	
	//method declaration
	IComplexNumber getSum(BigDecimal number);
	
	//method declaration
	IComplexNumber getSum(double number);

	//method declaration
	IComplexNumber getSum(IComplexNumber complexNumber);
	
	//method declaration
	boolean isPureImaginary();
	
	//method declaration
	boolean isPureReal();
}
