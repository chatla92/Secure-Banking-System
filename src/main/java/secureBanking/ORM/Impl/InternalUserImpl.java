package secureBanking.ORM.Impl;
import secureBanking.ORM.Entity.InternalUser;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface InternalUserImpl extends CrudRepository<InternalUser, Long> {


    public List<InternalUser> findByEmail(String email);


}
