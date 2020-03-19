package jr.kings.webtoon.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.util.FileCopyUtils;

public class ReadFile {

    // file의 내용을 읽어들인다.
    public static String fileToString(File file) {
        String string = "";
        
        // 파일의 내용을 복사한다.
        try (InputStream fin = new FileInputStream(file); ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            int count = FileCopyUtils.copy(fin, bos);
            System.out.println(count);
            string = new String(bos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    // public static List<OcrDTO> jsonToObject(File file) {
    //     Gson gson = new Gson();
    //     List<OcrDTO> list = null;

    //     try (Reader reader = new FileReader(file)) {
    //         // Json file to Java Object[]
    //         list = Arrays.asList(gson.fromJson(reader, OcrDTO[].class));

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return list;
    // }

}