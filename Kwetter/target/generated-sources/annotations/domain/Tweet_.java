package domain;

import domain.Hashtag;
import domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-23T11:10:08")
@StaticMetamodel(Tweet.class)
public class Tweet_ { 

    public static volatile ListAttribute<Tweet, User> likedBy;
    public static volatile SingularAttribute<Tweet, String> tweetContent;
    public static volatile ListAttribute<Tweet, Hashtag> hashTagsUsed;
    public static volatile SingularAttribute<Tweet, Long> id;

}