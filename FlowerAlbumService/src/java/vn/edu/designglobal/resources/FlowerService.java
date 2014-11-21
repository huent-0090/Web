/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.designglobal.resources;

import java.awt.Image;
import java.io.ByteArrayInputStream;
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
        //TODO write your implementation code here:
        byte[] bytes = getFlowerBytes(name);
        return getImage(bytes, false);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getThumbnails")
    public List<Image> getThumbnails() throws IOException {
        List<byte[]>flowers = allFlowers();
        List<Image>flowerList = new ArrayList<Image>(flowers.size());
        for (byte[]flower:flowers) {
            flowerList.add(getImage(flower, true));
        }
        return flowerList;
    }
    
    private byte[] getFlowerBytes(String name) throws IOException {
        URL resource = this.getClass().getResource("/org/flower/resources/" +name+".jpg");
        return getBytes(resource);
    }

    private byte[] getBytes(URL resource) throws IOException {
        InputStream in = resource.openStream();
        ByteArrayOutStream bos = new ByteArrayOutStream();
        byte[] buf = new byte[1024];
        for(int read; (read = in.read(buf)) !=-1;) {
            bos.write(buf, 0, read);
        }
        return bos.toByteArray();
    }
    
    private Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) readers.next();
        ImageReadParam param = reader.getDefaultReadParam();
        if (isThumbnail) {
            param.setSourceSubsampling(4, 4, 0, 0);
        }
        reader.setInput(iis, true);
        return reader.read(0, param);
    }
    
    private static final String[] FLOWERS = {"Nara1", "Nara2", "Nara3"};
    
    private List allFlowers() throws IOException {
        List flowers = new ArrayList();
        for (String flower:FLOWERS) {
            URL resource = this.getClass().getResource("/org/flower/resources/"+flower+".jpg");
            flowers.add(getBytes(resource));
        }
        return flowers;
    }

    private static class ByteArrayOutStream {

        public ByteArrayOutStream() {
        }

        private void write(byte[] buf, int i, int read) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private byte[] toByteArray() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
