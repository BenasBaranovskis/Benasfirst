package k.benasfirst;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {


    SearchView searchView = null;

    RecyclerView rvPokemonai;
    TargetAdapter targetAdapter;

            List<Target> DatabaseSQLite = Collections.emptyList();
    List<Target> targetsearch = Collections.emptyList();

            DatabaseSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        setTitle(R.string.search_label);


                db = new DatabaseSQLite(SearchActivity.this);

                         // Taupydami duomenų bazės resursus, darome 1 call'ą (getAllPokemonai) užkrovus paieškos langą,
                                // vėliau (not implemented) pokemonų ieškome iš užpildyto sąrašo
                                        DatabaseSQLite = db.getAlltargets();

                        if (!DatabaseSQLite.isEmpty()) {
                        setRecycleView(DatabaseSQLite);
                    } else {
                        Toast.makeText(this, "Duomenų bazėje nėra įrašų", Toast.LENGTH_SHORT).show();
                    }

                        Button btnPrideti = (Button) findViewById(R.id.btnNewEntry);
        btnPrideti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, NewTargetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //adds item to actionbar
        getMenuInflater().inflate(R.menu.search, menu);
        //get search item from actionbar and get search service
        MenuItem searchitem = menu.findItem(R.id.btnSearchTarget);
        SearchManager searchManger = (SearchManager) SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchitem != null) {
            searchView = (SearchView) searchitem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManger.getSearchableInfo(SearchActivity.this.getComponentName()));
            searchView.setIconified(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    //every time when you press search button an actvity is recreated which in turn
    //calls this function
    protected void onNewIntent(Intent intent) {
        //get search query and create object of class AsyncFetch
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();
            }
            new AsyncFetch(query).execute();
        }
    }

    /*
        setup and hand over list pokemonaiSQLite to recyclerview
        @params list of pokemonai from db
     */
            private void setRecycleView(List<Target> pokemonaiSQLite) {
                rvPokemonai = (RecyclerView) findViewById(R.id.pokemon_list);
                TargetAdapter = new TargetAdapter(SearchActivity.this,DatabaseSQLlite);
               rvPokemonai.setAdapter(targetAdapter);
                rvPokemonai.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
            }

    class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog = new ProgressDialog(SearchActivity.this);
        String searchQuery;


        public AsyncFetch(String searchQuery) {
            this.searchQuery = searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Prašome palaukti");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {


                        // jeigu negaila db resursų, galima kiekvieną kartą call'inti pagal įvestus kriterijus paieškos
                                DatabaseSQLite = db.getTargetByName(searchQuery);

                                // vartotojo paieska vykdoma sarase (ne db)
                                        //TODO iteruoja per cikla kol suranda tinkama ir tada uzluzta.
                                               /* for (int i = 1 ; i <pokemonaiSQLite.size();i++) {
                if (pokemonaiSQLite.get(i).getName().equals(searchQuery)) {
                    Toast.makeText(SearchActivity.this, pokemonaiSQLite.get(i).toString(), Toast.LENGTH_SHORT).show();
                   //pokemonaiPaieskai.add(pokemonaiSQLite.get(i));
                }
            }*/



                            if (DatabaseSQLite.isEmpty()) {
                    return "no rows";
                } else {
                    return "rows";
                }

            }

            @Override
            protected void onPostExecute(String result) {
                progressDialog.dismiss();

                            setRecycleView(DatabaseSQLite);
            }
        }
    }
