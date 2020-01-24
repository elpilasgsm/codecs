package org.sfedu.codecs.controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.sfedu.codecs.model.db.RecordEntity;
import org.sfedu.codecs.model.db.UserEntity;
import org.sfedu.codecs.model.dto.ArticleRecord;
import org.sfedu.codecs.model.dto.User;
import org.sfedu.codecs.repository.db.RecordRepository;
import org.sfedu.codecs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ArticleRecord put(@RequestBody ArticleRecord record) {
        return recordRepository.save(record.toDB(false)).toDTO(true);
    }


    @RequestMapping(value = "/{record-id}", method = RequestMethod.GET)
    public ArticleRecord get(@PathVariable("record-id") Long id, HttpServletResponse response) throws IOException {
        RecordEntity entity = recordRepository.getOne(id);
        return entity.toDTO(true);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ArticleRecord> getRootTree(HttpServletResponse response) throws IOException {
        List<RecordEntity> entity = recordRepository.getByParentRecordId(null);
        return entity.stream().map(it -> it.toDTO(true)).collect(Collectors.toList());
    }
}

