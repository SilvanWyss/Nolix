/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.counterpart;

import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public interface IUpdateCommandCreator {
  IChainedNode createSetCssCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetEventFunctionsCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandForIcon(IImage icon);

  IChainedNode createSetRootHtmlElementCommandFromControl(IControl<?, ?> control);

  IChainedNode createSetRootHtmlElementCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandFromWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandForTitle(String title);

  IChainedNode createSetUserInputFunctionsCommandFromWebGui(IWebGui<?> webGui);
}
