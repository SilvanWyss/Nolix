//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.IFluentMutableTextHolder;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITextbox<
	T extends ITextbox<T, TS>,
	TS extends ITextboxStyle<TS>
>
extends IControl<T, TS>, IFluentMutableTextHolder<T> {
	
	//method declaration
	void emptyText();
	
	//method declaration
	TextMode getTextMode();
	
	//method declaration
	void removeUpdateTextAction();
	
	//method declaration
	T setTextMode(TextMode textMode);
	
	//method declaration
	T setUpdateTextAction(IAction updateTextAction);
	
	//method declaration
	T setUpdateTextAction(IElementTaker<String> updateTextAction);
}
