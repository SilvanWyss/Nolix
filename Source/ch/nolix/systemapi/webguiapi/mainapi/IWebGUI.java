//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.coreapi.webapi.cssapi.ICSS;
import ch.nolix.systemapi.elementapi.styleapi.IStyleElement;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.canvasuniversalapi.ICanvas;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;
import ch.nolix.systemapi.guiapi.mainapi.IGUI;

//interface
public interface IWebGUI<WGUI extends IWebGUI<WGUI>>
extends Clearable, ICanvas<WGUI>, IGUI<WGUI>, IStyleElement<WGUI> {
	
	//method declaration
	@Override
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	IWebGUIContent getContent();
	
	//method declaration
	ICSS<?, ?> getCSS();
	
	//method declaration
	IContainer<IHTMLElementEvent> getHTMLElementEventRegistrations();
	
	//method declaration
	@Override
	IImage getIcon();
	
	//method declaration
	IControl<?, ?> getOriControlByInternalId(String fixedId);
	
	//method declaration
	IContainer<IControl<?, ?>> getOriControls();
	
	//method declaration
	IContainer<ILayer<?>> getOriLayers();
	
	//method declaration
	ILayer<?> getOriTopLayer();
	
	//method declaration
	boolean hasRemoveLayerAction();
	
	//method declaration
	@Override
	IFrontEndWriter onFrontEnd();
	
	//method declaration
	WGUI pushLayer(ILayer<?> layer);
	
	//method declaration
	WGUI pushLayerWithRootControl(IControl<?, ?> rootControl);
	
	//method declaration
	void removeLayer(ILayer<?> layer);
	
	//method declaration
	WGUI setFrontEndReaderAndFrontEndWriter(IFrontEndReader frontEndReader, IFrontEndWriter frontEndWriter);
	
	//method declaration
	@Override
	WGUI setIcon(IImage icon);
	
	//method declaration
	WGUI setRemoveLayerAction(IAction removeLayerAction);
}
