
package app.adapter.in.validators;

import org.springframework.stereotype.Component;

@Component
public class UserValidator extends SimpleValidator
{
    
    public String nameValidator(String value) throws Exception
    {
        
        return stringValidator("nombre de la persona",value);
        
    }
    
    public long documentValidator(String value) throws Exception
    {
        
        return longValidator("Documento de la persona",value);
        
    }
    
    public int ageValidator(String value) throws Exception
    {
        
        return integerValidator("Edad de la persona",value);
        
    }
    
    public String userNameValidator(String value) throws Exception
    {
        
        return stringValidator("Usuario de la persona",value);
        
    }
    
    public String passwordValidator(String value) throws Exception
    {
        
        return stringValidator("Contraseña de la persona",value);
        
    }
    
    public String dateValidator(String value) throws Exception
    {
        
        return stringValidator("Fecha de nacimiento de la persona",value);
        
    }
     
    public String genderValidator(String value) throws Exception
    {
        
        return stringValidator("Genero de la persona",value);
        
    }
    
    public String EmailValidator(String value) throws Exception
    {
        
        return stringValidator("Email de la persona",value);
        
    }
    
    public long PhoneNumber(String value) throws Exception
    {
        
        return longValidator("Telefono de la persona",value);
        
    }
     
    public String addresValidator(String value) throws Exception
    {
        
        return stringValidator("Direccion de la persona",value);
        
    }
    
    //policy info
    
    public long policyNumber(String value) throws Exception
    {
        
        return longValidator("Numero de la poliza",value);
        
    }
    
    public String policyName(String value) throws Exception
    {
        
        return stringValidator("Nombre de la poliza",value);
        
    }
    
    public boolean policyStatus(String value) throws Exception
    {
        return booleanValidator("Estado de la poliza",value);
    }
    
    public String policyEndDate(String value) throws Exception
    {
        return stringValidator("Fecha fin de la poliza",value);       
    }
    
    
    //contact emergency into
     
    public String contactNameValidator(String value) throws Exception
    {
        
        return stringValidator("Nombre de contacto de la persona",value);
        
    }
     
    public String relationshipValidator(String value) throws Exception
    {
        
        return stringValidator("Relacion con la persona",value);
        
    }
     
    public long contactNumberValidator(String value) throws Exception
    {
        
        return longValidator("Numero de contacto de la persona",value);
        
    }

    
}
