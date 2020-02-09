package org.sfedu.codecs.repository.db;

import org.sfedu.codecs.model.db.SanctionChangesEntity;
import org.sfedu.codecs.model.db.SanctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionsChangeRepository extends JpaRepository<SanctionChangesEntity, Long> {

}
