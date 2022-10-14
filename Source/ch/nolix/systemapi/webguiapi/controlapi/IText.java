//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface IText<
	T extends IText<T, TS>,
	TS extends ITextStyle<TS>
> extends IControl<T, TS> {
	
	//method declaration
	String getText();
	
	//method declaration
	T setText(String text);
}
