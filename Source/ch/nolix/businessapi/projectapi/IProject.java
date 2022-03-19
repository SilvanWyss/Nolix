//package declaration
package ch.nolix.businessapi.projectapi;

//own imports
import ch.nolix.core.attributeapi.mutablemandatoryattributeapi.Namable;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.generalskillapi.IFluentObject;
import ch.nolix.core.skillapi.Clearable;

//interface
public interface IProject extends Clearable, IFluentObject<IProject>, Namable<IProject> {
	
	//method declaration
	IProject addTask(ITask task);
	
	//method
	default IProject addTask(final ITask... tasks) {
		
		for (final var t : tasks) {
			addTask(t);
		}
		
		return this;
	}
	
	//method
	default IProject addTasks(final Iterable<ITask> tasks) {
		
		tasks.forEach(this::addTask);
		
		return this;
	}
	
	//method declaration
	IContainer<ITask> getRefTasks();
	
	//method declaration
	void removeTask(ITask task);
}
