package idv.jeff.offer.mgmt.Repository;

import idv.jeff.offer.mgmt.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}
