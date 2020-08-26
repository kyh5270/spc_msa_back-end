package eu.dreamix.spc.service;

import eu.dreamix.spc.entity.dto.SpcDto;

public interface TimecheckService {

    void sendSimpleMessage(SpcDto input);
}
