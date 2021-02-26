package za.co.newplant.musicapi.service;


import org.springframework.core.io.FileSystemResource;
import za.co.newplant.musicapi.model.Content;
import za.co.newplant.musicapi.model.ContentFile;
import za.co.newplant.musicapi.model.DataContentResponse;

import java.util.List;

public interface IContentFileService {
    List<ContentFile> findAll() throws Exception;
    List<DataContentResponse> findAllContentFiles(Integer pageNo, Integer pageSize, String sortBy) throws Exception;
    List<DataContentResponse> findAllBySpecification(String search) throws Exception;
    DataContentResponse findById(long contentId) throws Exception;
    DataContentResponse save(Content content) throws Exception;
    FileSystemResource findProfileImage(long contentId) throws Exception;
    FileSystemResource findArtWork(long contentId) throws Exception;
    FileSystemResource findMusic(long contentId) throws Exception;
    void deleteById(long contentId) throws Exception;


}
