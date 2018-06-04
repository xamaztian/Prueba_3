package cl.xamaztian.pruebatres.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Nodes {
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();

    public DatabaseReference getRoot() {
        return root;
    }

    public DatabaseReference users(String userId){
        return root.child("userProducts").child(userId);
    }

    public DatabaseReference userProducts(String key){
        return root.child("userProducts").child(key);
    }

    public DatabaseReference userProduct(String user, String key){
        return root.child("userProducts").child(user).child(key);
    }
}
