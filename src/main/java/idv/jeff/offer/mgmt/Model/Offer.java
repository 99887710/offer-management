package idv.jeff.offer.mgmt.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "OFFER")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdTime", "lastModified"}, allowGetters = true)
public class Offer extends Audit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    private int quantity;

    private int price;

    @NotNull
    @Enumerated
    private Currency currency;

    @NotNull
    @Enumerated
    private OfferStatus offerStatus;

    private Date dueDate;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {return customer;}

    public void setCustomer(Customer customer) {this.customer = customer;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public Currency getCurrency() {return currency;}

    public void setCurrency(Currency currency) {this.currency = currency;}

    public OfferStatus getOfferStatus() {return offerStatus;}

    public void setOfferStatus(OfferStatus offerStatus) {this.offerStatus = offerStatus;}

    public Date getDueDate() {return dueDate;}

    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}

    public String getRemark() {return remark;}

    public void setRemark(String remark) {this.remark = remark;}
}

