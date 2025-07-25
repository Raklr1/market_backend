package top.otsuland.market.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import top.otsuland.market.common.PictureUtils;
import top.otsuland.market.entity.PicIcon;
import top.otsuland.market.mapper.PicIconMapper;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private PicIconMapper picIconMapper;

    @Override
    public boolean saveIcon(int userId, String name, MultipartFile picture) throws IOException {
        PicIcon pim = picIconMapper.selectByUserId(userId).orElse(null);
        int row = 0;
        // 如果没有头像图片
        if(pim == null) {
            PicIcon pic = new PicIcon();
            pic.setName(name);
            pic.setUserId(userId);
            pic.setPicture(picture.getBytes());
            // 生成缩略图片
            pic.setPictureNarrow(PictureUtils.getNarrowPhoto(picture));
            row = picIconMapper.insert(pic);
        } else {
            // 如果已有头像图片
            pim.setName(name);
            pim.setPicture(null);
            pim.setPictureNarrow(null);
            row = picIconMapper.updateById(pim);
        }
        if(row <= 0) {
            return false;
        }
        return true;
    }
}
