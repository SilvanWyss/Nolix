package ch.nolix.systemapi.rawdataapi.datadtoapi;

import java.util.Optional;

public interface IContentFieldDto {

  String getColumnName();

  Optional<String> getOptionalValueAsString();
}
