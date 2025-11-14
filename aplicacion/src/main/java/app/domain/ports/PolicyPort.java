
package app.domain.ports;

import app.domain.model.Policy;

public interface PolicyPort {
    
    public Policy findById(Policy policy) throws Exception;
    public void save(Policy policy) throws Exception;
}
