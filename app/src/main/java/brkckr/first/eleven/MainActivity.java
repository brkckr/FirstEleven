package brkckr.first.eleven;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    List<Player> playerList;
    LinearLayoutCompat llContainer;

    AppCompatTextView txtClub;
    AppCompatTextView txtCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llContainer = findViewById(R.id.llContainer);
        txtClub = findViewById(R.id.txtClub);
        txtCoach = findViewById(R.id.txtCoach);

        playerList = createPlayerList();

        createFirstEleven(new FirstEleven("Galatasaray", "Fatih Terim", "4-1-4-1", playerList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.formation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        cleanContainer();
        createFirstEleven(new FirstEleven("Galatasaray", "Fatih Terim", item.getTitle().toString(), playerList));
        return true;
    }


    /**
     * creates sample data
     * <p>
     * starts from the goalkeeper, lining up towards the strikers.
     * works from right to left for each position.
     * To make the placement on the screen properly, you must set the player list according to the above.
     *
     * @return
     */
    private List<Player> createPlayerList()
    {
        List<Player> playerList = new ArrayList<>();

        playerList.add(new Player("Muslera", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_9ofktlxhrtzgvhm5btvvlq6l1.png?v=1.69.0&gis=mk", 1));
        playerList.add(new Player("Omar", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_9kv7at589nwkud8a1hmgkspn9.png?v=1.69.0&gis=mk", 3));
        playerList.add(new Player("Luyindama", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_b2c57cig5rvn9tnwwlifoewo9.png?v=1.69.0&gis=mk", 27));
        playerList.add(new Player("Marcao", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_1ghrcpi1cl0koh36puwblbfmd.png?v=1.69.0&gis=mk", 45));
        playerList.add(new Player("Saracchi", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_9qmyubxwvhhotzx7elbuzn7jd.png?v=1.69.0&gis=mk", 36));
        playerList.add(new Player("Taylan", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_cvp10zci3p7gg5gi9bmbgj6qd.png?v=1.69.0&gis=mk", 4));
        playerList.add(new Player("Feghouli", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_8lk61g6juvnra8cdaq1cgho7p.png?v=1.69.0&gis=mk", 89));
        playerList.add(new Player("Belhanda", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_3q5uf1exb4scte1iyeq5aa7yt.png?v=1.69.0&gis=mk", 10));
        playerList.add(new Player("Emre", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_c6rzadc9bx74tw06zdubmtc5x.png?v=1.69.0&gis=mk", 54));
        playerList.add(new Player("Arda", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_doiy4u54g1mxykz1z19d6tgwl.png?v=1.69.0&gis=mk", 66));
        playerList.add(new Player("Falcao", "https://secure.cache.images.core.optasports.com/soccer/players/150x150/uuid_8oc3lpz3xttrnwxk1fnnv7owl.png?v=1.69.0&gis=mk", 9));

        return playerList;
    }

    /**
     * creates first eleven
     *
     * @param firstEleven
     */
    private void createFirstEleven(FirstEleven firstEleven)
    {
        setClub(firstEleven.club, firstEleven.formation);
        setCoach(firstEleven.coach);

        addPositions(firstEleven.formation);
        addPlayers(firstEleven);
    }

    /**
     * adds the positions to the field
     * each position means a LinearLayout
     * <p>
     * for 4-1-4-1, adds 5 positions from goalkeeper to striker
     */
    private void addPositions(String formation)
    {
        //adds one more position
        //just wanna make it like that
        //you can change it
        for (int i = 0; i <= getPositionCount(formation); i++)
        {
            LinearLayoutCompat layoutPosition = new LinearLayoutCompat(this);

            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 0);
            layoutParams.weight = 1;
            layoutPosition.setLayoutParams(layoutParams);
            layoutPosition.setGravity(Gravity.CENTER);
            layoutPosition.setOrientation(LinearLayoutCompat.HORIZONTAL);

            layoutPosition.setId(i);

            llContainer.addView(layoutPosition);
        }
    }

    /**
     * adds players to the field.
     *
     * @param firstEleven
     */
    private void addPlayers(FirstEleven firstEleven)
    {
        int[] formationArray = getFormationArray(firstEleven.formation.split("-"));

        //starting index
        //end index
        // for ex : 4-1-4-1 means 4 defenders so defender position has 4 players
        // start = 0 was goalkeeper.
        // start = 1 is the first defender index in player list.
        // end = 5 is the last defender index in player list
        int start = 0;
        int end = 0;

        for (int i = 0; i < formationArray.length; i++)
        {
            start = end;
            end += formationArray[i];

            final LinearLayoutCompat positionLayout = llContainer.findViewById(i);

            for (int j = start; j < end; j++)
            {
                //adds player container layout to the position layout.
                final LinearLayoutCompat playerContainerLayout = getPlayerContainerLayout(i);
                positionLayout.addView(playerContainerLayout);

                final LinearLayoutCompat playerLayout = getPlayerLayout(firstEleven.playerList.get(j));

                //adds with delay.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        playerContainerLayout.addView(playerLayout);
                    }
                }, 500 + 250 * j);
            }
        }
    }

    private LinearLayoutCompat getPlayerContainerLayout(int index)
    {
        LinearLayoutCompat playerContainerLayout = new LinearLayoutCompat(this);
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(0, LinearLayoutCompat.LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        playerContainerLayout.setLayoutParams(layoutParams);
        playerContainerLayout.setGravity(Gravity.CENTER);

        playerContainerLayout.setId(index);

        return playerContainerLayout;
    }

    private LinearLayoutCompat getPlayerLayout(Player player)
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayoutCompat layout = (LinearLayoutCompat) inflater.inflate(R.layout.layout_player, null, false);

        setPlayer(layout, player);

        return layout;
    }

    /**
     * sets player's data
     *
     * @param layout
     * @param player
     */
    private void setPlayer(LinearLayoutCompat layout, Player player)
    {
        AppCompatImageView imgProfilePicture = layout.findViewById(R.id.imgProfilePicture);

        Glide.with(this).load(player.profilePictureUrl).transform(new CenterCrop(), new RoundedCorners(10)).into(imgProfilePicture);

        AppCompatTextView txtName = layout.findViewById(R.id.txtName);
        txtName.setText(player.name);
        AppCompatTextView txtNumber = layout.findViewById(R.id.txtNumber);
        txtNumber.setText(String.valueOf(player.number));
    }

    private void setCoach(String coach)
    {
        txtCoach.setText(coach);
    }

    private void setClub(String club, String formation)
    {
        txtClub.setText(club + " (" + formation + ")");
    }

    private int getPositionCount(String formation)
    {
        int[] formationArray = getFormationArray(formation.split("-"));
        return formationArray.length;
    }

    /**
     * gets formation array as int array
     * first element of the array is the goalkeeper.
     *
     * @param stringArray : formation as string array
     * @return
     */
    private int[] getFormationArray(String[] stringArray)
    {
        int[] numberArray = new int[stringArray.length + 1];
        numberArray[0] = 1; // here is the goalkeeper.

        // adds the numbers from formation (for ex : 4-1-4-1)
        for (int i = 1; i < numberArray.length; i++)
        {
            numberArray[i] = Integer.parseInt(stringArray[i - 1]);
        }

        return numberArray;
    }

    private void cleanContainer()
    {
        llContainer.removeAllViews();
    }
}