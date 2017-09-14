package co.ia.promobot.services;

import co.ia.promobot.model.Location;
import co.ia.promobot.model.Promo;
import co.ia.promobot.model.PromoRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class FindBestPromoService {

    @Inject
    private PromoRepository promoRepository;

    protected FindBestPromoService(){}

    public Promo execute(Location actualLocation){
        List<Promo> promos =  promoRepository.getAll();
        Promo bestPromo = promos.get(0);
        double minimunDistance = Long.MAX_VALUE;
        for (Promo p :
                promos) {
            double distance = computeDistanceBetween(
                    actualLocation.getLatitude(),
                    actualLocation.getLongitude(),
                    p.lat(),
                    p.lng());
            if(minimunDistance > distance){
                minimunDistance = distance;
                bestPromo = p;
            }
        }
        return bestPromo;
    }

    public double computeDistanceBetween(double lat1, double lng1, double lat2, double lng2){
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lng2-lng1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        return d;
    }

    public double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }




}
