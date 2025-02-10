package edu.snhu.kylerose.fishinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // Variables for text view and buttons
    TextView main_screen;

    Button north_button;
    Button east_button;
    Button south_button;
    Button west_button;
    Button getItem_button;
    Button restart_button;

    // Variables to store users current location and input
    String user_room = "Parking Lot";
    String user_input;
    // Size variable for determining if user has collected all items
    int goal_size = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // User inventory
        ArrayList<String> user_inventory = new ArrayList<>();
        // Map of maps. Stores different locations with available moves for each. Creates gameplay map.
        Map<String, Map<String, String>> rooms = new HashMap<>();

        // Creating all locations and adding to rooms
        Map<String, String> parkingLot = new HashMap<>();
        parkingLot.put("North", "Pond 1");
        parkingLot.put("South", "Pond 4");
        parkingLot.put("East", "Pond 6");
        parkingLot.put("West", "Pond 3");
        rooms.put("Parking Lot", parkingLot);

        Map<String, String> pond1 = new HashMap<>();
        pond1.put("East", "Pond 2");
        pond1.put("South", "Parking Lot");
        pond1.put("Item", "1 Fishing Rod");
        rooms.put("Pond 1", pond1);

        Map<String, String> pond2 = new HashMap<>();
        pond2.put("West", "Pond 1");
        pond2.put("Item", "1 Fishing Reel");
        rooms.put("Pond 2", pond2);

        Map<String, String> pond3 = new HashMap<>();
        pond3.put("East", "Parking Lot");
        pond3.put("Item", "1 Spool of Fishing Line");
        rooms.put("Pond 3", pond3);

        Map<String, String> pond4 = new HashMap<>();
        pond4.put("North", "Parking Lot");
        pond4.put("East", "Pond 5");
        pond4.put("Item", "1 Fishing Hook");
        rooms.put("Pond 4", pond4);

        Map<String, String> pond5 = new HashMap<>();
        pond5.put("West", "Pond 4");
        pond5.put("Item", "1 worm");
        rooms.put("Pond 5", pond5);

        Map<String, String> pond6 = new HashMap<>();
        pond6.put("North", "Pond 7");
        pond6.put("West", "Parking Lot");
        pond6.put("Item", "1 Bobber");
        rooms.put("Pond 6", pond6);

        Map<String, String> pond7 = new HashMap<>();
        pond7.put("South", "Pond 6");
        rooms.put("Pond 7", pond7);

        // Initializing text view and button variables, linking with layout
        main_screen = findViewById(R.id.screenText);

        north_button = findViewById(R.id.northButton);
        east_button = findViewById(R.id.eastButton);
        south_button = findViewById(R.id.southButton);
        west_button = findViewById(R.id.westButton);
        getItem_button = findViewById(R.id.getItemButton);
        restart_button = findViewById(R.id.restartButton);

        // Initial start up message
        main_screen.setText("Welcome To The Big Bass Hunt Text Adventure Game!" + "\nCollect the 6 items around the fishery before entering Pond 7 to catch the big bass and win the game." +
                "\nCurrent Location: " + user_room + "\nCurrent Inventory: " + user_inventory + "\nAvailable Moves: " + rooms.get(user_room));

        // if user clicks North
        north_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set input to North
                user_input = "North";
                // if input is a valid move
                if (rooms.get(user_room).containsKey(user_input)) {
                    // move user
                    user_room = rooms.get(user_room).get(user_input);
                    // show updated position
                    main_screen.setText("Current Location: " + user_room + "\nCurrent Inventory: " + user_inventory + "\nAvailable Moves: " + rooms.get(user_room));
                    // if user has collected all items and entered Pond 7
                    if(user_room == "Pond 7" && user_inventory.size() == goal_size){
                        // show user they have won
                        main_screen.setText("Congratulations! You have collected all 6 items and used them to catch the record bass!" + "\nGame over." + "\nPress Restart to play again.");
                        // disable all buttons except for restart button since the game is over
                        north_button.setEnabled(false);
                        east_button.setEnabled(false);
                        south_button.setEnabled(false);
                        west_button.setEnabled(false);
                        getItem_button.setEnabled(false);
                    }
                }
            }
        });

        // if user clicks East
        east_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set input to East
                user_input = "East";
                // if input is a valid move
                if (rooms.get(user_room).containsKey(user_input)) {
                    // move user
                    user_room = rooms.get(user_room).get(user_input);
                }
                // show updated position
                main_screen.setText("Current Location: " + user_room + "\nCurrent Inventory: " + user_inventory + "\nAvailable Moves: " + rooms.get(user_room));
            }
        });

        // if user clicks South
        south_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set input to South
                user_input = "South";
                // if input is a valid move
                if (rooms.get(user_room).containsKey(user_input)) {
                    // move user
                    user_room = rooms.get(user_room).get(user_input);
                }
                // show updated position
                main_screen.setText("Current Location: " + user_room + "\nCurrent Inventory: " + user_inventory + "\nAvailable Moves: " + rooms.get(user_room));
            }
        });

        // if user clicks West
        west_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set input to West
                user_input = "West";
                // if input is a valid move
                if (rooms.get(user_room).containsKey(user_input)) {
                    // move user
                    user_room = rooms.get(user_room).get(user_input);
                }
                // show updated position
                main_screen.setText("Current Location: " + user_room + "\nCurrent Inventory: " + user_inventory + "\nAvailable Moves: " + rooms.get(user_room));
            }
        });

        // if user clicks Get Item
        getItem_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the room contains an item that the user does not have
                if (rooms.get(user_room).containsKey("Item") && !user_inventory.contains(rooms.get(user_room).get("Item"))) {
                    // set current item to item in room
                    String current_item = rooms.get(user_room).get("Item");
                    // and the item slot is not empty
                    if (current_item != " ") {
                        // add item to inventory
                        user_inventory.add(current_item);
                        // remove item from room
                        rooms.get(user_room).put("Item", " ");
                    }
                }
                // show updated position
                main_screen.setText("Current Location: " + user_room + "\nCurrent Inventory: " + user_inventory + "\nAvailable Moves: " + rooms.get(user_room));
            }
        });

        // if user clicks Restart
        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // end activity
                finish();
                // restart activity
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
}
