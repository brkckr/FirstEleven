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
}