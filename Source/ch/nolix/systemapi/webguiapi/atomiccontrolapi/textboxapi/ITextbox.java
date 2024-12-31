package ch.nolix.systemapi.webguiapi.atomiccontrolapi.textboxapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTextHolder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface ITextbox extends IControl<ITextbox, ITextboxStyle>, IFluentMutableTextHolder<ITextbox> {

  void emptyText();

  TextMode getTextMode();

  void removeUpdateTextAction();

  ITextbox setTextMode(TextMode textMode);

  ITextbox setUpdateTextAction(Runnable updateTextAction);

  ITextbox setUpdateTextAction(Consumer<String> updateTextAction);
}
