//package declaration
package ch.nolix.techAPI.genericMathAPI;

//interface
public interface IImplicitSequence<N> extends ISequence<N> {
	
	//abstract method
	public abstract int getStartValuesCount();
	
	//abstract method
	public abstract N[] getStartValues();
}
