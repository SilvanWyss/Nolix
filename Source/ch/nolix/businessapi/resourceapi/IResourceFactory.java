//package declaration
package ch.nolix.businessapi.resourceapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IResourceFactory {
	
	//method declaration
	IResource create(String name, IResource... baseResources);
	
	//method declaration
	IResource create(String name, IContainer<IResource> baseResources);
}
