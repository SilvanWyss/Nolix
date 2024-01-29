//package declaration
package ch.nolix.systemapi.rawdatabaseapi.databasedtoapi;

//Java imports
import java.util.Optional;

//interface
public interface ILoadedContentFieldDto {

  //method declaration
  String getColumnName();

  //method declaration
  Optional<Object> getOptionalValue();
}
