package agk.wow.carrental.repository;

import agk.wow.carrental.model.Coupon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, String> {
    @Modifying
    @Query("UPDATE agk_coupon c " +
            "SET " +
            "c.startDate = :startDate, " +
            "c.expiredDate = :expiredDate, " +
            "c.discountPercentage = :discountPercentage, " +
            "c.isUsed = :isUsed " +
            "WHERE c.couponCode = :couponCode")
    void updateCoupon(@Param("couponCode") String couponCode,
                      @Param("startDate") LocalDateTime startDate,
                      @Param("expiredDate") LocalDateTime expiredDate,
                      @Param("discountPercentage") Float discountPercentage,
                      @Param("isUsed") String isUsed);
}