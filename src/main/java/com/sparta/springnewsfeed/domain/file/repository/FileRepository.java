package com.sparta.springnewsfeed.domain.file.repository;

import com.sparta.springnewsfeed.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
