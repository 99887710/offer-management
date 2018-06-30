package idv.jeff.offer.mgmt.Exception;


public class OfferExpiredException extends ResourceNotFoundException {

    public OfferExpiredException(String fieldName, Object fieldValue) {
        super("Offer", "expired", fieldName, fieldValue);
    }
}
