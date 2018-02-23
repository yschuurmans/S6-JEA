package domain;

import domain.Tweet;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-23T11:10:08")
@StaticMetamodel(Hashtag.class)
public class Hashtag_ { 

    public static volatile ListAttribute<Hashtag, Tweet> tweetsUsingHashtag;
    public static volatile SingularAttribute<Hashtag, Long> id;
    public static volatile SingularAttribute<Hashtag, String> hashtag;

}