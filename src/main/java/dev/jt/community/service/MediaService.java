package dev.jt.community.service;

import dev.jt.community.model.MediaDescriptorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MediaService {
    MediaDescriptorDto saveFile(MultipartFile file);
    Collection<MediaDescriptorDto> saveFileBulk(MultipartFile[] files);
}
