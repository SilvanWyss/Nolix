//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//interface
public interface IImplicitSequence<N> extends OldISequence<N> {
	
	//method declaration
	int getStartValuesCount();
	
	//method declaration
	N[] getStartValues();
}
