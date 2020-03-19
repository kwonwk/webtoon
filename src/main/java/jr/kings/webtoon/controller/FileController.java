package jr.kings.webtoon.controller;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jr.kings.webtoon.domain.AlbumFile;
import jr.kings.webtoon.domain.Member;
import jr.kings.webtoon.domain.Scrap;
import jr.kings.webtoon.dto.CanvasDTO;
import jr.kings.webtoon.service.EpisodeService;
import jr.kings.webtoon.util.ImageUtil;
import jr.kings.webtoon.util.ReadFile;
import lombok.AllArgsConstructor;

/**
 * uploadController
 */
// @RestController
@Controller
@RestController
@AllArgsConstructor
public class FileController {

    @Autowired
    EpisodeService episodeService;

    @RequestMapping(value = "/file", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> add(@RequestBody CanvasDTO canvasObject) {
        // canvasObject에는 파일(base64), 파일명, 내용, 앨범명이 들어가있다.

        ImageUtil.base64ToImage(canvasObject.getBase64(), canvasObject.getAlbumName(), canvasObject.getAlbumFile());

        // 유저의 정보는 임시로 user로 한다. 나중에 db에서 가져올 경우, user를 실제 유저 정보로 변경할 것.
        Member member = new Member();
        member.setId("user");

        // album과 scrap VO의 정보를 입력해준다.
        AlbumFile albumFile = new AlbumFile();
        Scrap scrap = new Scrap();
        scrap.setMember(member);
        scrap.setAlbumName(canvasObject.getAlbumName());

        albumFile.setAlbumFilePath(canvasObject.getAlbumFile());
        albumFile.setAlbumContent(canvasObject.getAlbumContent());
        albumFile.setScrap(scrap);

        // 캡쳐관련 서비스에서 album과 scrap을 등록해줄 것.
        // 이때, 만약 scrap테이블에 albumName이 없다면, 새로운 albumName을 생성해줘야한다.
        // 이후 album 데이터를 생성.
        episodeService.RegisterScrapFile(albumFile);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 번역본을 브라우저로 보내준다.
    @GetMapping(value = "/getOCR/{webtoonName}/{subtitle}/{ocrFile}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getOCR(
        @PathVariable("webtoonName") String webtoonName, 
        @PathVariable("subtitle") String subtitle, 
        @PathVariable("ocrFile") String ocrFile) {
        System.out.println("getOcrTest.........................................");
        // File file = new File("C:\\zzz\\OCR\\result00.json");
        File file = new File("C:\\zzz\\" + webtoonName +"\\" + subtitle + "\\" + ocrFile);
        System.out.println(file.exists());
        return ReadFile.fileToString(file);
    }
    
    // 파일을 읽어서 브라우저로 보내준다. -> 웹툰 에피소드 이미지
    @ResponseBody
    @GetMapping(value = "/displayWebtoon/{webtoonName}/{subtitle}/{imageFile}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> displayWebtoon(
        @PathVariable("webtoonName") String webtoonName, 
        @PathVariable("subtitle") String subtitle, 
        @PathVariable("imageFile") String imageFile){
        // File file = new File("C:\\zzz\\OCR\\imgg0.jpg");
        System.out.println("displayWebtoon.........................................");
        File file = new File("C:\\zzz\\" + webtoonName +"\\" + subtitle + "\\" + imageFile);
        return makeResponseEntity(file);
    }

    // 파일을 읽어서 브라우저로 보내준다. -> 웹툰 대표 썸네일 이미지
    @ResponseBody
    @GetMapping(value = "/displayWebtoonThumbnail/{title}/{thumbnailPath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> displayThumbnail(
        @PathVariable("title") String title, 
        @PathVariable("thumbnailPath") String thumbnailPath){
        // File file = new File("C:\\zzz\\OCR\\imgg0.jpg");
        System.out.println("displayThumbnail.........................................");
        File file = new File("C:\\zzz\\" + title +"\\" + thumbnailPath);
        return makeResponseEntity(file);
    }
    
    // 파일을 읽어서 브라우저로 보내준다. -> 사용자의 앨범 스크랩 이미지
    @ResponseBody
    @GetMapping(value = "/displayScrap/{albumName}/{albumContent}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> displayScrap(
        @PathVariable("albumName") String albumName, 
        @PathVariable("albumContent") String albumContent){
        // File file = new File("C:\\zzz\\OCR\\imgg0.jpg");
        System.out.println("displayAlbumFile.........................................");
        String id = "user";
        File file = new File("C:\\zzz\\capture\\" + id +"\\" + albumName + "\\" + albumContent + ".jpeg");
        return makeResponseEntity(file);
    }

    
    private ResponseEntity<byte[]> makeResponseEntity(File file){
        ResponseEntity<byte[]> result = null;
        try{
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
