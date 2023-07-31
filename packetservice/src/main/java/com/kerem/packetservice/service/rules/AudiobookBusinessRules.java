package com.kerem.packetservice.service.rules;

import com.kerem.commonpackage.utils.constants.Messages;
import com.kerem.commonpackage.utils.exceptions.BusinessException;
import com.kerem.packetservice.repository.AudiobookRepository;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AudiobookBusinessRules {
    private final AudiobookRepository repository;
    private final Tika tika;
    private final static String  blackListPathFile = "C:\\turkcellbootcamp\\PacketService\\blacklist.txt";

    public void checkIfAudiobookexists(int id){
        if ((!repository.existsById(id))) throw new BusinessException(Messages.Audiobook.NotExists);
    }



    public void isFileContentTypeSupported(String base64code){
        tika.setMaxStringLength(-1);
        String mimeType = tika.detect(Base64.getDecoder().decode(base64code));
        System.out.println(mimeType);
        if (!(mimeType.equals("audio/mpeg"))) throw new BusinessException(Messages.Audiobook.NotSupportedContentType);
        }

}





    