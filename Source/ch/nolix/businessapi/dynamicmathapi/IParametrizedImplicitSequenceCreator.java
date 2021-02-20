//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//interface
public interface IParametrizedImplicitSequenceCreator<N> {
	
	//method declaration
	ISequence<N> createSequence(N[] startValues);
	
	//method declaration
	int getSequencesStartIndex();
}
