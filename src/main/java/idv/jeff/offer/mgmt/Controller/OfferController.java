package idv.jeff.offer.mgmt.Controller;

import idv.jeff.offer.mgmt.Exception.OfferExpiredException;
import idv.jeff.offer.mgmt.Exception.ResourceNotFoundException;
import idv.jeff.offer.mgmt.Model.Offer;
import idv.jeff.offer.mgmt.Repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferController {

    @Autowired
    OfferRepository offerRepository;

    // Get All Offers
    @GetMapping("/offers")
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    // Create a new Offer
    @PostMapping("/offers")
    public Offer createOffer(@Valid @RequestBody Offer offer) {
        return offerRepository.save(offer);
    }

    // Get a Single Offer
    @GetMapping("/offers/{id}")
    public Offer getOfferById(@PathVariable(value = "id") Long postId) {
        return offerRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", postId));
    }

    // Update a Offer
    @PutMapping("/offers/{id}")
    public Offer updateOffer(@PathVariable(value = "id") Long offerId,
                           @Valid @RequestBody Offer offerDetails) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", offerId));

        Date now = new Date();
        if (offer.getDueDate().after(now)) {
            offer.setOfferStatus(offerDetails.getOfferStatus());
            offer.setPrice(offerDetails.getPrice());
            offer.setCurrency(offerDetails.getCurrency());
            offer.setQuantity(offerDetails.getQuantity());
            offer.setRemark(offerDetails.getRemark());
            Offer updatedOffer = offerRepository.save(offer);
            return updatedOffer;
        } else {
            throw new OfferExpiredException("id", offerId);
        }
    }

    // Delete a Offer
    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable(value = "id") Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", offerId));

        offerRepository.delete(offer);

        return ResponseEntity.ok().build();
    }
}
