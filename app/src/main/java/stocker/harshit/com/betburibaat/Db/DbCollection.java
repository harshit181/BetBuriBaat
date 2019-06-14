package stocker.harshit.com.betburibaat.Db;

import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.bson.Document;

import java.util.Date;

public class DbCollection {

    static boolean loginDone=false;

    public static StitchAppClient getClient() {
        return client;
    }

    public static RemoteMongoClient getMongoClient() {
        return mongoClient;
    }

    public static RemoteMongoCollection<Document> getColl() {
        return coll;
    }

    final static StitchAppClient client =
            Stitch.initializeDefaultAppClient("betburibaat-uclyi");

    final static RemoteMongoClient mongoClient =
            client.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas");

    final static RemoteMongoCollection<Document> coll =
            mongoClient.getDatabase("BBB1").getCollection("MaybeAll");

    public static void initLogin()
    {
        if(!loginDone) {
            loginDone=true;
            client.getAuth().loginWithCredential(new AnonymousCredential());
        }
    }
    public static void initStich()
    {

        Document x =new Document();
        x.put("Match_Name","w"+new Date().getTime());
        x.put("owner_id",client.getAuth().getUser().getId());
        coll.insertOne(x);
    }
}
