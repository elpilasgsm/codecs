package org.sfedu.codecs.model;

import java.io.Serializable;

public interface IDBObject<T extends DTOObject> extends Serializable {
    T toDTO(boolean deepCopy);
}
