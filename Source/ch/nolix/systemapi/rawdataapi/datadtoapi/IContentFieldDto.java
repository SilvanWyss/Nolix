//package declaration
package ch.nolix.systemapi.rawdataapi.datadtoapi;

//Java imports
import java.util.Optional;

//interface
public interface IContentFieldDto {

  //method declaration
  String getColumnName();

  //method declaration
  Optional<String> getOptionalValueAsString();
}
