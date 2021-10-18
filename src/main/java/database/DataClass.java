package database;

import java.util.List;

public interface DataClass<K> {
    DataClass<K> fromRow(List<String> row);

    List<String> toRow();
}
