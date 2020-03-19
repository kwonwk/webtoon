package jr.kings.webtoon.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

public class ImageUtil {

    // 받은 base64 코드를 이미지 파일로 저장시켜주는 함수
    public static void base64ToImage(String base64, String albumName, String fileName) {

        // 확장자 확인
        String[] strings = base64.split(",");
        String extension;
        switch (strings[0]) {
        case "data:image/jpeg;base64":
            extension = "jpeg";
            break;
        case "data:image/png;base64":
            extension = "png";
            break;
        default:
            extension = "jpg";
            break;
        }

        // img file 생성(base64를 파일데이터로 변환)
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        
        String id = "user";

        String folderPath = "C:\\zzz\\capture\\" + id + "\\" + albumName+"\\";
        String filePath = folderPath + fileName + "." + extension;
        File folder = new File(folderPath);

        // 폴더 먼저 생성
        if(!folder.exists()){
            folder.mkdirs();
        }
        // 이미지 생성
        File file = new File(filePath);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}