//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Titleble;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.guiapi.canvasapi.ICanvas;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndWriter;

//interface
public interface IWebGUI<WGUI extends IWebGUI<WGUI>> extends ICanvas<WGUI>, Titleble<WGUI> {
	
	//method declaration
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	IWebGUIContent getContent();
	
	//method declaration
	IImage getIcon();
	
	//method declaration
	IContainer<ILayer<?>> getRefLayers();
	
	//method declaration
	ILayer<?> getRefTopLayer();
	
	//method declaration
	IFrontEndWriter onFrontEnd();
	
	//method declaration
	WGUI pushLayer(ILayer<?> layer);
	
	//method declaration
	WGUI pushLayerWithRootControl(IControl<?, ?> rootControl);
	
	//method declaration
	void removeLayer(ILayer<?> layer);
	
	//method declaration
	WGUI setIcon(IImage icon);
}
