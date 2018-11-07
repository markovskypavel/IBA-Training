package by.iba.markovsky.festival.controller;

import org.springframework.stereotype.Controller;

@Controller
public class FileUploadController {

/*    @Value("${generator.service.strings.count}")
    public String uploadDirectory = System.getProperty("user.dir")+"/uploads";

    @RequestMapping("/upload")
    public String UploadPage(Model model) {
        return "uploadview";
    }
    @RequestMapping("/upload")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename()+" ");
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
        return "uploadstatusview";
    }*/

}
