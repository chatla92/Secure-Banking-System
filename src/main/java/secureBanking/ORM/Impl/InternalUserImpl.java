package secureBanking.ORM.Impl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import secureBanking.ORM.Entity.InternalUser;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface InternalUserImpl extends CrudRepository<InternalUser, Long> {


    public List<InternalUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("select employee_id from InternalUser")
    List<Integer> getallEmployee_id();


}
