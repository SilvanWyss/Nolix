/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.middata.adapter;

import ch.nolix.systemapi.middata.loader.IDataReader;

/**
 * @author Silvan Wyss
 */
public interface DataAdapter extends IDataReader, IDataWriter {
  // This interface is a dedicated union of other interfaces.
}
