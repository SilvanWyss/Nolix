//package declaration
package ch.nolix.businessapi.dynamicmathapi;

//interface
public interface ISequenceDefinedBy2Predecessor<V> extends ISequence<V> {
	
	//method declaration
	V getFirstValue();
	
	//method declaration
	V getSecondValue();
}
