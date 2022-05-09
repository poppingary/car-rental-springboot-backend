package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Corporate;
import agk.wow.carrental.repository.CorporateRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.CorporateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CorporateService {
    @Autowired
    private CorporateRepository corporateRepository;

    public ResponseEntity<?> getCorporates() {
        Iterable<Corporate> types = this.corporateRepository.findAll();

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), types), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> addCorporate(CorporateRequest corporateRequest) {
        Corporate newCorporate = new Corporate();
        newCorporate.setRegistrationNumber(corporateRequest.getRegistrationNumber());
        newCorporate.setCorporateDiscount(Float.valueOf(corporateRequest.getCorporateDiscount()));
        newCorporate.setCorporateName(corporateRequest.getCorporateName());

        this.corporateRepository.save(newCorporate);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> updateCorporate(CorporateRequest corporateRequest) {
        String registrationNumber = corporateRequest.getRegistrationNumber();
        Float corporateDiscount = Float.valueOf(corporateRequest.getCorporateDiscount());
        String corporateName = corporateRequest.getCorporateName();

        this.corporateRepository.updateCorporate(registrationNumber, corporateDiscount, corporateName);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteCorporate(String registrationNumber) {
        this.corporateRepository.deleteById(registrationNumber);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }
}