package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.CorporateRequest;
import agk.wow.carrental.service.CorporateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("corporate")
public class CorporateController {
    @Autowired
    private CorporateService corporateService;

    @GetMapping(value = "/get/corporates")
    public ResponseEntity getCorporates() {
        return this.corporateService.getCorporates();
    }

    @PostMapping(value = "/add/corporate")
    public ResponseEntity addCorporate(@RequestBody CorporateRequest corporateRequest) {
        return this.corporateService.addCorporate(corporateRequest);
    }

    @PostMapping(value = "/update/corporate")
    public ResponseEntity updateCorporate(@RequestBody CorporateRequest corporateRequest) {
        return this.corporateService.updateCorporate(corporateRequest);
    }

    @DeleteMapping(value = "/delete/corporate")
    public ResponseEntity deleteCorporate(@RequestParam String registrationNumber) {
        return this.corporateService.deleteCorporate(registrationNumber);
    }
}