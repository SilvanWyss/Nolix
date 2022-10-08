//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITextbox<
	T extends ITextbox<T, TS>,
	TS extends ITextboxStyle<TS>
> extends IControl<T, TS> {
	
	//method declaration
	void emptyText();
	
	//method declaration
	String getText();
	
	//method declaration
	TextMode getTextMode();
	
	//method declaration
	T setText(String text);
	
	//method declaration
	T setTextMode(TextMode textMode);
}
