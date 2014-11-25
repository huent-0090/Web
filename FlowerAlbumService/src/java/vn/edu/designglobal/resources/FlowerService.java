/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.designglobal.resources;

import java.awt.*;
import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author laonhi
 */
@WebService(serviceName = "FlowerService")
@Stateless()
public class FlowerService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getFlower")
    public Image getFlower(@WebParam(name = "name") String name) throws IOException {
        byte[] bytes = getFlowerBytes(name);
        return getImage(bytes, false);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getThumbnails")
    public List<Image> getThumbnails() throws IOException {
        List<byte[]> flowers = allFlowers();
        List<Image> flowerList = new ArrayList<Image>(flowers.size());
        for (byte[] flower : flowers) {
            flowerList.add(getImage(flower, true));
        }
        return flowerList;
    }
    
    private byte[] getFlowerBytes(String name) throws IOException {
        URL resource = this.getClass().getResource("/org/flower/resources/" + name + ".jpg");
        return getBytes(resource);
    }

    private byte[] getBytes(URL resource) throws IOException {
        InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        for (int read; (read = in.read(buf)) != -1;) {
            bos.write(buf, 0, read);
        }
        return bos.toByteArray();
    }
    
    private Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        if (isThumbnail) {
            param.setSourceSubsampling(4, 4, 0, 0);
        }
        return reader.read(0, param);
    }
    
    private static final String[] FLOWERS = {"Nara1", "Nara3"};
    
    private List allFlowers() throws IOException {
        List<byte[]> flowers = new ArrayList();
        for (String flower : FLOWERS) {
            URL resource = this.getClass().getResource("/flower/album/resources/" + flower + ".jpg");
            flowers.add(getBytes(resource));
        }
        return flowers;
    }
}
