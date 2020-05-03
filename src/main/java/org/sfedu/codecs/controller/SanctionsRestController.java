package org.sfedu.codecs.controller;

import org.sfedu.codecs.model.db.ChangesEntity;
import org.sfedu.codecs.model.db.SanctionChangesEntity;
import org.sfedu.codecs.model.db.SanctionEntity;
import org.sfedu.codecs.model.dto.SanctionChangesRecord;
import org.sfedu.codecs.model.dto.SanctionRecord;
import org.sfedu.codecs.repository.db.ChangesRepository;
import org.sfedu.codecs.repository.db.RecordRepository;
import org.sfedu.codecs.repository.db.SanctionsChangeRepository;
import org.sfedu.codecs.repository.db.SanctionsRepository;
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
@RequestMapping("/api/sanctions/")
public class SanctionsRestController {

    private final RecordRepository recordRepository;
    private final ChangesRepository changesRepository;
    private final SanctionsRepository sanctionsRepository;
    private final SanctionsChangeRepository sanctionsChangeRepository;

    @Autowired
    SanctionsRestController(RecordRepository recordRepository, ChangesRepository changesRepository, SanctionsRepository sanctionsRepository, SanctionsChangeRepository sanctionsChangeRepository) {
        this.recordRepository = recordRepository;
        this.sanctionsChangeRepository = sanctionsChangeRepository;
        this.changesRepository = changesRepository;
        this.sanctionsRepository = sanctionsRepository;
    }


    @RequestMapping(value = "/{change-id}", method = RequestMethod.PUT)
    public SanctionChangesRecord put(@RequestBody SanctionChangesRecord record, @PathVariable("change-id") Long changeId, HttpServletResponse response) throws IOException {

        Optional<ChangesEntity> changesEntityOptional = changesRepository.findById(changeId);
        if (!changesEntityOptional.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        SanctionChangesEntity entity = record.toDB(false);
        entity.setSanctionEntity(sanctionsRepository.getOne(record.getSanction().getId()));
        final ChangesEntity change = changesEntityOptional.get();
        entity.setChangesEntity(change);
        sanctionsChangeRepository.save(entity);
        return entity.toDTO(true);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public SanctionChangesRecord post(@RequestBody SanctionChangesRecord record, @PathVariable("id") Long recordId, HttpServletResponse response) throws IOException {
        final Optional<SanctionChangesEntity> sanctionChangesEntity = sanctionsChangeRepository.findById(recordId);
        if (!sanctionChangesEntity.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        final SanctionChangesEntity entity = sanctionChangesEntity.get();
        entity.setFrom(record.getFrom());
        entity.setTo(record.getTo());
        entity.setSanctionEntity(sanctionsRepository.getOne(record.getSanction().getId()));
        entity.setChangesEntity(changesRepository.getOne(record.getChange().getId()));
        if (CollectionUtils.isEmpty(record.getAlternateSanctions())) {
            entity.getAlternateSactions().clear();
        } else {
            entity.setAlternateSactions(record.getAlternateSanctions().stream().map(it -> {
                SanctionChangesEntity toSave = it.toDB(false);
                toSave.setMainSanction(entity);
                return toSave;
            }).collect(Collectors.toList()));
        }
        return sanctionsChangeRepository.save(entity).toDTO(false);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        sanctionsChangeRepository.deleteById(id);
        return HttpServletResponse.SC_OK;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<SanctionRecord> getRootTree(HttpServletResponse response) throws IOException {
        List<SanctionEntity> entities = sanctionsRepository.findAll();
        return CollectionUtils.isEmpty(entities)
                ? new ArrayList<>(0) :
                entities.stream().map(it -> it.toDTO(false)).collect(Collectors.toList());
    }
}

