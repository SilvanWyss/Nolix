//package declaration
package ch.nolix.techapi.genericmathapi;

//interface
public interface IParametrizedImplicitSequenceCreator<N> {
	
	//method declaration
	ISequence<N> createSequence(N[] startValues);
	
	//method declaration
	int getSequencesStartIndex();
}
