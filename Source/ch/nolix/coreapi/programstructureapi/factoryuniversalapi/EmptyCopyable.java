//package declaration
package ch.nolix.coreapi.programstructureapi.factoryuniversalapi;

//interface
public interface EmptyCopyable<EC extends EmptyCopyable<EC>> {
	
	//method declaration
	EC getEmptyCopy();
}
