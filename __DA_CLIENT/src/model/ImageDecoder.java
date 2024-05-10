package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

public class ImageDecoder {
    public static ImageIcon decodeStringToImageIcon(String encodedImage) {
        try {
            byte[] byteArr = Base64.getDecoder().decode(encodedImage);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArr);
            BufferedImage image = ImageIO.read(byteArrayInputStream);
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
//    public static ImageIcon decodeStringToImageIcon(String encodedImage) {
//    	new Thread(()->{
//            try {
//                // Giải mã Base64 trong một luồng riêng để cải thiện hiệu suất
//                byte[] byteArr = Base64.getDecoder().decode(encodedImage);
//                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArr);
//                BufferedImage image = ImageIO.read(byteArrayInputStream);
//
//                // Thay đổi kích thước ảnh nếu cần
//                Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//
//                return new ImageIcon(scaledImage);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//    	}).start();
//    }
    

}
