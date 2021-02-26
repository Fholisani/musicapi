package za.co.newplant.musicapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ContentsController {
    private static final Logger LOG = LoggerFactory.getLogger(ContentsController.class);
    @Autowired
    ContentFileService contentFileService;

    @GetMapping(value = "/contents", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ContentResponse>> getContents(@RequestParam(defaultValue = "0") Integer pageNo,
                                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                                             @RequestParam(defaultValue = "id") String sortBy) throws Exception {
        List<DataContentResponse> allContentFiles =contentFileService.findAllContentFiles(pageNo, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(allContentFiles.stream().map(dataContentResponse ->
                new ContentResponse(dataContentResponse,RuleProcessor.detailErrorStatusResponse(dataContentResponse),"")).collect(Collectors.toList()));

    }

    @GetMapping(value = "/contents/{contentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContentResponse> getContentById(@PathVariable String  contentId) throws Exception {
        DataContentResponse dataContentResponse =contentFileService.findById(Long.parseLong(contentId));
        ContentResponse contentResponse = new ContentResponse(dataContentResponse, RuleProcessor.detailErrorStatusResponse(dataContentResponse), "");
        return ResponseEntity.status(HttpStatus.OK).body(contentResponse);

    }
}
