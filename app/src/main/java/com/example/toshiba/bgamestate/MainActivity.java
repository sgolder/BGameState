package com.example.toshiba.bgamestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText testEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testEditText = (EditText) findViewById(R.id.testText);
        Button runTestButton = (Button)findViewById(R.id.testButton);
        runTestButton.setOnClickListener(new buttonListener());
    }

    private class buttonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int playerId = 0; // Current player being passed this BState
            BState firstInstance = new BState( playerId );
            BPlayerState[] firstPlayerList = firstInstance.getPlayerList();
            firstInstance.setEditText(testEditText);
            testEditText.append("First Instance\n");
            testEditText.append(firstInstance.toString()+"\n\n");
            //Make changes and call action methods
            firstPlayerList[3].setName("Geneveve");
            firstInstance.buyThirdField(playerId);
            firstInstance.plantBean(playerId, 1,
                    firstPlayerList[playerId].getHand().peekAtTopCard(),
                    firstPlayerList[playerId].getHand());

            // SECOND INSTANCE
            BState secondInstance = new BState(firstInstance, playerId);
            BPlayerState[] secondPlayerList = secondInstance.getPlayerList();
            testEditText.append("Second Instance\n");
            testEditText.append(secondInstance.toString()+"\n\n");

        }
    }
}
