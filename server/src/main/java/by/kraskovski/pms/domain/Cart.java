package by.kraskovski.pms.domain;

import by.kraskovski.pms.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Description database table "cart"
 */
@Entity
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    @Setter
    private User user;

    @Getter
    @Setter
    private LocalDateTime createDate;

    @Getter
    @Setter
    private double totalCost;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            orphanRemoval = true)
    @Getter
    private Set<CartProductStock> cartProductStocks = new HashSet<>();

    public Cart() {
        this.createDate = LocalDateTime.now();
    }

    public Cart(final User user) {
        this.createDate = LocalDateTime.now();
        this.user = user;
    }
}
