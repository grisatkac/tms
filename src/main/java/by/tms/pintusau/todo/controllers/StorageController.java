package by.tms.pintusau.todo.controllers;

import by.tms.pintusau.todo.services.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aws/s3")
@AllArgsConstructor
public class StorageController {

    private FileService fileService;

    @PostMapping
    public void saveFile(@RequestParam("file") MultipartFile multipartFile) {
        fileService.upload(multipartFile);
    }

    @DeleteMapping("/{key}")
    public void delete(@PathVariable String key) {
        fileService.delete(key);
    }
}
