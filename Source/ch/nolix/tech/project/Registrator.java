//package declaration
package ch.nolix.tech.project;

//own imports
import ch.nolix.core.instanceProvider.CentralInstanceProvider;
import ch.nolix.techAPI.projectAPI.IProject;
import ch.nolix.techAPI.projectAPI.ITask;

//class
public class Registrator {
	
	//static method
	public static void register() {
		CentralInstanceProvider
		.register(IProject.class, Project.class)
		.register(ITask.class, Task.class);
	}
	
	//private constructor
	private Registrator() {}
}
