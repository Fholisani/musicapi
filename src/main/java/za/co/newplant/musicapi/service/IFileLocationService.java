package za.co.newplant.musicapi.service;

import org.springframework.core.io.FileSystemResource;
import za.co.newplant.musicapi.contstant.Status;

import java.io.IOException;

public interface IFileLocationService {
    String save(byte[] content, String fileName, Status type) throws IOException;
    FileSystemResource findInFileSystem(String location) throws Exception;

}
