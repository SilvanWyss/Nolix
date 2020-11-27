//package declaration
package ch.nolix.tech.project;

//own imports
import ch.nolix.common.instanceProvider.CentralInstanceProvider;
import ch.nolix.techAPI.projectAPI.IProject;
import ch.nolix.techAPI.projectAPI.ITask;

//class
public final class Registrator {
	
	//static method
	public static void register() {
		CentralInstanceProvider
		.register(IProject.class, Project.class)
		.register(ITask.class, Task.class);
	}
	
	//visibility-reduced constructor
	private Registrator() {}
}
