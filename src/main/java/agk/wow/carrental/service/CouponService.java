package agk.wow.carrental.service;

import agk.wow.carrental.constant.ResponseBodyMessage;
import agk.wow.carrental.model.Coupon;
import agk.wow.carrental.repository.CouponRepository;
import agk.wow.carrental.rpcdomain.ResponseBody;
import agk.wow.carrental.rpcdomain.request.CouponRequest;
import agk.wow.carrental.rpcdomain.request.UpdateCouponRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CouponService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> getCoupons() {
        Iterable<Coupon> coupons = this.couponRepository.findAll();

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage(), coupons), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> addCoupon(CouponRequest couponRequest) {
        Coupon newCoupon = new Coupon();
        newCoupon.setCouponCode(couponRequest.getCouponCode());
        newCoupon.setStartDate(LocalDateTime.parse(couponRequest.getStartDate(), FORMATTER));
        newCoupon.setExpiredDate(LocalDateTime.parse(couponRequest.getExpiredDate(), FORMATTER));
        newCoupon.setDiscountPercentage(Float.valueOf(couponRequest.getDiscountPercentage()));
        newCoupon.setIsUsed("N");

        this.couponRepository.save(newCoupon);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> updateCoupon(UpdateCouponRequest updateCouponRequest) {
        String couponCode = updateCouponRequest.getCouponCode();
        LocalDateTime startDate = LocalDateTime.parse(updateCouponRequest.getStartDate(), FORMATTER);
        LocalDateTime expiredDate = LocalDateTime.parse(updateCouponRequest.getExpiredDate(), FORMATTER);
        Float discountPercentage = Float.valueOf(updateCouponRequest.getDiscountPercentage());
        String isUsed = updateCouponRequest.getIsUsed();

        this.couponRepository.updateCoupon(couponCode, startDate, expiredDate, discountPercentage, isUsed);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteCorporate(String couponCode) {
        this.couponRepository.deleteById(couponCode);

        return new ResponseEntity<>(new ResponseBody(ResponseBodyMessage.SUCCESS.getMessage()), HttpStatus.OK);
    }
}