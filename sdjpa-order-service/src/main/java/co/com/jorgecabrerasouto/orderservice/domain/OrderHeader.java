package co.com.jorgecabrerasouto.orderservice.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

@Entity
@AttributeOverrides({
    @AttributeOverride(
            name = "shippingAddress.address",
            column = @Column(name = "shipping_address")
    ),
    @AttributeOverride(
            name = "shippingAddress.city",
            column = @Column(name = "shipping_city")
    ),
    @AttributeOverride(
            name = "shippingAddress.state",
            column = @Column(name = "shipping_state")
    ),
    @AttributeOverride(
            name = "shippingAddress.zipCode",
            column = @Column(name = "shipping_zip_code")
    ),
    @AttributeOverride(
            name = "billToAddress.address",
            column = @Column(name = "bill_to_address")
    ),
    @AttributeOverride(
            name = "billToAddress.city",
            column = @Column(name = "bill_to_city")
    ),
    @AttributeOverride(
            name = "billToAddress.state",
            column = @Column(name = "bill_to_state")
    ),
    @AttributeOverride(
            name = "billToAddress.zipCode",
            column = @Column(name = "bill_to_zip_code")
    )
})
public class OrderHeader extends BaseEntity { 

    private String customer;
    
    @Embedded
    private Address shippingAddress;
    
    @Embedded
    private Address billToAddress;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    private Set<OrderLine> orderLines;
    
    public void addOrderLine (OrderLine orderLine) {
    	if (orderLines == null) {
    		orderLines = new HashSet<>();
    	}
    	
    	orderLines.add(orderLine);
    	orderLine.setOrderHeader(this);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Set<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Set<OrderLine> orderLines) {
		this.orderLines = orderLines;
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
		return Objects.equals(billToAddress, other.billToAddress) && Objects.equals(customer, other.customer)
				&& Objects.equals(orderLines, other.orderLines) && orderStatus == other.orderStatus
				&& Objects.equals(shippingAddress, other.shippingAddress);
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(billToAddress, customer, orderLines, orderStatus, shippingAddress);
		return result;
	}
}