package org.song.file.controllers;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestUpload {
    private String gid;
    private String location;
    private MultipartFile[] files; // 멀티 파일 업로드
    private boolean single; // 하나의 파일
    private boolean imageOnly; // 이미지만
}
