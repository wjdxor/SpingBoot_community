package dev.jt.community.service;

import dev.jt.community.model.MediaDescriptorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class LocalMediaService implements MediaService{
    private static final Logger logger = LoggerFactory.getLogger(LocalMediaService.class);
    private final String basePath = "./media";

    @Override
    public MediaDescriptorDto saveFile(MultipartFile file) {
        return this.saveToDir(file);
    }

    @Override
    public Collection<MediaDescriptorDto> saveFileBulk(MultipartFile[] files) {
        Collection<MediaDescriptorDto> resultList = new ArrayList<>();
        for (MultipartFile file: files){
            resultList.add(this.saveToDir(file));

        }
        return resultList;
    }

    private MediaDescriptorDto saveToDir(MultipartFile file){
        MediaDescriptorDto descriptorDto = new MediaDescriptorDto();
        descriptorDto.setStatus(200);
        descriptorDto.setOriginalName(file.getOriginalFilename());
        try {
            LocalDateTime now = LocalDateTime.now(); // 중복된 파일 올리는 것을 구분하기 위해
            String targetDir = Path.of(
                    basePath,
                    now.format(DateTimeFormatter.BASIC_ISO_DATE)
            ).toString();
            String newFileName =
                    now.format(DateTimeFormatter.ofPattern("HHmmss")) + "_" + file.getOriginalFilename();
            File dirNow = new File(targetDir);
            if(!dirNow.exists()) dirNow.mkdir();
            file.transferTo(Path.of(
                    targetDir,
                    newFileName
            ));
            descriptorDto.setResourcePath(Path.of(
                    targetDir,
                    newFileName).toString().substring(1)
            );
            return descriptorDto;
        } catch (IOException e) {
            logger.error(e.getMessage());
            descriptorDto.setMessage("failed");
            descriptorDto.setStatus(500);
            return descriptorDto;
        }
    }
}
