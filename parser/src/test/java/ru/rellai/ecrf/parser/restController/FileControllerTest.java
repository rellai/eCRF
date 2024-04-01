package ru.rellai.ecrf.parser.restController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import ru.rellai.ecrf.parser.service.FileStorageService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileStorageService fileStorageService;

    @Test
    public void SendFileToServerWhitAuthorize_whenPost_thenReturnsOK() throws Exception {

      MockMultipartFile file = new MockMultipartFile("file", "dummy.csv",
                "text/plain", "Some dataset...".getBytes());

      mockMvc.perform(multipart("/api/v1/files")
                                .file(file)
                                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_study"))))
              .andExpect(status().isOk());

    }







}