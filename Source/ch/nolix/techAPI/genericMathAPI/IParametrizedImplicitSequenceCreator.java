//package declaration
package ch.nolix.techAPI.genericMathAPI;

//Java import
import java.util.ArrayList;

//interface
public interface IParametrizedImplicitSequenceCreator<N> {
	
	//abstract method
	public abstract ISequence<N> createSequence(ArrayList<N> startValues);
	
	//abstract method
	public abstract int getSequencesStartIndex();
}
