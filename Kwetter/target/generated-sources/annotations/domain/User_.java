package domain;

import domain.Tweet;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-23T14:01:22")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> salt;
    public static volatile ListAttribute<User, User> followers;
    public static volatile ListAttribute<User, User> following;
    public static volatile SingularAttribute<User, String> bio;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> username;
    public static volatile ListAttribute<User, Tweet> likes;

}