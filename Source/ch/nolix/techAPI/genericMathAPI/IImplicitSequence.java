//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.util.ArrayList;

//interface
public interface IImplicitSequence<N> extends ISequence<N> {
	
	//abstract method
	public abstract int getStartValuesCount();
	
	//abstract method
	public abstract ArrayList<N> getStartValues();
}
