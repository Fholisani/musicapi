package za.co.newplant.musicapi.controller;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import za.co.newplant.musicapi.exception.CustomException;
import za.co.newplant.musicapi.exception.error.ApiError;
import za.co.newplant.musicapi.exception.error.ApiSubError;
import za.co.newplant.musicapi.model.Content;
import za.co.newplant.musicapi.model.ContentResponse;
import za.co.newplant.musicapi.model.DataContentResponse;
import za.co.newplant.musicapi.rules.RuleProcessor;
import za.co.newplant.musicapi.service.ContentFileService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/main")
public class MainContentController {
    private static final Logger LOG = LoggerFactory.getLogger(MainContentController.class);
    @Autowired
    ContentFileService contentFileService;
    final static ApiSubError apiSubError = new ApiSubError();
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContentResponse>
    uploadFile(@ApiParam(value = "Artist Profile Image") @Valid @RequestPart(value = "profileImage", required = false) MultipartFile profileContent,
               @ApiParam(value = "Music File") @Valid @RequestPart(value = "fileContent", required = false) MultipartFile fileContent,
               @ApiParam(value = "Name of the song") @Valid @RequestPart(value = "songName", required = false) String songName,
               @ApiParam(value = "Names of the producers") @Valid @RequestPart(value = "producerName", required = false) String producerName,
               @ApiParam(value = "Music artwork are images directly related to an artist or an album") @Valid @RequestPart(value = "artwork", required = false) MultipartFile artwork,
               @ApiParam(value = "Artist Name") @Valid @RequestPart(value = "artistsNames", required = false) String artistsNames,
               @ApiParam(value = "Release date") @Valid @RequestPart(value = "releaseDate", required = false) String releaseDate) throws Exception {

        List<ApiSubError> errorInput = new ArrayList<>();
        errorInput = validMultiPartContents(errorInput, profileContent, "profileContent", "Provide Artist Profile Image");
        errorInput = validMultiPartContents(errorInput, fileContent, "fileContent", "Provide Music File");
        errorInput = validMultiPartContents(errorInput, songName, "songName", "Provide Music song name");
        errorInput = validMultiPartContents(errorInput, producerName, "producerName", "Provide Names of the producers");
        errorInput = validMultiPartContents(errorInput, artwork, "artwork", "Provide Music artwork - images directly related to an artist or an album");
        errorInput = validMultiPartContents(errorInput, releaseDate, "releaseDate", "Provide Release date");
        errorInput = validMultiPartContents(errorInput, artistsNames, "artistsNames", "Provide Artist Profile Image");

        if (errorInput.isEmpty()) {
            LOG.info("==================Received File================");
            Content content = new Content();
            content.setArtistsNames(artistsNames);
            content.setArtwork(artwork.getResource());
            content.setArtworkBytes(artwork.getBytes());
            content.setSongName(songName);
            content.setProducerName(producerName);
            content.setReleaseDate(releaseDate);
            content.setFileContent(fileContent.getResource());
            content.setFileContentBytes(fileContent.getBytes());
            content.setProfileImage(profileContent.getResource());
            content.setProfileImageBytes(profileContent.getBytes());
            DataContentResponse dataContentResponse = contentFileService.save(content);
            ContentResponse contentResponse = new ContentResponse(dataContentResponse,
                    RuleProcessor.detailErrorStatusResponse(dataContentResponse), "");
            return ResponseEntity.status(HttpStatus.OK).body(contentResponse);

        } else {
            Set<ApiSubError> messages = new TreeSet<>();
            messages.addAll(errorInput.stream().map(fieldError ->
                    new ApiSubError(fieldError.getField(), fieldError.getMessage())).collect(Collectors.toList()));
            ApiError response = new ApiError(2, "Validation errors", messages);
            throw new CustomException("Validation", response);
        }

    }


    @PutMapping(path = "/update/{contentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContentResponse>
    updateContents(@PathVariable String contentId, @ApiParam(value = "Artist Profile Image")
    @Valid @RequestPart(value = "profileImage", required = false) MultipartFile profileContent,
                   @ApiParam(value = "Music File") @Valid @RequestPart(value = "fileContent", required = false) MultipartFile fileContent,
                   @ApiParam(value = "Name of the song") @Valid @RequestPart(value = "songName", required = false) String songName,
                   @ApiParam(value = "Names of the producers") @Valid @RequestPart(value = "producerName", required = false) String producerName,
                   @ApiParam(value = "Music artwork are images directly related to an artist or an album")
                   @Valid @RequestPart(value = "artwork", required = false) MultipartFile artwork,
                   @ApiParam(value = "Artist Name") @Valid @RequestPart(value = "artistsNames", required = false) String artistsNames,
                   @ApiParam(value = "Release date") @Valid @RequestPart(value = "releaseDate", required = false) String releaseDate) throws Exception {


        DataContentResponse contentDataContentResponse = contentFileService.findById(Long.parseLong(contentId));
        List<ApiSubError> errorInput = new ArrayList<>();
        errorInput = validMultiPartContents(errorInput, songName, "songName", "Provide Music song name");
        errorInput = validMultiPartContents(errorInput, producerName, "producerName", "Provide Names of the producers");
        errorInput = validMultiPartContents(errorInput, releaseDate, "releaseDate", "Provide Release date");
        errorInput = validMultiPartContents(errorInput, artistsNames, "artistsNames", "Provide Artist Profile Image");

        if (errorInput.isEmpty()) {
            LOG.info("==================Update File================ " + contentId);
            Content content = new Content();
            content.setId(Integer.parseInt(String.valueOf(contentDataContentResponse.getContentId())));
            content.setArtistsNames(artistsNames);
            content.setArtwork(artwork.getResource());
            content.setArtworkBytes(artwork.getBytes());
            content.setSongName(songName);
            content.setProducerName(producerName);
            content.setReleaseDate(releaseDate);
            content.setFileContent(fileContent.getResource());
            content.setFileContentBytes(fileContent.getBytes());
            content.setProfileImage(profileContent.getResource());
            content.setProfileImageBytes(profileContent.getBytes());
            content.setLocationContent(contentDataContentResponse.getLocationContent());
            content.setLocationProfile(contentDataContentResponse.getLocationProfile());
            content.setLocationArtwork(contentDataContentResponse.getLocationArtwork());
            DataContentResponse dataContentResponse = contentFileService.save(content);
            ContentResponse contentResponse = new ContentResponse(dataContentResponse,
                    RuleProcessor.detailErrorStatusResponse(dataContentResponse), "");
            return ResponseEntity.status(HttpStatus.OK).body(contentResponse);

        } else {

            Set<ApiSubError> messages = new TreeSet<>();
            messages.addAll(errorInput.stream().map(fieldError ->
                    new ApiSubError(fieldError.getField(), fieldError.getMessage())).collect(Collectors.toList()));
            ApiError response = new ApiError(2, "Validation errors", messages);
            throw new CustomException("Validation", response);
        }


    }
    @DeleteMapping("/contents/{contentId}")
    public void deleteContents(@PathVariable long contentId) throws Exception {
        LOG.info("deleteContents : " + contentId);
        contentFileService.deleteById(contentId);

    }

    public static List<ApiSubError> validMultiPartContents(List<ApiSubError> errorInput, Object fieldName,
                                                           String field, String message) {
        if (fieldName == null) {
            apiSubError.setField(field);
            apiSubError.setMessage(message);
            errorInput.add(apiSubError);
        }
        return errorInput;
    }


}
