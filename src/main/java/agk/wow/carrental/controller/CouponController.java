package agk.wow.carrental.controller;

import agk.wow.carrental.rpcdomain.request.CouponRequest;
import agk.wow.carrental.rpcdomain.request.UpdateCouponRequest;
import agk.wow.carrental.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping(value = "/get/coupons")
    public ResponseEntity<?> getCoupons() {
        return this.couponService.getCoupons();
    }

    @PostMapping(value = "/add/coupon")
    public ResponseEntity<?> addCorporate(@RequestBody CouponRequest couponRequest) {
        return this.couponService.addCoupon(couponRequest);
    }

    @PostMapping(value = "/update/coupon")
    public ResponseEntity<?> updateCorporate(@RequestBody UpdateCouponRequest updateCouponRequest) {
        return this.couponService.updateCoupon(updateCouponRequest);
    }

    @DeleteMapping(value = "/delete/coupon")
    public ResponseEntity<?> deleteCoupon(@RequestParam String couponCode) {
        return this.couponService.deleteCorporate(couponCode);
    }
}
