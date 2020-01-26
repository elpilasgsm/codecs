package org.sfedu.codecs.controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.sfedu.codecs.model.db.RecordEntity;
import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.model.dto.ArticleRecord;
import org.sfedu.codecs.model.dto.User;
import org.sfedu.codecs.repository.db.RecordRepository;
import org.sfedu.codecs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/api/article/")
public class ArticleRestController {

    private final RecordRepository recordRepository;

    @Autowired
    ArticleRestController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ArticleRecord put(@RequestBody ArticleRecord record, HttpServletResponse response) throws IOException {
        RecordEntity entity = record.toDB(false);
        if (record.getParent() != null && record.getParent().getRecordId() != 0) {
            final RecordEntity parent = recordRepository.getOne(record.getParent().getRecordId());
            if (parent.getRecordId() == 0) {
                response.sendError(HttpServletResponse.SC_FOUND);
                return null;
            } else {
                entity.setParent(parent);
            }
        }
        return recordRepository.save(entity).toDTO(false);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        recordRepository.deleteById(id);
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
        List<RecordEntity> entities = recordRepository.getByParentRecordId(null);
        List<ArticleRecord> tree = new ArrayList<>();
        for (RecordEntity e : entities) {
            tree.add(toDTO(e));
        }
        return tree;
    }
}

