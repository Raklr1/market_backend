package top.otsuland.market.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.entity.PicIcon;

public interface FileService {
    boolean saveIcon(int userId, String name, MultipartFile picture) throws IOException;
}