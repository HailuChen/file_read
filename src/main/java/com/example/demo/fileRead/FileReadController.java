package com.example.demo.fileRead;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileReadController {

    private static final int RET_CODE_SUCCESS            = 0;
    private static final String RET_MSG_SUCCESS          = "文件读取成功!";
    private static final int RET_CODE_FILE_TYPE_ERROR    = 1;
    private static final String RET_MSG_FILE_TYPE_ERROR  = "文件格式不正确!";
    private static final int RET_CODE_FILE_READ_FAIL      = 2;
    private static final String RET_MSG_FILE_READ_FAIL   = "读文件出错时出错!";
    private static final int RET_CODE_FILE_EMPTY         = 3;
    private static final String RET_MSG_FILE_EMPTY       = "文件为空!";


    @Autowired
    private ReadFileService readFileService;

    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public ReturnMessage readFile(MultipartFile file) {

        if (file == null) {
            return new ReturnMessage(RET_CODE_FILE_EMPTY, RET_MSG_FILE_EMPTY);
        }
        String suffix = readFileService.validateFileContentType(file.getOriginalFilename());

        if (suffix == null) {
            return new ReturnMessage(RET_CODE_FILE_TYPE_ERROR, RET_MSG_FILE_TYPE_ERROR);
        }

        try {
            String content = this.readFileService.readFile(file.getInputStream(), suffix);
            return new ReturnMessage(RET_CODE_SUCCESS, RET_MSG_SUCCESS, content);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnMessage(RET_CODE_FILE_READ_FAIL, RET_MSG_FILE_READ_FAIL);
        }
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ReturnMessage hello() {
        return new ReturnMessage(RET_CODE_SUCCESS, RET_MSG_SUCCESS);
    }
}
