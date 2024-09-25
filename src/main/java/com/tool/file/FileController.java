package com.tool.file;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private final static String DIRECTORY = "uploads";

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<?> createProduct(@RequestParam("file") List<MultipartFile> files) {
        for (MultipartFile file : files) {
            storeFile(file);
        }
        return ResponseEntity.ok("All file complete saved");
    }

    @GetMapping(value = "")
    public String getFiles(Model model) throws IOException {
        Path uploadDir = Paths.get(DIRECTORY);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        File file = new File(DIRECTORY);
        File[] files = file.listFiles();

        List<String> filenames;
        if (files != null && files.length > 0) {
            filenames = Arrays.stream(files).map(File::getName).toList();
        } else {
            filenames = new ArrayList<>(1);
        }
        model.addAttribute("files", filenames);
        return "file";
    }

    @PostMapping(value = "/send")
    public RedirectView sendFile(@RequestParam("file") MultipartFile file) {
        storeFile(file);
        return new RedirectView("/file");
    }

    @PostMapping(value = "/delete")
    public RedirectView deleteFile(@RequestParam("filename") String fileName) {
        File dir = new File("uploads");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File item : files) {
                if (fileName.equals(item.getName())) item.delete();
            }
        }
        return new RedirectView("/file");
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filename") String filename) {
        File dir = new File(DIRECTORY);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            Path filePath = Paths.get(DIRECTORY, filename);
            Resource resource = new FileSystemResource(filePath.toFile());
            if (resource.exists()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
                return ResponseEntity.ok().headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            }
        }
        return ResponseEntity.notFound().build();
    }

    private void storeFile(MultipartFile file) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
            Path uploadDir = getDirectories();
            Path destination = Paths.get(uploadDir.toString(), filename);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("File input exception");
        }
    }

    private Path getDirectories() {
        Path uploadDir;
        try {
            uploadDir = Paths.get(DIRECTORY);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
        } catch (IOException e) {
            throw new RuntimeException("Directories exception");
        }
        return uploadDir;
    }
}
