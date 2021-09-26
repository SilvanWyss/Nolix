//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.IdentifiedByString;
import ch.nolix.common.attributeapi.mandatoryattributeapi.ShortDescripted;
import ch.nolix.techapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IEntity<E extends IEntity<E>>
extends Deletable, IDatabaseObject, IdentifiedByString, ShortDescripted {}
