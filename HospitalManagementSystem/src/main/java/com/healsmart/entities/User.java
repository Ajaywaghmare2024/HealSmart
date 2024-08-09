package com.healsmart.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
      private Long userId;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private String cellNo;
	private String securityQuestion;
	private String securityAnswer;
	
	
// ------------connect to Employee--------------
	@Exclude
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Employee employee;
	
	// ------------connect to Employee--------------
	@Exclude
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	private Patient patient;
	


	public User(String firstName, String lastName, String email, String password, String role, String cellNo,
			String securityQuestion, String securityAnswer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.cellNo = cellNo;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}

	//***************connection to employee 
	public void addEmployee(Employee e) {
		this.employee=e;
		this.employee.setUser(this);
		
	}
	//***************connection to patient 
	public void addPatient(Patient p) {
		this.patient=p;
		this.patient.setUser(this);
		}
		
//	
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
//		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+this.getRole().toUpperCase()));
//
//		return grantedAuthorities;
//		
//	}
//	
//	
//	@Override
//	public String getUsername() {
//		
//		return email;
//	}
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	
//      
      
}
