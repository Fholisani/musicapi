package za.co.newplant.musicapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.newplant.musicapi.service.ContentFileService;

@RestController
@RequestMapping
public class DownloadController {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadController.class);
    @Autowired
    protected ContentFileService contentFileService;

    @GetMapping(value = "/profileimage/{contentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileSystemResource> profileDownloadImage(@PathVariable String  contentId) throws Exception {
        LOG.info("Download profile image - " + contentId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(contentFileService.findProfileImage(Long.parseLong(contentId)));
    }
    @GetMapping(value = "/artwork/{contentId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public  ResponseEntity<FileSystemResource> artworkDownloadImage( @PathVariable String  contentId) throws Exception {
        LOG.info("Download artwork image - " + contentId);
        return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(contentFileService.findArtWork(Long.parseLong(contentId)));
    }

    @GetMapping(value = "/musicfile/{contentId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public  ResponseEntity<FileSystemResource> musicDownload( @PathVariable String  contentId) throws Exception {
        LOG.info("Download music - " + contentId);
        return  ResponseEntity.ok().contentType(MediaType.valueOf("audio/mpeg")).body(contentFileService.findMusic(Long.parseLong(contentId)));
    }

}
