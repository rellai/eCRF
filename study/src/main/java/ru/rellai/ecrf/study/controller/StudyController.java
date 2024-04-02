
package ru.rellai.ecrf.study.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.rellai.ecrf.study.annotation.AddMenu;
import ru.rellai.ecrf.study.client.FilesClient;


@Controller
@RequiredArgsConstructor
public class StudyController {

    private final FilesClient filesClient;

    @GetMapping("/study")
    @AddMenu
    public String study(HttpServletRequest request, Model model) {
        model.addAttribute("title", "Study Creation");
        return "study";
    }


    @AddMenu
    @PostMapping("/study/uploadFile")
    public String uploadFile(Model model, @RequestParam("file") MultipartFile file) {

        String message = "";

        if (file.isEmpty()) {
            message = "Please select a file to upload";
            model.addAttribute("message", message);
            return "study";
        }

        try {
            filesClient.uploadFile(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        return "study";
    }


}