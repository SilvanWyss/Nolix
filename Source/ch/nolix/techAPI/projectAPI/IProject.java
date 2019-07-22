//package declaration
package ch.nolix.techAPI.projectAPI;

//own imports
import ch.nolix.core.attributeAPI.Namable;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.skillAPI.Resettable;
import ch.nolix.element.baseAPI.IElement;

//interface
public interface IProject extends Clearable<IProject>, IElement, Namable<IProject>, Resettable<IProject> {
	
	//abstract method
	public abstract IProject addTask(ITask task);
	
	//default method
	public default IProject addTask(final ITask... tasks) {
		
		for (final var t : tasks) {
			addTask(t);
		}
		
		return this;
	}
	
	//abstract method
	public abstract IContainer<ITask> getRefTasks();
	
	//abstract method
	public abstract IProject removeTask(ITask task);
}
