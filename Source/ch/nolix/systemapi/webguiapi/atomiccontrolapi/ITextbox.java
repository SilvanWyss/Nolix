//package declaration
package ch.nolix.systemapi.webguiapi.atomiccontrolapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTextHolder;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//interface
public interface ITextbox extends IControl<ITextbox, ITextboxStyle>, IFluentMutableTextHolder<ITextbox> {

  //method declaration
  void emptyText();

  //method declaration
  TextMode getTextMode();

  //method declaration
  void removeUpdateTextAction();

  //method declaration
  ITextbox setTextMode(TextMode textMode);

  //method declaration
  ITextbox setUpdateTextAction(Runnable updateTextAction);

  //method declaration
  ITextbox setUpdateTextAction(Consumer<String> updateTextAction);
}
