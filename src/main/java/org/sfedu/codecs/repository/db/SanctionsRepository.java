package org.sfedu.codecs.repository.db;

import org.sfedu.codecs.model.db.ChangesEntity;
import org.sfedu.codecs.model.db.SanctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanctionsRepository extends JpaRepository<SanctionEntity, Long> {

}
