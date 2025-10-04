package app.domain.model;

import java.security.SecureRandom;

public class User extends Person
{
    
    public String userName;
    public String password;
    
    public User(){} /* Empty Constructor */
    
    // Methods for generating credentials
    private String generateUserName(String nameComplete, String lastnameComplete) {
        String firstNameOnly = (nameComplete != null && !nameComplete.isBlank())
                ? nameComplete.trim().split("\\s+")[0]
                : "user";

        String firstLastnameOnly = (lastnameComplete != null && !lastnameComplete.isBlank())
                ? lastnameComplete.trim().split("\\s+")[0]
                : "surname";

        return (firstNameOnly + "." + firstLastnameOnly).toLowerCase();
    }
    
    public User(String nameComplete, String lastnameComplete)
    {

        this.userName = generateUserName(nameComplete, lastnameComplete);
        this.password = generatePassword(10);
        
    }
    
    private String generatePassword(int length)
    {
        
        if (length < 4) throw new IllegalArgumentException("length must be >= 4");

            final String U = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            final String L = "abcdefghijklmnopqrstuvwxyz";
            final String D = "0123456789";
            final String S = "+-*/.,_";
            final String ALL = U + L + D + S;

            SecureRandom rnd = new SecureRandom();
            StringBuilder sb = new StringBuilder(length);

            // For default is 1 for each group
            sb.append(U.charAt(rnd.nextInt(U.length())));
            sb.append(L.charAt(rnd.nextInt(L.length())));
            sb.append(D.charAt(rnd.nextInt(D.length())));
            sb.append(S.charAt(rnd.nextInt(S.length())));

            for (int i = 4; i < length; i++)
            {
                
                sb.append(ALL.charAt(rnd.nextInt(ALL.length())));
                
            }

            // shuffle
            char[] arr = sb.toString().toCharArray();
            
            for (int i = arr.length - 1; i > 0; i--)
            {
                
                int j = rnd.nextInt(i + 1);
                char tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
                
            }
            
            return new String(arr);
            
    }
    
    
    /*Methods getters and setters*/
    public void setUserName(String userName)
    {
        
        this.userName = userName;
        
    }

    public void setPassword(String password)
    {
        
        this.password = password;
        
    }
    
    public String getUserName()
    {
        
        return userName;
        
    }
    
    public String getPassword()
    {
        
        return password;
        
    }

    public void setDocument(long documentValidator) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public long getDocument() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
