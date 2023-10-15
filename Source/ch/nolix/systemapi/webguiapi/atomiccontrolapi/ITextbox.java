//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTextHolder;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITextbox extends IControl<ITextbox, ITextboxStyle>, IFluentMutableTextHolder<ITextbox> {

  // method declaration
  void emptyText();

  // method declaration
  TextMode getTextMode();

  // method declaration
  void removeUpdateTextAction();

  // method declaration
  ITextbox setTextMode(TextMode textMode);

  // method declaration
  ITextbox setUpdateTextAction(IAction updateTextAction);

  // method declaration
  ITextbox setUpdateTextAction(IElementTaker<String> updateTextAction);
}
