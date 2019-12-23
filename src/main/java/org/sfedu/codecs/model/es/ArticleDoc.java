package org.sfedu.codecs.model.es;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(type = "article",indexName = "article")
public class ArticleDoc implements Serializable {
    private static final long serialVersionUID = -2337653684608401065L;

    @Id
    @org.springframework.data.annotation.Id
    private String id;
    
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
