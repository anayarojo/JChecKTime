
package JCHECKTIME.TOOLS.DigitalPersona;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import java.awt.Image;
import org.apache.commons.codec.binary.Base64;

public class ConvertirStringTo {
    
    public static Image Imagen(String string){
        
        Image Imagen=DPFPGlobal.
                getSampleConversionFactory().
                createImage(Sample(string));
        
        return Imagen;
    }
    
    public static DPFPSample Sample(String string){
        
        DPFPSample sample= DPFPGlobal.getSampleFactory().createSample();
        sample.deserialize(Bytes(string));
        return sample;
    }
    
    public static byte[] Bytes(String string){
        byte[] Bytes = Base64.decodeBase64(string);
        return  Bytes;
    }
}
