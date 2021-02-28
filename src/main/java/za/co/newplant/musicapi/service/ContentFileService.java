package za.co.newplant.musicapi.service;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.newplant.musicapi.contstant.Status;
import za.co.newplant.musicapi.exception.ResourceNotFoundException;
import za.co.newplant.musicapi.model.Content;
import za.co.newplant.musicapi.model.ContentFile;
import za.co.newplant.musicapi.model.DataContentResponse;
import za.co.newplant.musicapi.repository.ContentFileRepository;
import za.co.newplant.musicapi.rules.RuleProcessor;
import za.co.newplant.musicapi.util.ContentSpecificationsBuilder;
import za.co.newplant.musicapi.util.SearchOperation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ContentFileService implements IContentFileService {

    private static final Logger LOG = LoggerFactory.getLogger(ContentFileService.class);
    @Autowired
    private ContentFileRepository contentFileRepository;
    @Autowired
    private FileLocationService fileLocationService;

    @Override
    public List<ContentFile> findAll() throws Exception {
        return (List<ContentFile>) contentFileRepository.findAll();
    }

    @Override
    public List<DataContentResponse> findAllContentFiles(Integer pageNo, Integer pageSize, String sortBy) throws Exception {
        //DESCENDING -  Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("song_name"));
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("contentId").ascending());
        Page<ContentFile> pagedResult = contentFileRepository.findAll(paging);
        List<ContentFile> contentFiles = new ArrayList<>();
        if (pagedResult.hasContent()) {
           contentFiles = pagedResult.getContent();
        }
        return contentFiles.stream().map(contentData ->
             new DataContentResponse(contentData.getContentId(),contentData.getSongName(),
                    contentData.getProducerName(),contentData.getArtistsNames(), contentData.getReleaseDate().toString(),
                    downloadUri("profileimage",contentData.getContentId()), downloadUri("musicfile",contentData.getContentId()),
                    downloadUri("artwork",contentData.getContentId()), contentData.getStatus().name())).collect(Collectors.toList());

    }

    @Override
    public List<DataContentResponse> findAllBySpecification(String search) throws Exception {

        ContentSpecificationsBuilder builder = new ContentSpecificationsBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }
        Specification<ContentFile> spec = builder.build();

        return contentFileRepository.findAll(spec).stream().map(contentData ->
                new DataContentResponse(contentData.getContentId(),contentData.getSongName(),
                        contentData.getProducerName(),contentData.getArtistsNames(), contentData.getReleaseDate().toString(),
                        downloadUri("profileimage",contentData.getContentId()), downloadUri("musicfile",contentData.getContentId()),
                        downloadUri("artwork",contentData.getContentId()), contentData.getStatus().name())).collect(Collectors.toList());
    }

    private static String downloadUri(String contentType, long contentId) {
        return  ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/"+contentType+"/")
                .path(String.valueOf(contentId))
                .toUriString();
    }

    @Override
    public DataContentResponse findById(long contentId) throws Exception {
        ContentFile contentData =  contentFileRepository.findById(contentId).orElseThrow(() -> new ResourceNotFoundException(String.valueOf(contentId)));
        return new DataContentResponse(contentData.getContentId(),contentData.getSongName(),
                contentData.getProducerName(),contentData.getArtistsNames(), contentData.getReleaseDate().toString(),
                downloadUri("profileimage",contentData.getContentId()), downloadUri("musicfile",contentData.getContentId()),
                downloadUri("artwork",contentData.getContentId()), contentData.getStatus().name(), contentData.getLocation(), contentData.getLocationProfile(),
                contentData.getLocationArtwork());
    }

    @Override
    public DataContentResponse save(Content content) throws Exception {
        try {

            String locationProfile = content.getProfileImage() == null ? content.getLocationProfile() :
                    fileLocationService.save(content.getProfileImageBytes(), content.getProfileImage().getFilename(),Status.IMAGE);
            String locationArtWork = content.getArtwork() == null ? content.getLocationArtwork() :
                    fileLocationService.save(content.getArtworkBytes(), content.getArtwork().getFilename(), Status.IMAGE);
            String locationFileContent = content.getFileContent() == null ? content.getLocationContent() :
                    fileLocationService.save(content.getFileContentBytes(), content.getFileContent().getFilename(),Status.AUDIO);

            ContentFile contentData = new ContentFile();
            contentData.setContentId(content.getId());
            contentData.setArtistsNames(content.getArtistsNames());
            contentData.setProducerName(content.getProducerName());
            contentData.setReleaseDate(LocalDateTime.now());
            contentData.setSongName(content.getSongName());
            contentData.setStatus(RuleProcessor.statusRule(content));
            contentData.setType(MusicUtility.getExtensionByStringHandling( content.getFileContent() == null ?
                    content.getLocationContent() : content.getFileContent().getFilename()));
            contentData.setLocation(locationFileContent);
            contentData.setLocationProfile(locationProfile);
            contentData.setLocationArtwork(locationArtWork);
            contentData = contentFileRepository.save(contentData);

            DataContentResponse  dataContentResponse =  new DataContentResponse(contentData.getContentId(),contentData.getSongName(),
                    contentData.getProducerName(),contentData.getArtistsNames(), contentData.getReleaseDate().toString(),
                    downloadUri("profileimage",contentData.getContentId()),downloadUri("musicfile",
                    contentData.getContentId()),downloadUri("artwork",contentData.getContentId()), contentData.getStatus().name());

            return dataContentResponse;
        }catch (Exception e){
            LOG.error("save" + e.toString());
            throw new Exception(e);
        }

    }

    @Override
    public FileSystemResource findProfileImage(long contentId) throws Exception {
        ContentFile image = contentFileRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(contentId)));

        return fileLocationService.findInFileSystem(image.getLocationProfile());
    }

    @Override
    public FileSystemResource findArtWork(long contentId) throws Exception {
        ContentFile image = contentFileRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(contentId)));

        return fileLocationService.findInFileSystem(image.getLocationArtwork());
    }
    @Override
    public FileSystemResource findMusic(long contentId) throws Exception {
        ContentFile music = contentFileRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(contentId)));

        return fileLocationService.findInFileSystem(music.getLocation());
    }

    @Override
    public void deleteById(long contentId) throws Exception {
        ContentFile contentData = contentFileRepository.findById(contentId).orElseThrow(() -> new ResourceNotFoundException(String.valueOf(contentId)));
        contentFileRepository.deleteById(contentId);
        RuleProcessor.deleteFiles(contentData.getLocation());
        RuleProcessor.deleteFiles(contentData.getLocationArtwork());
        RuleProcessor.deleteFiles(contentData.getLocationProfile());

    }



}
