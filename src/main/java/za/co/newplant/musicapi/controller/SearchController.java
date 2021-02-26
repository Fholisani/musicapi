package za.co.newplant.musicapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.newplant.musicapi.model.ContentResponse;
import za.co.newplant.musicapi.model.DataContentResponse;
import za.co.newplant.musicapi.rules.RuleProcessor;
import za.co.newplant.musicapi.service.ContentFileService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);
    @Autowired
    ContentFileService contentFileService;
    @GetMapping(value = "/search",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ContentResponse>> findAllBySpecification(@RequestParam(value = "search") String search) throws Exception {
        LOG.info("Search string : " + search);

        List<DataContentResponse> searchContentFiles = contentFileService.findAllBySpecification(search);
        return ResponseEntity.status(HttpStatus.OK).body(searchContentFiles.stream().map(dataContentResponse ->
                new ContentResponse(dataContentResponse, RuleProcessor.detailErrorStatusResponse(dataContentResponse),"")).collect(Collectors.toList()));
    }
}
