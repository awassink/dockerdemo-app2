package app;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Test entity
 */
@Entity
@Table(name = "credit_request")
public class CreditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;

    private String naam;

    private String adres;

    private String postcode;

    private String plaats;

    private BigDecimal bedrag;

    private String status;

    private String ontvangendoor;

    private String verwerktdoor;

    private String afwijsreden;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationdate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationdate = creationDate;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(final String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(final String adres) {
        this.adres = adres;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(final String plaats) {
        this.plaats = plaats;
    }

    public BigDecimal getBedrag() {
        return bedrag;
    }

    public void setBedrag(final BigDecimal bedrag) {
        this.bedrag = bedrag;
    }

    public Status getStatus() {
        return Status.valueOf(status);
    }

    public void setStatus(final Status status) {
        this.status = status.name();
    }

    public String getOntvangendoor() {
        return ontvangendoor;
    }

    public void setOntvangendoor(final String ontvangendoor) {
        this.ontvangendoor = ontvangendoor;
    }

    public String getVerwerktdoor() {
        return verwerktdoor;
    }

    public void setVerwerktdoor(final String verwerktdoor) {
        this.verwerktdoor = verwerktdoor;
    }

    public String getAfwijsreden() {
        return afwijsreden;
    }

    public void setAfwijsreden(final String afwijsreden) {
        this.afwijsreden = afwijsreden;
    }
}
