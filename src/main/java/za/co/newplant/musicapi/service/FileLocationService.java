package za.co.newplant.musicapi.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import za.co.newplant.musicapi.contstant.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FileLocationService implements IFileLocationService {

    static final  String RESOURCES_DIR = "/opt/uploads/";
    @Override
    public String save(byte[] content, String fileName,Status type) throws IOException {

        Path newFile = Paths.get(RESOURCES_DIR + "/" + type.name().toLowerCase() +"/" + new Date().getTime() + "-" + fileName);
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath()
                .toString();
    }

    @Override
    public FileSystemResource findInFileSystem(String location) throws Exception {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e){
            throw new Exception();
        }
    }




}
