package com.nighthawk.spring_portfolio.mvc.person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @NonNull
    private int height; 
    
    @NonNull
    private int weight;
    
    @NonNull
    private int freetime;
    
    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name, Date dob, int height, int weight, int freetime ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.freetime = freetime;
    }

    public String toString()
    {
        return ("{ \"ID\":"  +this.id+
               ", \"name\":"  +this.name+ 
               ", \"email\":" +this.email+ 
               ", \"dob\":" +this.dob+
               ", \"age\":" +this.getAge()+
               ", \"height in cm\":" +this.height+
               ", \"weight\":" +this.weight+
               ", \"Freetime to workout\":" +this.freetime+
               " }");
    }

    public String getProfiletoString()
    {
        return ("{ \"ID\":"  +this.id+
               ", \"name\":"  +this.name+ 
               ", \"height\":" +this.height+
               ", \"weight\":" +this.weight+
               ", \"Freetime to workout\":" +this.freetime+
               " }");
    }
    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    public void setStats(Map<String, Map<String, Object>> st)
    {
        for(String str:st.keySet())
        {
            stats.put(str,st.get(str));
        }
    }
    

    public static void main(String[] args)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Person rohan = new Person();
            Person bob = new Person("bob@gmail.com", "Q@W#E$R%TY", "Bob",
                                     sdf.parse("1600-03-01"), 189, 200, 60) ;

            System.out.println(rohan);
            System.out.println(bob);
        } catch (Exception e) 
        {
            System.out.println("error; try yyyy-MM-dd");
        }
    }

    



}