package org.sfedu.codecs.controller;

import org.sfedu.codecs.model.db.ChangesEntity;
import org.sfedu.codecs.model.db.RecordEntity;
import org.sfedu.codecs.model.db.SanctionChangesEntity;
import org.sfedu.codecs.model.dto.ArticleRecord;
import org.sfedu.codecs.model.dto.ChangesRecord;
import org.sfedu.codecs.model.dto.SanctionChangesRecord;
import org.sfedu.codecs.repository.db.ChangesRepository;
import org.sfedu.codecs.repository.db.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/api/change/")
public class ChangesRestController {

    private final RecordRepository recordRepository;
    private final ChangesRepository changesRepository;

    @Autowired
    ChangesRestController(RecordRepository recordRepository, ChangesRepository changesRepository) {
        this.recordRepository = recordRepository;
        this.changesRepository = changesRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ChangesRecord put(@RequestBody ChangesRecord record, HttpServletResponse response) throws IOException {
        ChangesEntity entity = record.toDB(false);
        if (record.getRecord() == null || record.getRecord().getRecordId() == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        RecordEntity p = recordRepository.getOne(record.getRecord().getRecordId());
        entity.setRecord(p);
        entity.setPrimarySanctions(toDB(record.getPrimarySanctions(), entity, true));
        entity.setAlternateSanctions(toDB(record.getAlternateSanctions(), entity, false));
        return changesRepository.save(entity).toDTO(false);
    }

    private List<SanctionChangesEntity> toDB(List<SanctionChangesRecord> records, ChangesEntity changesEntity, boolean primary) {
        List<SanctionChangesEntity> toDB = new ArrayList<>();
        if (!CollectionUtils.isEmpty(records)) {
            toDB.addAll(records
                    .stream()
                    .map(it -> {
                        SanctionChangesEntity entity = it.toDB();
                        if (primary) {
                            entity.setChangesEntity(changesEntity);
                        } else {
                            entity.setAlternateChanges(changesEntity);
                        }
                        return entity;
                    })
                    .collect(Collectors.toList()));
        }
        return toDB;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ChangesRecord post(@RequestBody ChangesRecord record, @PathVariable("id") Long recordId, HttpServletResponse response) throws IOException {
        final Optional<ChangesEntity> parent = changesRepository.findById(recordId);
        if (!parent.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        final ChangesEntity entity = parent.get();
        final ChangesEntity toSave = record.toDB(false);
        toSave.setRecord(entity.getRecord());
        toSave.setPrimarySanctions(toDB(record.getPrimarySanctions(), entity, true));
        toSave.setAlternateSanctions(toDB(record.getAlternateSanctions(), entity, false));
        return changesRepository.save(toSave).toDTO(false);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        changesRepository.deleteById(id);
        return HttpServletResponse.SC_OK;
    }


    @RequestMapping(value = "/{record-id}", method = RequestMethod.GET)
    public ArticleRecord get(@PathVariable("record-id") Long id, HttpServletResponse response) throws IOException {
        RecordEntity entity = recordRepository.getOne(id);
        return entity.toDTO(true);
    }


    private ArticleRecord toDTO(RecordEntity e) {
        ArticleRecord record = e.toDTO(false);
        if (e.getChildren() != null) {
            record.setChildren(new ArrayList<>());
            for (RecordEntity ch : e.getChildren()) {
                if (ch != null) {
                    record.getChildren().add(toDTO(ch));
                }
            }
        }
        return record;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ArticleRecord> getRootTree(HttpServletResponse response) throws IOException {
        List<RecordEntity> entities = recordRepository.getByParentRecordIdOrderByAbbreviation(null);
        List<ArticleRecord> tree = new ArrayList<>();
        for (RecordEntity e : entities) {
            tree.add(toDTO(e));
        }
        return tree;
    }
}

