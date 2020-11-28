//package declaration
package ch.nolix.techapi.resourceapi;

import ch.nolix.common.container.IContainer;

//interface
public interface IResourceFactory {
	
	//method declaration
	IResource create(String name, IResource... baseResources);
	
	//method declaration
	IResource create(String name, IContainer<IResource> baseResources);
}
