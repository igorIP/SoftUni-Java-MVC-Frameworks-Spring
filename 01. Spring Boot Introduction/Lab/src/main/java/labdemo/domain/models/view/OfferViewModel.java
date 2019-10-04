package labdemo.domain.models.view;


import labdemo.domain.enities.Offer;

import java.math.BigDecimal;

public class OfferViewModel implements Viewable<Offer> {

    private String id;
    private BigDecimal apartmentRent;
    private String apartmentType;
    private BigDecimal agencyCommission;

    public OfferViewModel() {
    }

    public String getId() {
        return id;
    }

    public BigDecimal getApartmentRent() {
        return apartmentRent;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public BigDecimal getAgencyCommission() {
        return agencyCommission;
    }
}
