package com.sparta.springnewsfeed.domain.file.service;

import com.sparta.springnewsfeed.domain.file.entity.File;
import com.sparta.springnewsfeed.domain.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;    // 파일 업로드 디렉토리
    
    // 파일을 저장하고 File 엔티티를 생성하여 반환하는 메서드
    public File saveFile(MultipartFile multipartFile){
        if (multipartFile.isEmpty())    // 파일이 비어있는지 확인
            return null;
        try {
            String originalFileName = multipartFile.getOriginalFilename();     // 원본 파일 이름 가져오기
            String fileExtension = getFileExtension(originalFileName);    // 파일 확장자 추출
            String storedFileName = UUID.randomUUID().toString() + fileExtension;   // 고유한 파일 이름 생성

            Path uploadPath = Paths.get(uploadDir);   // 업로드 디렉토리 경로 생성
            if (!Files.exists(uploadPath))    // 디렉토리가 존재하지 않으면 생성
                Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(storedFileName);  // 파일 저장 경로 설정
            Files.copy(multipartFile.getInputStream(), filePath);    // 파일을 지정된 경로에 저장

            File file = new File(
                    originalFileName,
                    storedFileName,
                    filePath.toString(),
                    multipartFile.getContentType(),
                    multipartFile.getSize()
            );

            return fileRepository.save(file);
        }catch (IOException e){
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
    
    // 파일 이름에서 확장자를 추출하는 메서드
    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');   
        if (lastIndex == -1)
            return "";

        return fileName.substring(lastIndex);    // 확장자 반환
    }
}
