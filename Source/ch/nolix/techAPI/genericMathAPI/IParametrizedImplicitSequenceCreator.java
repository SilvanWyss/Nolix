//package declaration
package ch.nolix.techAPI.genericMathAPI;

//interface
public interface IParametrizedImplicitSequenceCreator<N> {
	
	//method declaration
	public abstract ISequence<N> createSequence(N[] startValues);
	
	//method declaration
	public abstract int getSequencesStartIndex();
}
