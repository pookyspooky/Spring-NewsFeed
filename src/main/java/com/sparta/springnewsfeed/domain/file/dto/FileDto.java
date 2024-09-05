package com.sparta.springnewsfeed.domain.file.dto;

import com.sparta.springnewsfeed.domain.file.entity.File;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDto {
    private Long id;
    private String originalFileName;
    private String fileType;
    private long fileSize;

    public static FileDto fromEntity(File file){
        FileDto dto = new FileDto();
        dto.setId(file.getId());
        dto.setOriginalFileName(file.getOriginalFileName());
        dto.setFileType(file.getFileType());
        dto.setFileSize(file.getFileSize());
        return dto;
    }
}
