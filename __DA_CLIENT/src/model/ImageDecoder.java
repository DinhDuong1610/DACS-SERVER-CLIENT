package model;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.swing.ImageIcon;

public class ImageDecoder {
    public static ImageIcon decodeImageIcon(String encodedImage) {
        byte[] imageBytes = Base64.getDecoder().decode(encodedImage);
        ImageIcon imageIcon = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(imageBytes));
            imageIcon = (ImageIcon) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageIcon;
    }
}
