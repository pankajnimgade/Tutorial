package network.calls.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.nimgade.pk.mytutorialapplication.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class ParseJsonTestOneActivity extends AppCompatActivity {

    private Button start_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json_test_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initializeUI();
    }

    private void initializeUI() {
        start_Button = (Button) findViewById(R.id.ParseJsonTestOneActivity_start_button);
        start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadData().execute();
            }
        });
    }

    private class DownloadData extends AsyncTask<Void,Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try{

                URL url = new URL("https://blockchain.info/ticker");
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
                httpsURLConnection.connect();
                String result = IOUtils.toString(httpsURLConnection.getInputStream());
                System.out.println(""+result);

                HashMap<String, Information> map = new HashMap<>();

                JSONObject jsonObject = new JSONObject(result);
                Iterator<String> iterator = jsonObject.keys();

                while (iterator.hasNext()){
                    String key = iterator.next();
                    System.out.println(key);

                    Information information = new Information();
                    information.setA_15m(jsonObject.getJSONObject(key).getString("15m"));
                    information.setBuy(jsonObject.getJSONObject(key).getString("last"));
                    information.setLast(jsonObject.getJSONObject(key).getString("buy"));
                    information.setSell(jsonObject.getJSONObject(key).getString("sell"));
                    information.setSymbol(jsonObject.getJSONObject(key).getString("symbol"));
                    map.put(key,information);
                }

                Set<String> key_Strings = map.keySet();

                for (String single_key:key_Strings) {
                    System.out.println();
                    Information information = map.get(single_key);
                    System.out.println("Currency: "+single_key);
                    System.out.println("15m: "+information.getA_15m());
                    System.out.println("Buy: "+information.getBuy());
                    System.out.println("Last: "+information.getLast());
                    System.out.println("Sell: "+information.getSell());
                    System.out.println("Symbol: "+information.getSymbol());
                    System.out.println();
                }

            }catch (Exception e){
                e.printStackTrace();
            }            return null;
        }
    }

    private class Information {

        private String a_15m;
        private String last;
        private String buy;
        private String sell;
        private String symbol;

        public String getA_15m() {
            return a_15m;
        }

        public void setA_15m(String a_15m) {
            this.a_15m = a_15m;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

}
