package org.sfedu.codecs.repository.db;

import org.sfedu.codecs.model.db.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    public List<RecordEntity> getByParentRecordId(Long parentId);
}
