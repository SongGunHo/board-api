
package org.song.file.constrollers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.song.file.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private FileUploadService service;

    private MultipartFile file;
    private MultipartFile file1;

    @BeforeEach
    void init() throws Exception{
        FileInputStream fis = new FileInputStream("C:/data/1.png");
        file = new MockMultipartFile("file", "image1.png", MediaType.IMAGE_PNG_VALUE, fis);
        file1 = new MockMultipartFile("file", "image2.png", MediaType.IMAGE_PNG_VALUE, fis);
    }
    @Test
    @DisplayName("파일 업로드 테스트 ")
    void fileuploadTest() throws Exception{
        mvc.perform(multipart("/api/v1/file/upload").file(file).file(file1).param("gid", "testgid").param("location", "testLocation")).andDo(print()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
    }
}
