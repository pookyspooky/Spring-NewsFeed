package com.sparta.springnewsfeed.domain.file.entity;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public File(String originalFileName, String storedFileName, String filePath, String fileType, long fileSize) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    @Column(nullable = false)
    private String originalFileName;   // 원본 파일 이름

    @Column(nullable = false)
    private String storedFileName;   // 서버에 저장된 파일 이름

    @Column(nullable = false)
    private String filePath; // 파일이 서버에 저장된 경로

    private String fileType;  // 파일의 MIME 타입 (예: image/jpeg, application/pdf)
    
    private long fileSize; // 파일 크기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }
}
