//package declaration
package ch.nolix.techAPI.genericMathAPI;

//interface
public interface IImplicitSequence<N> extends ISequence<N> {
	
	//method declaration
	public abstract int getStartValuesCount();
	
	//method declaration
	public abstract N[] getStartValues();
}
