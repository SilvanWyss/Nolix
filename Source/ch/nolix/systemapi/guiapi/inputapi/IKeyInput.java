//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

//interface
public interface IKeyInput<KI extends IKeyInput<KI>> extends IInput<KI> {
	
	//method declaration
	Key getKey();
	
	//method declaration
	KeyInputType getKeyInputType();
}
