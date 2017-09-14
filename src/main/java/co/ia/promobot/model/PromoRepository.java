package co.ia.promobot.model;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Stateful
public class PromoRepository {

    @Inject
    private EntityManager entityManager;

    public PromoRepository(){}

    public Promo save(Promo promo){
        entityManager.persist(promo);
        return promo;
    }

    public Promo get(long id){
        return entityManager
                .find(Promo.class, id);
    }

    public List<Promo> getAll(){
        return entityManager
                .createNamedQuery("Promo.findAll")
                .getResultList();
    }
}
