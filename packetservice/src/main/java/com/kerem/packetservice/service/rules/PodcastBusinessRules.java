package com.kerem.packetservice.service.rules;

import com.kerem.commonpackage.utils.constants.Messages;
import com.kerem.commonpackage.utils.exceptions.BusinessException;
import com.kerem.packetservice.repository.PodcastRepository;
import lombok.AllArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@AllArgsConstructor
public class PodcastBusinessRules {
    private final PodcastRepository repository;
    private final Tika tika;

    public void checkIfPodcastexists(int id){
        if ((!repository.existsById(id))) throw new BusinessException(Messages.Podcast.NotExists);
    }



    public void isFileContentTypeSupported(String base64code){
        tika.setMaxStringLength(-1);
        String mimeType = tika.detect(Base64.getDecoder().decode(base64code));
        System.out.println(mimeType);
        if (!(mimeType.equals("audio/mpeg"))) throw new BusinessException(Messages.Podcast.NotSupportedContentType);
    }
}
