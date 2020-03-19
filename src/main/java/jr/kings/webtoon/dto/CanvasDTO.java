package jr.kings.webtoon.dto;

import lombok.Data;

@Data
public class CanvasDTO {
    // 앨범 이름. -> Scrap 테이블에 들어감.
    private String albumName;
    // 파일 이름. -> Album 테이블에 들어감
    private String albumFile;
    // 내용 -> Album 테이블에 들어감
    private String albumContent;
    // 파일 -> uploadController에서 파일 생성 목적.
    private String base64;

}