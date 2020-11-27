//package declaration
package ch.nolix.techAPI.genericMathAPI;

//interface
public interface IParametrizedImplicitSequenceCreator<N> {
	
	//method declaration
	ISequence<N> createSequence(N[] startValues);
	
	//method declaration
	int getSequencesStartIndex();
}
