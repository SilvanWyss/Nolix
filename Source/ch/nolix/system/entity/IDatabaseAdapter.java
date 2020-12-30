//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.skillapi.IChangesSaver;

//interface
/**
 * Important: A {@link IDatabaseAdapter} must fulfill the following requirements.
 * This is important for a proper database.
 * 
 * What is a {@link IDatabaseAdapter}?
 * A {@link IDatabaseAdapter} is an adapter to a database.
 * A {@link IDatabaseAdapter} can read and write objects to a database.
 * Those objects are {@link Entity}s.
 * All business data a {@link IDatabaseAdapter} reads or writes to a database are {@link Entity}s.
 * 
 * What can a {@link Entity} store?
 * Everything a {@link Entity} can store is a {@link Property}.
 * 
 * What categories of {@link Property} exist?
 * There exist 3 general categories of {@link Property}s.
 * -{@link BaseValueProperty}: Can store 1 or several values of a certain type.
 * -{@link BaseReference}: Can reference 1 or several other {@link Entity}s of a certain type.
 * -{@link BaseBackReference}: References back the {@link BaseReference} that references the current {@link Entity}.
 *  A {@link BaseBackReference} is not changeable directly. A {@link BackReference} is edited by {@link BaseReference}s.
 * 
 * What resources does a {@link IDatabaseAdapter} need?
 * A {@link IDatabaseAdapter} needs to be connected to a database at first.
 * The place where to find the database is preferably given into the constructor of a {@link IDatabaseAdapter}.
 * 
 * How does a {@link IDatabaseAdapter} principally read and write data?
 * A {@link IDatabaseAdapter} stores the {@link Entity}s it should read, create, edit or delete in the memory.
 * A {@link IDatabaseAdapter} does not store the whole database in the memory.
 * A {@link IDatabaseAdapter} loads the needed {@link Entity}s in background and lazily.
 * A {@link IDatabaseAdapter} does not store changes to a database immediately.
 * A {@link IDatabaseAdapter} stores all its changes when the {@link IDatabaseAdapter#saveChanges} is called.
 * 
 * Generally, what needs to be validated and when?
 * -A {@link BaseValueProperty} validates its values immediately when it is edited.
 * -A {@link BaseReference} validates its references immediately when it is edited.
 * -A {@link Entity} validates itself when it saves its changes into a database.
 *  At this point, a {@link Entity} validates also
 *  if each of its {@link BaseBackReference} has a valid count of back references.
 *  Because a {@link BaseBackReference}
 *  cannot not validate meaningfully its count of back references before being saved.
 * 
 * What does a {@link Entity} need to provide when it is set as deleted.
 * -1. The {@link Entity} throws an Exception if it is referenced by another {@link Entity}.
 * -2. The {@link Entity} updates for each of its {@link BaseReference} its {@link BaseBackReference}.
 * 
 * What does a {@link IDatabaseAdapter} need to provide when it saves the changes of a {@link Entity} into a database?
 * -The {@link Entity} is new:
 * --1. The database table does not have another {@link Entity} with the same id as the {@link Entity}.
 *      Because the id of a {@link Entity} is generated and has a small risk to equal another id.
 * --2. Each {@link BaseValueProperty} of the {@link Entity} has a valid count of values.
 * --3. Each {@link BaseReference} of the {@link Entity} has a valid count of references.
 * --4. Each {@link BaseBackReference} of the {@link Entity} has a valid count of back references.
 * --5. The {@link Entity} is set as rejected at the end of the transaction.
 * -The {@link Entity} is persisted:
 * --1. The {@link Entity} is set as rejected at the end of the transaction.
 * -The {@link Entity} is edited:
 * --1. The timestamp of the {@link Entity} in the database
 *      is not newer than the creation time of the current {@link IDatabaseAdapter}.
 * --2. Each {@link BaseBackReference} of the {@link Entity} has a valid count of back references.
 * --3. The {@link Entity} is set as rejected at the end of the transaction.
 * -The {@link Entity} is deleted:
 * --1. The timestamp of the {@link Entity} in the database
 *      is not newer than the creation time of the current {@link IDatabaseAdapter}.
 * --2. The {@link Entity} is set as rejected at the end of the transaction.
 * -The {@link Entity} is rejected:
 * --1. An Exception is thrown.
 * 
 * What does a {@link Property} need to provide when it is edited?
 * -When the {@link Property} is a {@link BaseValueProperty}:
 * --1. The {@link BaseValueProperty} has valid values.
 * --2. The {@link BaseValueProperty} notifies its parent {@link Entity} that it may be edited.
 * -When the {@link Property} is a {@link BaseReference}:
 * --1. The {@link BaseReference} has valid references.
 * --2. The {@link BaseReference} updates each of its former {@link BaseBackReference}.
 * --3. The {@link BaseReference} updates each of its newly {@link BaseBackReference}.
 * --4. The {@link BaseValueProperty} notifies its parent {@link Entity} that it may be edited.
 * -When the {@link Property} is a {@link BaseBackReference}:
 * --1. The {@link BaseBackReference} validates its back references
 *      without (!) validating the count of its back references.
 *      Because a {@link BaseBackReference}
 *      cannot not validate meaningfully its count of back references before being saved.
 * --2. The {@link BaseBackReference} notifies its parent {@link Entity} that it may be edited.
 * 
 * @author Silvan Wyss
 * @month 2019-11
 * @lines 120
 */
public interface IDatabaseAdapter extends IChangesSaver<IDatabaseAdapter>  {
	
	//method declaration
	/**
	 * @param type
	 * @param specification
	 * @return a new value if the given type from the given specification.
	 */
	<V> V createValueFromSpecification(Class<V> type, BaseNode specification);
	
	//method declaration
	/**
	 * @param type
	 * @return the {@link IEntitySet} of the given type from the current {@link IDatabaseAdapter}.
	 */
	<E extends Entity> IEntitySet<E> getRefEntitySet(Class<E> type);
	
	//method declaration
	/**
	 * @return the {@link IEntitySet}s of the current {@link IDatabaseAdapter}.
	 */
	<ES extends IEntitySet<Entity>> IContainer<ES> getRefEntitySets();
}
