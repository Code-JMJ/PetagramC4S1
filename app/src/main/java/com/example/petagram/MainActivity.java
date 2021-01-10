package com.example.petagram;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.petagram.RestApi.EndpointsAPI;
import com.example.petagram.RestApi.adapter.RestApiAdapter;
import com.example.petagram.model.PetResponse;
import com.example.petagram.pojo.ProfileItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.petagram.ui.main.SectionsPagerAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static ArrayList<ProfileItem> profileItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Aún no implementada!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        profileItems = new ArrayList<>();
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    RestApiAdapter restApiAdapter = new RestApiAdapter();
                    Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializeMediaRecent();
                    EndpointsAPI endpointsAPI = restApiAdapter.stablishConnectionRestAPInstagram(gsonMediaRecent);
                    Call<PetResponse> petResponseCall = endpointsAPI.getRecentMedia();
                    petResponseCall.enqueue(new Callback<PetResponse>() {
                        @Override
                        public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                            ArrayList<ProfileItem> newProfileItems = new ArrayList<>();
                            PetResponse petResponse = response.body();
                            newProfileItems = petResponse.getProfileItems();
                            for (int i = 0; i < newProfileItems.size(); i++) {
                                ProfileItem newProfileItem = newProfileItems.get(i);
                                for (int j = 0; j < profileItems.size(); j++) {
                                    ProfileItem oldProfileItem = profileItems.get(j);
                                    if (newProfileItem.getId().equals(oldProfileItem.getId())) {
                                        if (newProfileItem.getLikes() > oldProfileItem.getLikes()) {
                                            System.out.println("Tienes un nuevo like");
                                            launchNotification(newProfileItem.getUrlPetPic(), newProfileItem.getLikes());
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PetResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Falló la conexión con servidor", Toast.LENGTH_LONG).show();
                            Log.e("Connection failed", t.toString());
                        }
                    });
                    try {
                        sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
        // [END handle_data_extras]

    public void launchNotification(String url, int likes) {
        Intent i = new Intent(this, Detail.class);
        i.putExtra(Detail.KEY_EXTRA_URL, url);
        i.putExtra(Detail.KEY_EXTRA_LIKES, likes);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_baseline_touch_app_24, "View notification", pendingIntent)
                .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender();

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, getString(R.string.channelId))
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("You have a new like!")
                .setContentText("Click to display it!")
                .setSound(uri)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .extend(wearableExtender
                        .addAction(action));
                //.addAction(R.drawable.ic_baseline_touch_app_24, "Devuelve el toque", pendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channelId), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }

        // Issue the notification with notification manager.
        notificationManager.notify(0, nBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_contact:
                Intent goToContact = new Intent(MainActivity.this, Contact.class);
                startActivity(goToContact);
                break;

            case R.id.action_about:
                Intent i =  new Intent(this, AcercaD.class);
                startActivity(i);
                break;

            case R.id.action_favorites:
                Intent goToFavorites = new Intent(MainActivity.this, Favorites.class);
                startActivity(goToFavorites);
                break;
/*
            case R.id.action_receive_notifications:
                // Get token
                // [START retrieve_current_token]
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }

                                // Get new Instance ID token
                                String token = task.getResult().getToken();

                                // Log and toast
                                String msg = getString(R.string.msg_token_fmt, token);
                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, msg + "/n" + "id_usuario_instagram: " + ConstantsRestApi.USER_ID, Toast.LENGTH_SHORT).show();
                            }
                        });
                // [END retrieve_current_token]
                break;
                */

        }

        return super.onOptionsItemSelected(item);
    }
}