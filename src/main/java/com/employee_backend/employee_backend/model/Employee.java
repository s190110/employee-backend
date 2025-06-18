package com.employee_backend.employee_backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
public class Employee {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Min(value = 0, message = "Age must be positive")
    private Integer age;

    @NotNull(message = "Date of joining is required")
    @PastOrPresent(message = "Date of joining cannot be in the future")
    private LocalDate dateOfJoined;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Wing is required")
    @ManyToOne
    @JoinColumn(name = "wing_id")
    private Wing wing;

    @NotNull(message = "position is required")
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @NotNull(message = "Has Experience is required")
    private Boolean hasExperience;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Experience> experiences = new ArrayList<>();

    private String totalExperience;

    @Lob
    @Column(name = "photo", columnDefinition = "TEXT")
    @NotBlank(message = "Photo is required")
    @Pattern(
      regexp = "^data:image\\/[^;]+;base64,[a-zA-Z0-9+/=\\r\\n]+$",
      message = "Photo must be a valid base64 image"
    )
    private String photo;


    @NotEmpty(message = "Skills are required")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employee_skills",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public LocalDate getDateOfJoined() {
		return dateOfJoined;
	}


	public void setDateOfJoined(LocalDate dateOfJoined) {
		this.dateOfJoined = dateOfJoined;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Wing getWing() {
		return wing;
	}


	public void setWing(Wing wing) {
		this.wing = wing;
	}


	public Position getPosition() {
		return position;
	}


	public void setPosition(Position position) {
		this.position = position;
	}


	public Boolean getHasExperience() {
		return hasExperience;
	}


	public void setHasExperience(Boolean hasExperience) {
		this.hasExperience = hasExperience;
	}


	public List<Experience> getExperiences() {
		return experiences;
	}


	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}


	public String getTotalExperience() {
		return totalExperience;
	}


	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public List<Skill> getSkills() {
		return skills;
	}


	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
    
    @ManyToOne
    private Department department;

    @OneToOne(mappedBy = "employee")
    private User user;

}
