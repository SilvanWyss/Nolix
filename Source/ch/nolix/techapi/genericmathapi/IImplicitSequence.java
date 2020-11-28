//package declaration
package ch.nolix.techapi.genericmathapi;

//interface
public interface IImplicitSequence<N> extends ISequence<N> {
	
	//method declaration
	int getStartValuesCount();
	
	//method declaration
	N[] getStartValues();
}
