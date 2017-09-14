package co.ia.promobot.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Promo.findAll",
                query = "Select p from Promo p")
})
public class Promo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String imageURL;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    protected Promo(){}

    public Promo(String imageURL, String description, Shop shop) {
        this.imageURL = imageURL;
        this.description = description;
        this.shop = shop;
    }

    public double lat(){
        return this.shop.getLatitude();
    }

    public double lng(){
        return this.shop.getLongitude();
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Promo promo = (Promo) o;

        return id == promo.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
