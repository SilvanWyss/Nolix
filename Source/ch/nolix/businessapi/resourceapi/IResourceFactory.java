//package declaration
package ch.nolix.businessapi.resourceapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IResourceFactory {
	
	//method declaration
	IResource create(String name, IResource... baseResources);
	
	//method declaration
	IResource create(String name, IContainer<IResource> baseResources);
}
