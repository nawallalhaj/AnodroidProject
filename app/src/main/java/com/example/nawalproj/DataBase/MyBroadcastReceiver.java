package com.example.nawalproj.DataBase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String AUCTION_HOUR_OVER_ACTION = "com.example.app.AUCTION_HOUR_OVER";
    public static final String EXTRA_HIGHEST_BID_PRICE = "com.example.app.EXTRA_HIGHEST_BID_PRICE";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AUCTION_HOUR_OVER_ACTION)) {
            // Broadcast received after auction hour is over
            // Extract the highest bid price from the intent
            double highestBidPrice = intent.getDoubleExtra(EXTRA_HIGHEST_BID_PRICE, 0.0);

            // Send message to all users in your app including the highest bid price
            sendMessageToAllUsers(context, highestBidPrice);
        }
    }

    private void sendMessageToAllUsers(Context context, double highestBidPrice) {
        // Code to send message to all users in your app
        // You can use any method here to notify users, such as sending a notification or updating UI
        String message = "Auction is over! The highest bid price was: " + highestBidPrice + "₪";
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}