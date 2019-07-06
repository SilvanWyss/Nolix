//package declaration
package ch.nolix.techAPI.genericMathAPI;

//interface
public interface IParametrizedImplicitSequenceCreator<N> {
	
	//abstract method
	public abstract ISequence<N> createSequence(N[] startValues);
	
	//abstract method
	public abstract int getSequencesStartIndex();
}
