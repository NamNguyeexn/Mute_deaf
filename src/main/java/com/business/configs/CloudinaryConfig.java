//import com.cloudinary.Cloudinary;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import java.io.IOException;
//import java.util.Map;
//
//@Controller
//@RefreshScope
//public class UploadController {
//
//    @Value("${cloudinary.cloud_name}")
//    private String cloudName;
//
//    @Value("${cloudinary.api_key}")
//    private String apiKey;
//
//    @Value("${cloudinary.api_secret}")
//    private String apiSecret;
//
//    @Autowired
//    private YourDatabaseService databaseService; // Đảm bảo bạn đã tạo service để quản lý cơ sở dữ liệu
//
//    @GetMapping("/")
//    public String index() {
//        return "upload";
//    }
//
//    @PostMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
//        // Cấu hình Cloudinary bằng các thuộc tính từ application.properties
//        Cloudinary cloudinary = new Cloudinary("cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName);
//
//        // Tải lên ảnh lên Cloudinary
//        Map<String, String> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//
//        // Lấy URL của ảnh từ kết quả tải lên
//        String imageUrl = uploadResult.get("url");
//
//        // Lưu URL vào cơ sở dữ liệu (ví dụ: MySQL)
//        databaseService.saveImageUrl(imageUrl);
//
//        model.addAttribute("message", "Tải lên ảnh thành công và đã lưu URL vào cơ sở dữ liệu.");
//        return "upload";
//    }
//}
