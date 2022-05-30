package ua.sms4f.service;

import ua.sms4f.entity.ClientsDetails;

import java.util.List;

public interface ClientsDetailsService {

    void saveClient(ClientsDetails clientsDetails);
    ClientsDetails updateClient(ClientsDetails clientsDetails) throws Exception;
    List<ClientsDetails> findAll();
    void saveAll(List<ClientsDetails> clientsDetails);
}
