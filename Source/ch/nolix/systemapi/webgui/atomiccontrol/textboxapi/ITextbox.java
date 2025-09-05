package ch.nolix.systemapi.webgui.atomiccontrol.textboxapi;

import java.util.function.Consumer;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.IControl;

public interface ITextbox extends IControl<ITextbox, ITextboxStyle>, IFluentMutableTextHolder<ITextbox> {
  void emptyText();

  TextMode getTextMode();

  void removeUpdateTextAction();

  ITextbox setTextMode(TextMode textMode);

  ITextbox setUpdateTextAction(Runnable updateTextAction);

  ITextbox setUpdateTextAction(Consumer<String> updateTextAction);
}
