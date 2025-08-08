package org.song.file.repostiroy;

import org.song.file.fileinfo.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FileRepository extends JpaRepository<FileInfo , Long> , QuerydslPredicateExecutor<FileInfo> {
}
