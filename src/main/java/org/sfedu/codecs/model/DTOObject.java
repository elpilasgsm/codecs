package org.sfedu.codecs.model;

import java.io.Serializable;

public interface DTOObject<T extends IDBObject> extends Serializable {
    T toDB(boolean deepCopy);

    default T toDB() {
        return toDB(false);
    }

}
