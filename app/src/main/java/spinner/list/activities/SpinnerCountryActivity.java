package spinner.list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.nimgade.pk.mytutorialapplication.R;

import java.util.ArrayList;

/**
 * This activity is created to answer this question<br>
 * {http://stackoverflow.com/questions/36603522/country-state-and-city-spinner-not-working-correctly}
 */
public class SpinnerCountryActivity extends AppCompatActivity {

    private Spinner country_Spinner;
    private Spinner state_Spinner;
    private Spinner city_Spinner;

    private ArrayAdapter<Country> countryArrayAdapter;
    private ArrayAdapter<State> stateArrayAdapter;
    private ArrayAdapter<City> cityArrayAdapter;

    private ArrayList<Country> countries;
    private ArrayList<State> states;
    private ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_country);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_custom_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeUI();
    }

    private void initializeUI() {
        country_Spinner = (Spinner) findViewById(R.id.SpinnerCountryActivity_country_spinner);
        state_Spinner = (Spinner) findViewById(R.id.SpinnerCountryActivity_state_spinner);
        city_Spinner = (Spinner) findViewById(R.id.SpinnerCountryActivity_city_spinner);

        countries = new ArrayList<>();
        states = new ArrayList<>();
        cities = new ArrayList<>();

        createLists();

        countryArrayAdapter = new ArrayAdapter<Country>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, countries);
        countryArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        country_Spinner.setAdapter(countryArrayAdapter);

        stateArrayAdapter = new ArrayAdapter<State>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, states);
        stateArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        state_Spinner.setAdapter(stateArrayAdapter);

        cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, cities);
        cityArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        city_Spinner.setAdapter(cityArrayAdapter);

        country_Spinner.setOnItemSelectedListener(country_listener);
        state_Spinner.setOnItemSelectedListener(state_listener);
        city_Spinner.setOnItemSelectedListener(city_listener);

    }

    private AdapterView.OnItemSelectedListener country_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final Country country = (Country) country_Spinner.getItemAtPosition(position);
                Log.d("SpinnerCountry", "onItemSelected: country: "+country.getCountryID());
                ArrayList<State> tempStates = new ArrayList<>();

                tempStates.add(new State(0, new Country(0, "Choose a Country"), "Choose a State"));

                for (State singleState : states) {
                    if (singleState.getCountry().getCountryID() == country.getCountryID()) {
                        tempStates.add(singleState);
                    }
                }

                stateArrayAdapter = new ArrayAdapter<State>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, tempStates);
                stateArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                state_Spinner.setAdapter(stateArrayAdapter);
            }

            cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, new ArrayList<City>());
            cityArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            city_Spinner.setAdapter(cityArrayAdapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener state_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final State state = (State) state_Spinner.getItemAtPosition(position);
                Log.d("SpinnerCountry", "onItemSelected: state: "+state.getStateID());
                ArrayList<City> tempCities = new ArrayList<>();

                Country country = new Country(0, "Choose a Country");
                State firstState = new State(0, country, "Choose a State");
                tempCities.add(new City(0, country, firstState, "Choose a City"));

                for (City singleCity : cities) {
                    if (singleCity.getState().getStateID() == state.getStateID()) {
                        tempCities.add(singleCity);
                    }
                }

                cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, tempCities);
                cityArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                city_Spinner.setAdapter(cityArrayAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener city_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void createLists() {
        Country country0 = new Country(0, "Choose a Country");
        Country country1 = new Country(1, "Country1");
        Country country2 = new Country(2, "Country2");

        countries.add(new Country(0, "Choose a Country"));
        countries.add(new Country(1, "Country1"));
        countries.add(new Country(2, "Country2"));

        State state0 = new State(0, country0, "Choose a Country");
        State state1 = new State(1, country1, "state1");
        State state2 = new State(2, country1, "state2");
        State state3 = new State(3, country2, "state3");
        State state4 = new State(4, country2, "state4");

        states.add(state0);
        states.add(state1);
        states.add(state2);
        states.add(state3);
        states.add(state4);

        cities.add(new City(0, country0, state0, "Choose a City"));
        cities.add(new City(1, country1, state1, "City1"));
        cities.add(new City(2, country1, state1, "City2"));
        cities.add(new City(3, country1, state2, "City3"));
        cities.add(new City(4, country2, state2, "City4"));
        cities.add(new City(5, country2, state3, "City5"));
        cities.add(new City(6, country2, state3, "City6"));
        cities.add(new City(7, country2, state4, "City7"));
        cities.add(new City(8, country1, state4, "City8"));
    }

    private class Country implements Comparable<Country> {

        private int countryID;
        private String countryName;


        public Country(int countryID, String countryName) {
            this.countryID = countryID;
            this.countryName = countryName;
        }

        public int getCountryID() {
            return countryID;
        }

        public String getCountryName() {
            return countryName;
        }

        @Override
        public String toString() {
            return countryName;
        }


        @Override
        public int compareTo(Country another) {
            return this.getCountryID() - another.getCountryID();//ascending order
//            return another.getCountryID()-this.getCountryID();//descending  order
        }
    }

    private class State implements Comparable<State> {

        private int stateID;
        private Country country;
        private String stateName;

        public State(int stateID, Country country, String stateName) {
            this.stateID = stateID;
            this.country = country;
            this.stateName = stateName;
        }

        public int getStateID() {
            return stateID;
        }

        public Country getCountry() {
            return country;
        }

        public String getStateName() {
            return stateName;
        }

        @Override
        public String toString() {
            return stateName;
        }

        @Override
        public int compareTo(State another) {
            return this.getStateID() - another.getStateID();//ascending order
//            return another.getStateID()-this.getStateID();//descending order
        }
    }

    private class City implements Comparable<City> {

        private int cityID;
        private Country country;
        private State state;
        private String cityName;

        public City(int cityID, Country country, State state, String cityName) {
            this.cityID = cityID;
            this.country = country;
            this.state = state;
            this.cityName = cityName;
        }

        public int getCityID() {
            return cityID;
        }

        public Country getCountry() {
            return country;
        }

        public State getState() {
            return state;
        }

        public String getCityName() {
            return cityName;
        }

        @Override
        public String toString() {
            return cityName;
        }

        @Override
        public int compareTo(City another) {
            return this.cityID - another.getCityID();//ascending order
//            return another.getCityID() - this.cityID;//descending order
        }
    }
}
