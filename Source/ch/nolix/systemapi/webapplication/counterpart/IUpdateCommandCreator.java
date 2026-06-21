/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.counterpart;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public interface IUpdateCommandCreator {
  IChainedNode createSetCssCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetEventFunctionsCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandForIcon(IImage icon);

  IChainedNode createSetRootHtmlElementCommandForControl(IControl<?, ?> control);

  IChainedNode createSetRootHtmlElementCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandForTitle(String title);

  IChainedNode createSetUserInputFunctionsCommandForWebGui(IWebGui<?> webGui);

  ExtendedIterable<IChainedNode> createUpdateCommandsForControls(
    ExtendedIterable<IControl<?, ?>> controls,
    boolean updateConstellationOrStyle);

  ExtendedIterable<IChainedNode> createUpdateCommandsForWebGui(IWebGui<?> webGui);
}
