package org.perscholas.controllers;

import org.perscholas.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;

@Controller
public class FileController {

    FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/uploadform")
    public String uploadForm(){
        return "uploadform";
    }

    @PostMapping("/uploadfile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws FileNotFoundException {

        fileService.uploadFile(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename());
        return "redirect:/uploadform";
    }

    @GetMapping("showallimages")
    public String showImages(Model model){

        model.addAttribute("allimages", fileService.getAllImages());
        return "showimages";

    }

    @GetMapping("/webcam")
    public String takePicture(){
        return "webcam";
    }

    @PostMapping("/saveimage")
    public String saveImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws FileNotFoundException {

        fileService.uploadFile(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename());

        return "redirect:/uploadform";
    }


}