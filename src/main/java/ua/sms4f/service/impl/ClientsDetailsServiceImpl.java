package ua.sms4f.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sms4f.entity.ClientsDetails;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.ClientsDetailsRepository;
import ua.sms4f.repository.UserDBRepository;
import ua.sms4f.service.ClientsDetailsService;

import java.util.List;

@Service
public class ClientsDetailsServiceImpl implements ClientsDetailsService {

    private ClientsDetailsRepository clientsDetailsRepository;

    @Autowired
    private UserDBRepository userDBRepository;

    @Override
    public void saveClient(ClientsDetails clientsDetails) {
        clientsDetailsRepository.save(clientsDetails);
    }

    public ClientsDetails updateClient(ClientsDetails clientsDetails) throws Exception {

        ClientsDetails newCli;
        if (clientsDetails.getUserDB() == null) {
            throw new Exception("Incorrect client");
        }
        if (clientsDetailsRepository.findByUserDB(clientsDetails.getUserDB()) == null) {
            newCli = new ClientsDetails(clientsDetails.getUserDB());
            clientsDetailsRepository.save(newCli);
        }
        newCli = clientsDetailsRepository.findByUserDB(clientsDetails.getUserDB());
        newCli.setLastName(clientsDetails.getLastName());
        newCli.setFirstName(clientsDetails.getFirstName());
        newCli.setIdentificationNumber(clientsDetails.getIdentificationNumber());
        newCli.setPassport(clientsDetails.getPassport());
        newCli.setCity(clientsDetails.getCity());
        clientsDetailsRepository.save(newCli);
        return newCli;
    }

    @Override
    public List<ClientsDetails> findAll() {
        return clientsDetailsRepository.findAll();
    }

    @Override
    public void saveAll(List<ClientsDetails> clientsDetails) {
        clientsDetailsRepository.saveAll(clientsDetails);
    }

    @Autowired
    public void setClientsDetailsRepository(ClientsDetailsRepository clientsDetailsRepository) {
        this.clientsDetailsRepository = clientsDetailsRepository;
    }

}