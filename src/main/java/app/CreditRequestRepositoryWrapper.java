package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cees Bos ($Author$)
 * @version $Revision$ $Date$
 */
@Component
public class CreditRequestRepositoryWrapper {

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CreditRequest save(CreditRequest entity) {
        return creditRequestRepository.save(entity);
    }

    @Transactional
    public CreditRequest findOne(Long id) {
        return creditRequestRepository.findOne(id);
    }

}
