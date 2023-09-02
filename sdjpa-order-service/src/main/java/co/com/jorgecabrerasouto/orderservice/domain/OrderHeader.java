package co.com.jorgecabrerasouto.orderservice.domain;

import java.util.Objects;

import jakarta.persistence.Entity;

@Entity
public class OrderHeader extends BaseEntity {

    private String customer;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderHeader other = (OrderHeader) obj;
		return Objects.equals(customer, other.customer);
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(customer);
		return result;
	}
}