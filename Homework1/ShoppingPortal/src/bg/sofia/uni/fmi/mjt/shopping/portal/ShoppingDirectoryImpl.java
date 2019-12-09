package bg.sofia.uni.fmi.mjt.shopping.portal;

import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.NoOfferFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.OfferAlreadySubmittedException;
import bg.sofia.uni.fmi.mjt.shopping.portal.exceptions.ProductNotFoundException;
import bg.sofia.uni.fmi.mjt.shopping.portal.offer.Offer;

import java.util.Collection;

public class ShoppingDirectoryImpl implements ShoppingDirectory {

    @Override
    public Collection<Offer> findAllOffers(String productName) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Offer findBestOffer(String productName) throws ProductNotFoundException, NoOfferFoundException {
        return null;
    }

    @Override
    public Collection<PriceStatistic> collectProductStatistics(String productName) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void submitOffer(Offer offer) throws OfferAlreadySubmittedException {

    }
}
