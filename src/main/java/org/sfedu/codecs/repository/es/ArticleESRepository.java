package org.sfedu.codecs.repository.es;

import org.sfedu.codecs.model.es.ArticleDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleESRepository extends ElasticsearchCrudRepository<ArticleDoc,String> {
}
