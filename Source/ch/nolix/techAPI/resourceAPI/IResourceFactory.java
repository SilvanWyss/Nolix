//package declaration
package ch.nolix.techAPI.resourceAPI;

//own import
import ch.nolix.common.containers.IContainer;

//interface
public interface IResourceFactory {
	
	//method declaration
	public abstract IResource create(String name, IResource... baseResources);
	
	//method declaration
	public abstract IResource create(String name, IContainer<IResource> baseResources);
}
