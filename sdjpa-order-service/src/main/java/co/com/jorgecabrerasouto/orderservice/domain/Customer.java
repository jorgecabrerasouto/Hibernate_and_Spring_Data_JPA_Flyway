package co.com.jorgecabrerasouto.orderservice.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class Customer extends BaseEntity{
	@Size(max = 50)
	private String customerName;
	
	@Valid
	@Embedded
	private Address address;
		
	@Size(max = 20)
	private String phone;
	
	@Size(max = 255)
	@Email
	private String email;
	
	@Version
	private Integer version;
	
	@OneToMany(mappedBy ="customer")
    private Set<OrderHeader> orders = new LinkedHashSet<>();

	@Size(max = 50)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Size(max = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<OrderHeader> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderHeader> orders) {
		this.orders = orders;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
