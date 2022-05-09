package agk.wow.carrental.repository;

import agk.wow.carrental.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Reservation findReservationByCustomerId(String customerId);
}