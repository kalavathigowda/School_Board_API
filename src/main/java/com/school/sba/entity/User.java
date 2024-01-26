package com.school.sba.entity;

import com.school.sba.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int userId;
        @Column(unique=true)
        private String userName;
        private String password;
        private String firstName;
        private String lastName;
        private long contactNo;
        @Column(unique = true)
        private String email;
        @Enumerated(EnumType.STRING)
        private UserRole userrole;
        
        @ManyToOne
        private School school;
        
		public School getSchool() {
			return school;
		}
		public void setSchool(School school) {
			this.school = school;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public long getContactNo() {
			return contactNo;
		}
		public void setContactNo(long contactNo) {
			this.contactNo = contactNo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public UserRole getUserrole() {
			return userrole;
		}
		public void setUserrole(UserRole userrole) {
			this.userrole = userrole;
		}
	@Override
	public String toString() {
	 return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
					+ firstName + ", lastName=" + lastName + ", contactNo=" + contactNo + ", email=" + email
					+ ", userrole=" + userrole + "]";
		}	
        
}
