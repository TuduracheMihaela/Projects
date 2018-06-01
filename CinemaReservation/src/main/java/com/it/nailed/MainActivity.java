package com.it.nailed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new LoadMovies().execute();
    }


    class LoadMovies extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog("Loading");
        }

        protected String doInBackground(String... args) {


            return "";
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            if (MainActivity.this.isFinishing()) {
                return;
            }
            runOnUiThread(new Runnable() {
                public void run() {

                    //final String movies[] = {"Select movie", "Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
                    final String movies[] = {"Select movie", "Red","Blue","White","Yellow","Black"};

                    ArrayAdapter<String> movie_adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, movies);
                    movie_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner sMovies = (Spinner) findViewById(R.id.spinnerMovie);
                    sMovies.setAdapter(movie_adapter);

                    sMovies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Spinner sMovies = (Spinner) findViewById(R.id.spinnerMovie);
                            String mIndex = sMovies.getSelectedItem().toString();

                            if(mIndex.contains("Select movie")){
                                // do nothing

                            }else{

                                int myIndex = sMovies.getSelectedItemPosition();
                                String movie = movies[myIndex];

                                new LoadDateTime(movie).execute();

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            });
            hideProgressDialog();
        }
    }


    class LoadDateTime extends AsyncTask<String, String, String> {

        String movie;

        public LoadDateTime(String movie){
            this.movie = movie;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog("Loading");
        }

        protected String doInBackground(String... args) {


            return "";
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            if (MainActivity.this.isFinishing()) {
                return;
            }
            runOnUiThread(new Runnable() {
                public void run() {

                    final String movie_red[] = {"01 Jun, 01:00 PM", "06 Jun, 05:00 PM"};
                    final String movie_blue[] = {"02 Jun, 02:00 PM", "07 Jun, 06:00 PM"};
                    final String movie_white[] = {"03 Jun, 03:00 PM", "08 Jun, 07:45 PM"};
                    final String movie_yellow[] = {"04 Jun, 04:00 PM", "09 Jun, 08:10 PM"};
                    final String movie_black[] = {"05 Jun, 02:00 PM", "10 Jun, 05:30 PM"};

                    if(movie.equals("Red")){
                        setDateTimeSpinner(movie_red);
                    }else if(movie.equals("Blue")){
                        setDateTimeSpinner(movie_blue);
                    }else if(movie.equals("White")){
                        setDateTimeSpinner(movie_white);
                    }else if(movie.equals("Yellow")){
                        setDateTimeSpinner(movie_yellow);
                    }else if(movie.equals("Black")){
                        setDateTimeSpinner(movie_black);
                    }

                }
            });
            hideProgressDialog();
        }
    }

    private void setDateTimeSpinner(final String[] datetime_array) {
        ArrayAdapter<String> movie_adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, datetime_array);
        movie_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sMovies = (Spinner) findViewById(R.id.spinnerDateTime);
        sMovies.setAdapter(movie_adapter);

        sMovies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner sMovies = (Spinner) findViewById(R.id.spinnerDateTime);
                String mIndex = sMovies.getSelectedItem().toString();

                if(mIndex.equals("")){
                    // do nothing

                }else{

                    int myIndex = sMovies.getSelectedItemPosition();
                    String datetime = datetime_array[myIndex];

                    Button btnSeat = (Button) findViewById(R.id.btnSeat);
                    btnSeat.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * Shows a Progress Dialog
     *
     * @param msg
     */
    public void showProgressDialog(String msg)
    {

        // check for existing progressDialog
        if (progressDialog == null) {
            // create a progress Dialog
            progressDialog = new ProgressDialog(this);

            // remove the ability to hide it by tapping back button
            progressDialog.setIndeterminate(true);

            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);

            progressDialog.setMessage(msg);

        }

        // now display it.
        progressDialog.show();
    }


    /**
     * Hides the Progress Dialog
     */
    public void hideProgressDialog() {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        progressDialog = null;
    }
}
