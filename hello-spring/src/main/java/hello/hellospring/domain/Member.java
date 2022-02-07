package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;

    private String name;


    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(long Id)
    {
        this.Id = Id;
    }

    public long getId()
    {
        return Id;
    }

    public String getName()
    {
        return name;
    }

    
}
