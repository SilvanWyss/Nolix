//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

//own imports
import ch.nolix.systemapi.guiapi.processproperty.KeyInputType;

//interface
public interface IKeyInput<KI extends IKeyInput<KI>> extends IInput<KI> {
	
	//method declaration
	Key getKey();
	
	//method declaration
	KeyInputType getKeyInputType();
}
