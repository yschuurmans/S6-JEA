package websockets;

import domain.Tweet;
import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(
        value = "/ws/timeline",
        encoders = {}
)
@Stateless
public class TimelineSocket {
    private static final Logger LOG = Logger.getLogger(TimelineSocket.class.getName());
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private static final Map<String, Session> peerUsers = Collections.synchronizedMap(new HashMap<String, Session>());

    @Inject
    UserService userService;

    @OnMessage
    public void onMessage(final Session client, final String message) {
        if (message != null) {
            peerUsers.put(message, client);
            LOG.log(Level.INFO, "MESSAGE RECEIVED: " + message);
        }
    }

    public void alertNewTweet(Tweet newTweet) {
        String tweet = newTweet.toJson().toString();
        List<User> relevantUsers = new ArrayList<>(userService.getUserFollowers(newTweet.getPoster().getUsername()));
        relevantUsers.add(newTweet.getPoster());

        for (User relevantUser : relevantUsers) {
            sendMessage(relevantUser.getUsername(), tweet);
        }
    }

    private void sendMessage(String username, Object send) {
        if(peerUsers.containsKey(username))
            sendMessage(peerUsers.get(username), send);
    }

    private void sendMessage(Session peer, Object send) {
        try {
            if (peer.isOpen()) {
                peer.getBasicRemote().sendObject(send);
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        LOG.info("Connection opened ...");
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        LOG.info("Connection closed ...");
        peers.remove(peer);
        peerUsers.remove(peer.getId());
    }

    @OnError
    public void onError(Throwable t) {
        LOG.log(Level.INFO, "[ERROR] {0}", t.getMessage());
    }

    private void sent2All(Object answer) {

        peers.stream().forEach((peer) -> {
            sendMessage(peer, answer);
        });
    }
}
