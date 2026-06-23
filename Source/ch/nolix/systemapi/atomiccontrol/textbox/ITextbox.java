/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.atomiccontrol.textbox;

import java.util.function.Consumer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ITextbox extends Control<ITextbox, ITextboxStyle>, FluentMutableTextHolder<ITextbox> {
  void emptyText();

  TextMode getTextMode();

  void removeUpdateTextAction();

  ITextbox setTextMode(TextMode textMode);

  ITextbox setUpdateTextAction(Runnable updateTextAction);

  ITextbox setUpdateTextAction(Consumer<String> updateTextAction);
}
