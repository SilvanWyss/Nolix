//package declaration
package ch.nolix.businessapi.projectapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.generalskillapi.IFluentObject;
import ch.nolix.common.mutableattributeapi.Namable;
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IProject<P extends IProject<P>> extends Clearable<P>, IFluentObject<P>, Namable<P> {
	
	//method declaration
	P addTask(ITask task);
	
	//method
	default P addTask(final ITask... tasks) {
		
		for (final var t : tasks) {
			addTask(t);
		}
		
		return asConcrete();
	}
	
	//method
	default P addTasks(final Iterable<ITask> tasks) {
		
		tasks.forEach(this::addTask);
		
		return asConcrete();
	}
	
	//method declaration
	IContainer<ITask> getRefTasks();
	
	//method declaration
	void removeTask(ITask task);
}
