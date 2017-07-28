package data;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FirebaseConnection {


    public static class User {
        public String name;
        public String text;

        public User(String name, String text) {
            this.name = name;
            this.text = text;
        }
    }


    public static void main(String[] args) throws IOException {
        // Fetch the service account key JSON file contents
        FileInputStream serviceAccount = new FileInputStream("studentsmonitoring-firebase-adminsdk-wanxa-f26b945978.json");

// Initialize the app with a service account, granting admin privileges
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                .setDatabaseUrl("https://studentsmonitoring.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("server/saving-data/fireblog");


        DatabaseReference usersRef = ref.child("messages");

        HashMap<String, User> users = new HashMap<String, User>();
        users.put("one", new User("Sveta", "bebebe"));
        users.put("two", new User("Kate", "bobobo"));

        usersRef.setValue(users, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.out.println("Data could not be saved " + databaseError.getMessage());
            } else {
                System.out.println("Data saved successfully.");
            }
        });


        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        System.exit(0);

//        usersRef.child("alanisawesome").setValue(new User("June 23, 1912", "Alan Turing"));
//        usersRef.child("gracehop").setValue(new User("December 9, 1906", "Grace Hopper"));


    }


}
