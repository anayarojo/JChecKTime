
package JCHECKTIME.TOOLS.DigitalPersona;

import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import java.awt.Image;
import org.apache.commons.codec.binary.Base64;

public class ConvertirBytesTo {
    
    public static Image Imagen(byte[] ARR){
        
        Image Imagen=DPFPGlobal.
                getSampleConversionFactory().
                createImage(Sample(ARR));
        
        return Imagen;
    }
    
    public static DPFPSample Sample(byte[] ARR){
        
        DPFPSample sample= DPFPGlobal.getSampleFactory().createSample();
        sample.deserialize(ARR);
        return sample;
    }
    
    public static String String(byte[] ARR){
        
        String Conversion = Base64.encodeBase64String(ARR);
        return Conversion;
    }
    
    
}
