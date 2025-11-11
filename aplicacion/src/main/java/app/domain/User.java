
package app.domain;

public class User
{

    private Long id;
    private Long document;
    private String name;
    
    public User(Long id1, Long document1, String name1)
    {
    
    }

    public Long getId()
    {
        
        return id;
        
    }

    public void setId(Long id)
    {
        
        this.id = id;
        
    }

    public Long getDocument()
    {
        
        return document;
        
    }

    public void setDocument(Long document)
    {
        
        this.document = document;
        
    }

    public String getName()
    {
        
        return name;
        
    }

    public void setName(String name)
    {
        
        this.name = name;
        
    }
    
}
